package com.testproject.converter.services;

import com.testproject.converter.dao.CurrenciesRepository;
import com.testproject.converter.dao.HistoryRepository;
import com.testproject.converter.domain.Currency;
import com.testproject.converter.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    static final String URL_NAME = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static Logger logger = Logger.getLogger(CurrencyServiceImpl.class);
    @Autowired
    CurrenciesRepository currenciesRepository;

    @Autowired
    HistoryRepository historyRepository;


    @Override
    public void getCurrency() {

        URL url = null;
        try {
            url = new URL(URL_NAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(url.openConnection().getInputStream());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Node root = document.getDocumentElement();
            java.util.Date langDate = sdf.parse(root.getAttributes().getNamedItem("Date").getNodeValue());
            Date date = new Date(langDate.getTime());
            NodeList currencys = root.getChildNodes();
            for (int i = 0; i < currencys.getLength(); i++) {
                Node currency = currencys.item(i);

                Currency item = new Currency(date);
                item.setValuteId(currency.getAttributes().getNamedItem("ID").getNodeValue());
                if (currency.getNodeType() != Node.TEXT_NODE) {
                    NodeList currencyProps = currency.getChildNodes();

                    for (int j = 0; j < currencyProps.getLength(); j++) {
                        Node currencyProp = currencyProps.item(j);
                        if (currencyProp.getNodeType() != Node.TEXT_NODE) {
                            switch (currencyProp.getNodeName()) {
                                case "NumCode" -> item.setNumCode(Integer.parseInt(currencyProp.getChildNodes().item(0).getTextContent()));
                                case "CharCode" -> item.setCharCode(currencyProp.getChildNodes().item(0).getTextContent());
                                case "Nominal" -> item.setNominal(Integer.parseInt(currencyProp.getChildNodes().item(0).getTextContent()));
                                case "Name" -> item.setName(currencyProp.getChildNodes().item(0).getTextContent());
                                case "Value" -> item.setValue(Double.parseDouble(currencyProp.getChildNodes().item(0).getTextContent().replace(',', '.')));
                            }
                        }
                    }
                    currenciesRepository.save(item);
                }
            }
            logger.info("Записали актуальные валюты и их курсы в БД");
        } catch (ParserConfigurationException ex) {
            logger.error("Error parser!", ex);
        } catch (SAXException ex) {
            logger.error("Error SAX!", ex);
        } catch (IOException ex) {
            logger.error("IOException!", ex);
        } catch (ParseException ex) {
            logger.error("ParseException!", ex);
        }

    }

    @Override
    public List<Currency> getCurrencies(Date date) {
        List<Currency> list = currenciesRepository.findByDate(date);
        if (!list.isEmpty()) {
            return list;
        } else
        return currenciesRepository.findByDateAfter(date);
    }

    @Override
    public Iterable<History> getHistory() {
        return historyRepository.findAll();
    }

    @Override
    public Currency getCurrencyByDate(Date date) {
        Currency currency = currenciesRepository.findFirstByDate(new Date(System.currentTimeMillis()));
        if (currency != null) {
            return currency;
        } else return currenciesRepository.findFirstByDateAfter(new Date(System.currentTimeMillis()));
    }

    @Override
    public double convert(String origValute, double originalAmount, String targetValute) {
        Currency origCurency = currenciesRepository.findFirstByName(origValute);
        Currency targetCurency = currenciesRepository.findFirstByName(targetValute);
        double res = (origCurency.getValue() / origCurency.getNominal() * originalAmount) / (targetCurency.getValue() / targetCurency.getNominal());
        historyRepository.save(new History(origValute, targetValute, originalAmount, res, new Date(System.currentTimeMillis())));
        logger.info("Записали в историю новую конвертацию");
        return res;
    }


}

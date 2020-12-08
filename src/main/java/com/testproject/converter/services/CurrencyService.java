package com.testproject.converter.services;

import com.testproject.converter.domain.Currency;
import com.testproject.converter.domain.History;


import java.sql.Date;
import java.util.List;

public interface CurrencyService {
    void getCurrency();
    List<Currency> getCurrencies(Date date);
    double convert(String origValute, double originalAmount, String targetValute);
    Iterable<History> getHistory();
    Currency getCurrencyByDate(Date date);
}

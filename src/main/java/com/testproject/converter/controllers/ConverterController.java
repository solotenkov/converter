package com.testproject.converter.controllers;

import com.testproject.converter.domain.Request;
import com.testproject.converter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.Date;


@Controller
public class ConverterController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/converter")
    public String getConverter(Model model){
        Date currentDate = new Date(System.currentTimeMillis());
        if (currencyService.getCurrencyByDate(currentDate) == null){
            currencyService.getCurrency();
        }
        model.addAttribute("request", new Request());
        model.addAttribute("currencyList", currencyService.getCurrencies(currentDate));
        return "converter";
    }

    @PostMapping("/converter")
    public String converter(@ModelAttribute Request request, Model model){
        model.addAttribute("request", request);
        model.addAttribute("currencyList", currencyService.getCurrencies(new Date(System.currentTimeMillis())));
        request.setReceivedAmount(currencyService.convert(request.getOrigValute(),  request.getOriginalAmount(), request.getTargetValute()));
        return "converter";
    }

    @GetMapping("/history")
    public String history(Model model){
        model.addAttribute("history", currencyService.getHistory());
        return "history";
    }

}

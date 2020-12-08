package com.testproject.converter.dao;

import com.testproject.converter.domain.Currency;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface CurrenciesRepository extends CrudRepository<Currency, String> {
    Currency findFirstByDateAfter(Date date);
    Currency findFirstByDate(Date date);
    List<Currency> findByDate(Date date);
    List<Currency> findByDateAfter(Date date);
    Currency findFirstByName(String name);
}

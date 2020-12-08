package com.testproject.converter.dao;

import com.testproject.converter.domain.Currency;
import com.testproject.converter.domain.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, String> {

}

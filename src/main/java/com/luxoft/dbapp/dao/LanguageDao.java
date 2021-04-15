package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LanguageDao extends PagingAndSortingRepository<Language, Long> {
}

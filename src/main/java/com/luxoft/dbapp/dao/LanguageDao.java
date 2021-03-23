package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageDao extends CrudRepository<Language, Long> {
}

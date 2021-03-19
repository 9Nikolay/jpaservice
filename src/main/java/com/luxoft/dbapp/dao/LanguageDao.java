package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface LanguageDao extends CrudRepository<Language, Long> {
}

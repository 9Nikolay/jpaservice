package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.FilmText;
import org.springframework.data.repository.CrudRepository;

public interface FilmTextDao extends CrudRepository<FilmText, Long> {
}

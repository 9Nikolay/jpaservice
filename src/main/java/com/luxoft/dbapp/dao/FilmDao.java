package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmDao extends CrudRepository<Film, Long> {
    List<Film> findByTitle(String title);
}

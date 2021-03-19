package com.luxoft.dbapp.service;

import com.luxoft.dbapp.entities.Film;
import com.luxoft.dbapp.entities.FilmActor;
import com.luxoft.dbapp.entities.Language;

import java.util.List;

public interface FilmService {
    List<Film> findAllFilms();
    List<Film> findAllWithAlbum();
    Film saveFilm(Film singer);
    FilmActor saveFilmActor(FilmActor singer);
    void delete(Film singer);
    List<String> findAllByNativeQuery();
}

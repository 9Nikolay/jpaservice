package com.luxoft.dbapp.services;

import com.luxoft.dbapp.entities.Film;
import com.luxoft.dbapp.entities.FilmActor;

import java.util.List;

public interface FilmService {
    List<Film> findAllFilms();
    List<Film> findAllWithAlbum();
    Film saveFilm(Film singer);
    FilmActor saveFilmActor(FilmActor singer);
    void delete(Film singer);
    List<String> findAllByNativeQuery();
}

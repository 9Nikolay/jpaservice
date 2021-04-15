package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Film;
import com.luxoft.dbapp.entities.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FilmDao extends PagingAndSortingRepository<Film, Long> {
    @Query(value = "select f from Film f where f.filmId in (select i.film.filmId from Inventory i group by i.film.filmId)")
    Page<Film> findAvailabeFilms(Pageable pageable);
}

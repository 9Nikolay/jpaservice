package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    City save(City City);

    Optional<City> findById(Long id);

    List<City> findAll();

    long count();

    void delete(City City);

    boolean existsById(Long id);
}

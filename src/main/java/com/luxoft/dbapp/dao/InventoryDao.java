package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Country;

import java.util.List;
import java.util.Optional;

public interface InventoryDao {
    Country save(Country Country);

    Optional<Country> findById(Long id);

    List<Country> findAll();

    long count();

    void delete(Country Country);

    boolean existsById(Long id);
}

package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalDao extends CrudRepository<Rental, Long> {
}

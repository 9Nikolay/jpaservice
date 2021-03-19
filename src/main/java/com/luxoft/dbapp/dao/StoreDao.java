package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreDao extends CrudRepository<Store, Long> {
}

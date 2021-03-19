package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffDao extends CrudRepository<Staff, Long> {
}

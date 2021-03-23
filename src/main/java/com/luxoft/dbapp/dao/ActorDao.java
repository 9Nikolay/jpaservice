package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorDao extends CrudRepository<Actor, Long> {
}

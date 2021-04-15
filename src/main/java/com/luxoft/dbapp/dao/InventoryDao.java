package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InventoryDao extends PagingAndSortingRepository<Inventory, Long> {

    List<Inventory> getAllByFilm_filmId(Long filmId);

    @Query(value = "select i from Inventory i where i.inventoryId not in" +
            " (select r.inventory.inventoryId from Rental r where r.returnDate is null group by r.inventory.inventoryId)" +
            " and i.film.filmId = ?1")
    List<Inventory> getAvailableInventoriesByFilmId(Long filmId);
}

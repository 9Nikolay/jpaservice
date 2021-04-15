package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalDao extends JpaRepository<Rental, Long> {
    List<Rental> findAllByCustomerId(Integer customerId);

    @Query("select r from Rental r where r.inventory.film.filmId=?1")
    List<Rental> findRentalsByFilmId(Long filmId);

    @Query(value = "select r.* from rental r join inventory i on i.inventory_id = r.inventory_id join film f on f.film_id = i.film_id " +
            "where r.return_date is null and date_add(r.rental_date, INTERVAL f.rental_duration DAY) < CURRENT_DATE", nativeQuery = true)
    List<Rental> findExpiredRentals();
}

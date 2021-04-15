package com.luxoft.dbapp.dao;

import com.luxoft.dbapp.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.rental.inventory.film.filmId=1")
    List<Payment> findPaymentsByFilmId(Long filmId);
}

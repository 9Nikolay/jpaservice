package com.luxoft.dbapp.servicesimpl;

import com.luxoft.dbapp.dao.RentalDao;
import com.luxoft.dbapp.dao.security.UserDao;
import com.luxoft.dbapp.entities.Rental;
import com.luxoft.dbapp.entities.security.User;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@Transactional
@AllArgsConstructor
public class ScheduledService {

    RentalDao rentalDao;
    UserDao userDao;

    /**
     * Метод предназначен для поиска аренд с истекшиим сроком действия
     */
    @Scheduled(fixedDelay = 100000)
    public void findExpiredRents() {
        List<Rental> rentals = rentalDao.findExpiredRentals();
        List<User> users = userDao.findAllById(rentals.stream().map(r -> r.getCustomerId()).collect(Collectors.toList()));
        System.out.println("Найдено " + rentals.size() + " не сданных фильмов");
        System.out.println("Отправка уведомлений пользователям " + users.stream().map(u -> u.getEmail()).collect(Collectors.toList()));
    }
}

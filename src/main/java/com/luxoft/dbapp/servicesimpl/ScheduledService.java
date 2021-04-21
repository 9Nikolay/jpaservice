package com.luxoft.dbapp.servicesimpl;

import com.luxoft.dbapp.aop.AdviceRequired;
import com.luxoft.dbapp.dao.RentalDao;
import com.luxoft.dbapp.dao.security.UserDao;
import com.luxoft.dbapp.entities.Rental;
import com.luxoft.dbapp.entities.security.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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

    private RentalDao rentalDao;
    private UserDao userDao;
    private JmsTemplate jmsTemplate;

    /**
     * Метод предназначен для поиска аренд с истекшиим сроком действия
     */
    @Scheduled(fixedDelay = 10000)
    @AdviceRequired
    public void findExpiredRents() {
        List<Rental> rentals = rentalDao.findExpiredRentals();
        List<User> users = userDao.findAllById(rentals.stream().map(r -> r.getCustomerId()).collect(Collectors.toList()));
        jmsTemplate.convertAndSend("OrderTransactionQueue", users.get(0));
        System.out.println("Найдено " + rentals.size() + " не сданных фильмов");
        System.out.println("Отправка уведомлений пользователям " + users.stream().map(u -> u.getEmail()).collect(Collectors.toList()));
    }
}

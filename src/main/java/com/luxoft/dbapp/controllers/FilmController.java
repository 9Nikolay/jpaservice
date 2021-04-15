package com.luxoft.dbapp.controllers;

import com.luxoft.dbapp.dao.*;
import com.luxoft.dbapp.dao.security.UserDao;
import com.luxoft.dbapp.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class FilmController {

    FilmDao filmDao;
    InventoryDao inventoryDao;
    LanguageDao languageDao;
    RentalDao rentalDao;
    UserDao userDao;
    PaymentDao paymentDao;

    /**
     * Домашняя страница
     *
     * @return
     */
    @GetMapping(value = "/home")
    public String homePage() {
        return "home";
    }

    /**
     * Обработка запроса на просмотр имеющихся в наличии фильмов
     * Фильмы извлекаются из таблицы Inventory
     * Доступ возможен для пользователей с ролью ROLE_ADMIN
     * Доступные дейтсвия:
     * -добавление нового фильма
     * -просмотр пунктов выдачи дисков с данным фильмом
     * -удаление фильма
     *
     * @param model
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/admin_films/{pageNumber}")
    public String adminFilms(@PathVariable Integer pageNumber, Model model) {
        Pageable firstPageWithTwoElements = PageRequest.of(pageNumber - 1, 20);
        Page<Film> page = filmDao.findAvailabeFilms(firstPageWithTwoElements);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("allFilms", page.get().collect(Collectors.toCollection(ArrayList::new)));
        return "admin_films";
    }

    /**
     * Обработка запроса на добавление фильма
     *
     * @param model
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/add_film")
    public String addFilm(Model model) {
        List<Language> languages = (List) languageDao.findAll();
        Film film = new Film();
        film.setTitle("example title");
        film.setRentalDuration(1L);
        film.setRentalRate(BigDecimal.ONE);
        film.setReplacementCost(BigDecimal.TEN);
        film.setLastUpdate(LocalDateTime.now());
        film.setLanguageId(languages.get(0));
        film.setOriginalLanguageId(languages.get(0));
        model.addAttribute("film", film);
        model.addAttribute("languages", languages);
        return "add_film";
    }

    /**
     * Обработка запроса на сохранение фильма в БД
     *
     * @param film
     * @param model
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/save_film")
    public String saveFilm(Film film, Model model) {
        filmDao.save(film);
        model.addAttribute("title", film.getTitle());
        return "successful_save";
    }

    /**
     * Обработка запроса на удаление фильма
     *
     * @param film
     * @param model
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/delete_film")
    public String deleteFilm(@ModelAttribute("filmdel") Film film, Model model) {
        List<Payment> payments = paymentDao.findPaymentsByFilmId(film.getFilmId());
        List<Long> paymentIds = payments.stream().map(p -> p.getPaymentId()).collect(Collectors.toList());
        paymentIds.forEach(paymentId -> paymentDao.deleteById(paymentId));

        List<Rental> rentals = rentalDao.findRentalsByFilmId(film.getFilmId());
        List<Long> rentalIds = rentals.stream().map(p -> p.getRentalId()).collect(Collectors.toList());
        rentalIds.forEach(rentalId -> rentalDao.deleteById(rentalId));

        List<Inventory> inventories = inventoryDao.getAllByFilm_filmId(film.getFilmId());
        List<Long> inventoryIds = inventories.stream().map(p -> p.getInventoryId()).collect(Collectors.toList());
        inventoryIds.forEach(inventoryId -> inventoryDao.deleteById(inventoryId));

        model.addAttribute("title", film.getTitle());
        return "successful_deletion";
    }

    /**
     * Обработка запроса на отображение пунктов выдачи диска с фильмом
     *
     * @param film
     * @param model
     * @return
     */
    @PostMapping(value = "/show_film_stores")
    public String showStores(@ModelAttribute("filmFound") Film film, Model model) {
        List<Inventory> inventors = inventoryDao.getAvailableInventoriesByFilmId(film.getFilmId());
        model.addAttribute("inventors", inventors);
        return "film_stores";
    }

    /**
     * Обработка запроса на бронирование фильма пользователем
     *
     * @param inventory
     * @param model
     * @return
     */
    @RequestMapping("/rent_movie")
    public String rentMovie(@ModelAttribute("inventoryFound") Inventory inventory, Model model) {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setInventory(inventory);
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDao.findByUsername(user.getUsername()).get().getId();
        rental.setCustomerId(userId);
        rental.setReturnDate(null);
        rental.setLastUpdate(LocalDateTime.now());
        rental.setStaff(1L);
        rentalDao.save(rental);
        model.addAttribute("title", inventory.getInventoryId());
        return "successful_rent";
    }

    /**
     * Обработка запроса на отображение истории аренд фильмов пользователя
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/rents")
    public String showAllUserRents(Model model) {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDao.findByUsername(user.getUsername()).get().getId();
        List<Rental> rentals = rentalDao.findAllByCustomerId(userId);
        model.addAttribute("rents", rentals);
        return "rents";
    }


    /**
     * Постраничный вывод книг из Inventory
     *
     * @param pageId
     * @param model
     * @return
     */
    @GetMapping(value = "/films/{pageId}")
    public String userFilms(@PathVariable Integer pageId, Model model) {
        Pageable firstPageWithTwoElements = PageRequest.of(pageId - 1, 20);
        Page<Film> page = filmDao.findAvailabeFilms(firstPageWithTwoElements);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("allFilms", page.get().collect(Collectors.toCollection(ArrayList::new)));
        return "films";
    }

    /**
     * Обработка ошибок
     *
     * @param e
     * @param model
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @GetMapping("/error")
    public String errorHandler(RuntimeException e, Model model
    ) {
        e.printStackTrace();
        model.addAttribute("exception", e.getMessage());
        return "error_desc";
    }
}
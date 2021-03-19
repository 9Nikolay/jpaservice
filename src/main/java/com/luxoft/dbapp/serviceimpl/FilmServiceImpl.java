package com.luxoft.dbapp.serviceimpl;

import com.luxoft.dbapp.dao.LanguageDao;
import com.luxoft.dbapp.entities.Film;
import com.luxoft.dbapp.entities.FilmActor;
import com.luxoft.dbapp.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Repository
@Transactional
public class FilmServiceImpl implements FilmService {
    static final String ALL_SINGER_NATIVE_QUERY = "select f.description from film f join film_actor fa on f.film_id = fa.film_id";

    @Autowired
    private LanguageDao languageDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Film> findAllFilms() {
        return em.createNamedQuery(Film.FIND_ALL, Film.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> findAllWithAlbum() {
        List<Film> films = em.createNamedQuery(Film.FIND_ALL, Film.class).getResultList();
        return films;
    }

    @Transactional
    @Override
    public Film saveFilm(Film film) {
        transactionTemplate.execute(transactionStatus -> {
            if (film.getFilmId() == null) {
                em.persist(film);
            } else {
                em.merge(film);
            }

            return null;
        });
        return film;
    }

    @Transactional
    @Override
    public FilmActor saveFilmActor(FilmActor filmActor) {
        transactionTemplate.execute(transactionStatus -> {
            em.persist(filmActor);
            return null;
        });
        return filmActor;
    }

    @Override
    public void delete(Film film) {
        Film mergedContact = em.merge(film);
        em.remove(mergedContact);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY).getResultList();
    }
}

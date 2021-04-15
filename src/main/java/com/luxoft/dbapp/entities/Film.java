package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.converters.LocalDateTimeStampConverter;
import com.luxoft.dbapp.converters.RatingStringConverter;
import com.luxoft.dbapp.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Long filmId;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(name = "release_year")
    private Integer releaseYear;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language languageId;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguageId;
    @Column(name = "rental_duration", nullable = false)
    private Long rentalDuration;
    @Column(name = "rental_rate", nullable = false)
    private BigDecimal rentalRate;
    @Column
    private Long length;
    @Column(name = "replacement_cost", nullable = false)
    private BigDecimal replacementCost;
    @Convert(converter = RatingStringConverter.class)
    @Column
    private Rating rating;
    @Column(name = "special_features")
    private String specialFeatures;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
         boolean b = Objects.equals(filmId, film.filmId) && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(releaseYear, film.releaseYear) ;
         b = b && Objects.equals(languageId, film.languageId) && Objects.equals(originalLanguageId, film.originalLanguageId) && Objects.equals(rentalDuration, film.rentalDuration) && Objects.equals(rentalRate, film.rentalRate) && Objects.equals(length, film.length) && Objects.equals(replacementCost, film.replacementCost) && rating == film.rating && Objects.equals(specialFeatures, film.specialFeatures) && Objects.equals(lastUpdate, film.lastUpdate);
    return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description, releaseYear, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }
}

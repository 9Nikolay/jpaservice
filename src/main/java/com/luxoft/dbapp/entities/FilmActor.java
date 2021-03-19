package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.converter.LocalDateTimeStampConverter;
import com.luxoft.dbapp.converter.RatingAttributeConverter;
import com.luxoft.dbapp.keys.ActorFilmKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {

    @EmbeddedId
    private ActorFilmKey actorFilmKey;
    @Column
    Long category;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}

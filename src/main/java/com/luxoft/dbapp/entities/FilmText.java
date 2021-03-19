package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class FilmText implements Serializable {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
}

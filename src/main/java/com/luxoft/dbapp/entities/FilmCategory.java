package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.keys.FilmCategoryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class FilmCategory implements Serializable {
    @EmbeddedId
    private FilmCategoryKey filmCategoryKey;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

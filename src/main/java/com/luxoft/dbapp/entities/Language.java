package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.converters.LocalDateTimeStampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Language implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;
    @Column(name = "name")
    private String name;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        boolean b =languageId.equals(language.languageId) && name.equals(language.name) && lastUpdate.equals(language.lastUpdate);
        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, name, lastUpdate);
    }
}

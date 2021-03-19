package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name= Country.FIND_BY_NAME ,
                query="select c from Country c " +
                        "where c.country = :name")
})
public class Country implements Serializable {
    public static final String FIND_BY_NAME = "Country.findByName";
    @Id
    @Column(name = "country_id", nullable = false)
    private Short countryId;
    @Column(nullable = false)
    private String country;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

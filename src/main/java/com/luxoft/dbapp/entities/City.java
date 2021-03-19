package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class City implements Serializable {
    @Id
    @Column(name = "city_id", nullable = false)
    private Short cityId;
    @Column(name = "city", nullable = false)
    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

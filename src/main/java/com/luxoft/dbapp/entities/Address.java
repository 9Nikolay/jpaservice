package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Address implements Serializable {
    @Id
    @Column(name = "address_id", nullable = false)
    private Short addressId;
    @Column(nullable = false)
    private String address;
    @Column
    private String address2;
    @Column(nullable = false)
    private String district;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(nullable = false)
    private String phone;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

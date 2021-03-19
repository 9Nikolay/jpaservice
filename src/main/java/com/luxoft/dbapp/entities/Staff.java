package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Staff implements Serializable {
    @Id
    @Column(name = "staff_id")
    private Byte staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    private Date lastUpdate;
}

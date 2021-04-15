package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude="store")
@ToString(exclude = "store")
public class Staff implements Serializable {
    @Id
    @Column(name = "staff_id")
    private Byte staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "email")
    private String email;
    //@ToString.Exclude
    @ManyToOne
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

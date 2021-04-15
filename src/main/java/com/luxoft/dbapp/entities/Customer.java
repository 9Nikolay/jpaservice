package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(nullable = false)
    private Boolean active;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "last_update")
    private Date lastUpdate;
}

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
public class Rental implements Serializable {
    @Id
    @Column(name = "rental_id")
    private Integer rentalId;
    @Column(name = "rental_date")
    private Date rentalDate;
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @Column(name = "return_date")
    private Date returnDate;
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @Column(name = "last_update")
    private Date lastUpdate;
}

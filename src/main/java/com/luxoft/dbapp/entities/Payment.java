package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Payment implements Serializable {
    @Id
    @Column(name = "payment_id", nullable = false)
    private Short paymentId;
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
    @Column(name = "last_update")
    private Date lastUpdate;
}

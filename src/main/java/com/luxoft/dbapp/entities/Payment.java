package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.converters.LocalDateTimeStampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Payment implements Serializable {
    @Id
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

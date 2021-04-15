package com.luxoft.dbapp.entities;

import com.luxoft.dbapp.converters.LocalDateTimeStampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long rentalId;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "rental_date")
    private LocalDateTime rentalDate;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @Column(name = "customer_id")
    private Integer customerId;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @Column(name = "staff_id")
    private Long staff;
    @Convert(converter = LocalDateTimeStampConverter.class)
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

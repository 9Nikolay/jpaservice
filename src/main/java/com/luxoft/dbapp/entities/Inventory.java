package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Inventory implements Serializable {
    @Id
    @Column(name = "inventory_id", nullable = false)
    private Long inventoryId;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @Column(name = "store_id", nullable = false)
    private Long store;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

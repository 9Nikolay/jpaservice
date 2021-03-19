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
public class Inventory implements Serializable {
    @Id
    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

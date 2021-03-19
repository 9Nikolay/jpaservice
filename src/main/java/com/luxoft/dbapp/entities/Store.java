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
public class Store implements Serializable {
    @Id
    @Column(name = "store_id", nullable = false)
    private Byte storeId;
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaff;
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

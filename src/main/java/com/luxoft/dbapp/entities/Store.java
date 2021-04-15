package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Store implements Serializable {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long storeId;
    @OneToMany(mappedBy = "store")
    @Column(name = "manager_staff_id", nullable = false)
    private Set<Staff> managerStaff;
    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

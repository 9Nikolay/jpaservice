package com.luxoft.dbapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Category implements Serializable {
    @Id
    @Column(name = "category_id", nullable = false)
    private Byte categoryId;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}

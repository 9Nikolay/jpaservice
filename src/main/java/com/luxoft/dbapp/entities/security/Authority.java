package com.luxoft.dbapp.entities.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    Long id;

    String role;

    @Transient
    @ManyToMany(mappedBy = "authorities")
    Set<User> users;
}

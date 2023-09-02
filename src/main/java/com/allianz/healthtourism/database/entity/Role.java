package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;
    @Column
    private String description;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
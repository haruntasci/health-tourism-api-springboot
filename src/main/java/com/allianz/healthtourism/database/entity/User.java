package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column
    private boolean isEnable;


    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name="user_roles",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", nullable = false)}
    )
    private Set<Role> roles;
    public User() {
        isEnable = true;
    }

}
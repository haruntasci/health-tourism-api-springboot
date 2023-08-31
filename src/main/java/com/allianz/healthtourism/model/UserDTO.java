package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.Role;
import com.allianz.healthtourism.util.BaseDTO;
import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;


@Data
public class UserDTO extends BaseDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private boolean isEnable;

    private Set<Role> roles;
}
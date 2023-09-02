package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.Role;
import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;

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
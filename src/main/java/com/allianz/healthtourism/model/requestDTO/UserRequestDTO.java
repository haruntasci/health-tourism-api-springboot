package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;


@Data
public class UserRequestDTO extends BaseRequestDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

}

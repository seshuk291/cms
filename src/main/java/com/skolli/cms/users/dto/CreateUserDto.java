package com.skolli.cms.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long role;
}

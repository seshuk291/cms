package com.skolli.cms.users.dto;

import com.skolli.cms.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Roles role;
}

package com.skolli.cms.users.dto;

public record UpdateUserDto(
        String userName,
        String firstName,
        String lastName,
        String email,
        Long roleId
) {}

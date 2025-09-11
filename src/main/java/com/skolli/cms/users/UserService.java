package com.skolli.cms.users;

import com.skolli.cms.common.custom_exceptions.RoleNotFoundException;
import com.skolli.cms.common.custom_exceptions.UserCreationException;
import com.skolli.cms.common.custom_exceptions.UserNotFoundException;
import com.skolli.cms.roles.Roles;
import com.skolli.cms.roles.RolesRepository;
import com.skolli.cms.users.dto.CreateUserDto;
import com.skolli.cms.users.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public Users findUser(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    public List<UsersDto> findAllUser() {
        try {
            return this.userRepository
                    .findAll()
                    .stream()
                    .map(user -> UsersDto.builder()
                            .id(user.getId())
                            .userName(user.getUserName())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .build())
                    .collect(Collectors.toList());
        } catch(Exception exception) {
            throw new UserNotFoundException("Users not found " + exception.getMessage());
        }

    }

    public CreateUserDto createUser(CreateUserDto createUserDto) {
        Roles role = rolesRepository.findById(createUserDto.getRole()).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        try {
            Users user = Users.builder()
                    .userName(createUserDto.getUserName())
                    .firstName(createUserDto.getFirstName())
                    .lastName(createUserDto.getLastName())
                    .email(createUserDto.getEmail())
                    .role(role)
                    .passwordHash(createUserDto.getPassword())
                    .isActive(false)
                    .build();
            this.userRepository.save(user);
        } catch(Exception exception) {
            throw new UserCreationException("Error creating user " + exception.getMessage());
        }
        return createUserDto;
    }
}

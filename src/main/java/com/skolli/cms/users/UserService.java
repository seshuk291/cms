package com.skolli.cms.users;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.roles.Roles;
import com.skolli.cms.roles.RolesRepository;
import com.skolli.cms.users.dto.CreateUserDto;
import com.skolli.cms.users.dto.UpdateUserDto;
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
                    .map(this::mapUserToDto)
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

    public Boolean updateUser(Long userId, UpdateUserDto updateUserDto) {
        Users user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found "));
        user.setUserName(updateUserDto.userName());
        user.setFirstName(updateUserDto.firstName());
        user.setLastName(updateUserDto.lastName());
        user.setEmail(updateUserDto.email());

        try {
            this.userRepository.save(user);
            return true;
        } catch (Exception exception) {
            throw new UserUpdateException("Unable to update the user " + exception.getMessage());
        }
    }

    public Boolean deleteUser(Long userId) {
        try {
            this.userRepository.deleteById(userId);
            return true;
        } catch(Exception exception) {
            throw new UserDeletionException("Unable to delete the user " + exception.getMessage());
        }
    }

    public UsersDto getUserById(Long userId) {
        Users user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapUserToDto(user);
    }

    private UsersDto mapUserToDto(Users user) {
        return UsersDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

package com.skolli.cms.roles;

import com.skolli.cms.common.custom_exceptions.*;
import com.skolli.cms.roles.dto.CreateRoleDto;
import com.skolli.cms.roles.dto.RoleItemDto;
import com.skolli.cms.roles.dto.UpdateRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesService {
    
    private final RolesRepository rolesRepository;

    public List<RoleItemDto> findAllRoles() {
        return this.rolesRepository.findAll()
                .stream()
                .map(this::mapRoleToDto)
                .collect(Collectors.toList());
    }

    public RoleItemDto createRole(CreateRoleDto roleDto) {
        // Check if role with the same name already exists
        Roles existingRole = this.rolesRepository.findByName(roleDto.getName());
        if (existingRole != null) {
            throw new DuplicateRoleException("Role with name '" + roleDto.getName() + "' already exists");
        }

        try {
            Roles role = new Roles();
            role.setName(roleDto.getName());
            Roles savedRole = this.rolesRepository.save(role);
            return mapRoleToDto(savedRole);
        } catch (Exception exception) {
            throw new RoleCreationException("Unable to create role: " + exception.getMessage());
        }
    }

    public RoleItemDto getRoleById(Long roleId) {
        Roles role = this.rolesRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));
        return mapRoleToDto(role);
    }

    public RoleItemDto getRoleByName(String name) {
        Roles role = this.rolesRepository.findByName(name);
        if (role == null) {
            throw new RoleNotFoundException("Role with name '" + name + "' not found");
        }
        return mapRoleToDto(role);
    }

    public Boolean updateRole(Long roleId, UpdateRoleDto roleDto) {
        Roles role = this.rolesRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));

        // Check if another role with the same name already exists
        Roles existingRole = this.rolesRepository.findByName(roleDto.getName());
        if (existingRole != null && !existingRole.getId().equals(roleId)) {
            throw new DuplicateRoleException("Role with name '" + roleDto.getName() + "' already exists");
        }

        try {
            role.setName(roleDto.getName());
            this.rolesRepository.save(role);
            return true;
        } catch (Exception exception) {
            throw new RoleUpdateException("Unable to update role: " + exception.getMessage());
        }
    }

    public Boolean deleteRole(Long roleId) {
        Roles role = this.rolesRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));

        // Check if role is being used by any users
        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new RoleDeletionException("Cannot delete role as it is assigned to " + role.getUsers().size() + " user(s)");
        }

        try {
            this.rolesRepository.deleteById(roleId);
            return true;
        } catch (Exception exception) {
            throw new RoleDeletionException("Unable to delete role: " + exception.getMessage());
        }
    }

    private RoleItemDto mapRoleToDto(Roles role) {
        return RoleItemDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}

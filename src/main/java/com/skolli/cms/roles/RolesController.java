package com.skolli.cms.roles;

import com.skolli.cms.roles.dto.CreateRoleDto;
import com.skolli.cms.roles.dto.RoleItemDto;
import com.skolli.cms.roles.dto.UpdateRoleDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleItemDto>>> getAllRoles() {
        List<RoleItemDto> roles = this.rolesService.findAllRoles();
        return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleItemDto>> getRoleById(@PathVariable Long roleId) {
        RoleItemDto role = this.rolesService.getRoleById(roleId);
        return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
    }

    @GetMapping("/name/{roleName}")
    public ResponseEntity<ApiResponse<RoleItemDto>> getRoleByName(@PathVariable String roleName) {
        RoleItemDto role = this.rolesService.getRoleByName(roleName);
        return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleItemDto>> createRole(@RequestBody CreateRoleDto roleDto) {
        RoleItemDto createdRole = this.rolesService.createRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Role created successfully", createdRole));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse<Boolean>> updateRole(
            @PathVariable Long roleId,
            @RequestBody UpdateRoleDto roleDto
    ) {
        Boolean updated = this.rolesService.updateRole(roleId, roleDto);
        return ResponseEntity.ok(ApiResponse.success("Role updated successfully", updated));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteRole(@PathVariable Long roleId) {
        Boolean deleted = this.rolesService.deleteRole(roleId);
        return ResponseEntity.ok(ApiResponse.success("Role deleted successfully", deleted));
    }
}
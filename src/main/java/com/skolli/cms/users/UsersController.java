package com.skolli.cms.users;

import com.skolli.cms.users.dto.CreateUserDto;
import com.skolli.cms.users.dto.UpdateUserDto;
import com.skolli.cms.users.dto.UsersDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsersDto>>> getAllUsers() {
        List<UsersDto> users = this.userService.findAllUser();
        return ResponseEntity.ok(ApiResponse.success("Success", users));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UsersDto>> getUserById(@PathVariable Long userId) {
        UsersDto user = this.userService.getUserById(userId);
        return ResponseEntity.ok(ApiResponse.success("Success", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateUserDto>> createUser(@RequestBody CreateUserDto createUserDto) {
        CreateUserDto createdUser = this.userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Success", createdUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Boolean>> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserDto updateUserDto
    ) {
        Boolean updated = this.userService.updateUser(userId, updateUserDto);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable Long userId) {
        Boolean deleted = this.userService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", deleted));
    }
}

package com.skolli.cms.users;

import com.skolli.cms.users.dto.CreateUserDto;
import com.skolli.cms.users.dto.UsersDto;
import com.skolli.cms.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        return ResponseEntity.ok().body(ApiResponse.success("Success", users));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateUserDto>> createUser(@RequestBody CreateUserDto createUserDto) {
        CreateUserDto createdUser = this.userService.createUser(createUserDto);
        return ResponseEntity.ok().body(ApiResponse.success("Success", createdUser));
    }
}

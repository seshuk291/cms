package com.skolli.cms.users;

import com.skolli.cms.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @GetMapping
    public ResponseEntity<ApiResponse<List<String>>> getAllUsers() {
        try {
            return ResponseEntity.ok().body(ApiResponse.success("Success",List.of("john", "bob")));
        } catch(Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(500).body(ApiResponse.error("Failed to retrieve users", "USER_FETCH_ERROR"));
        }
    }
}

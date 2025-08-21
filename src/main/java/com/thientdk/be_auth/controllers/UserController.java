package com.thientdk.be_auth.controllers;

import com.thientdk.be_auth.models.responses.UserResponse;
import com.thientdk.be_auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //TODO: Throw exception with authorization 401 and 403
    //TODO: Login response
//    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @GetMapping
    public Page<UserResponse> getUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "") String keySearch,
            Pageable pageable

    ) {
        return userService.getUsers(page, size, keySearch, pageable);
    }
}

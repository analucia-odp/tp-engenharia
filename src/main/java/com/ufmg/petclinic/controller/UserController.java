package com.ufmg.petclinic.controller;

import com.ufmg.petclinic.model.User;
import com.ufmg.petclinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@Validated @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Object> get(@PathVariable("userId") UUID id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path ="{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") UUID id) {
        return userService.delete(id);
    }

    @PutMapping(path ="{userId}")
    public ResponseEntity<String> update(@PathVariable("userId") UUID id,
                                         @RequestBody User newUser) {
        return userService.update(id, newUser);
    }
}

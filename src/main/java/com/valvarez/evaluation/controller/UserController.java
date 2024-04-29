package com.valvarez.evaluation.controller;

import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;
import com.valvarez.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDtoResponse> saveUser(@Valid @RequestBody UserDto userRequest) throws Exception {
        return new ResponseEntity<>(this.userService.createUser(userRequest), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(
            @Valid @RequestBody UserDto userRequest,
            @PathVariable("id") UUID id) throws Exception {
        return new ResponseEntity<>(this.userService.updateUser(id, userRequest), OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDtoResponse>> allUsers() throws Exception {
        return new ResponseEntity<>(this.userService.getAllUsers(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> allUsers(@PathVariable("id") UUID id) throws Exception {
        return new ResponseEntity<>(this.userService.getUsersById(id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) throws Exception {
        return ResponseEntity.ok("Usuario eliminado");
    }

}

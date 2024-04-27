package com.valvarez.evaluation.controller;

import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.payload.dto.UserDto;
import com.valvarez.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("save")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userRequest) throws Exception {
        return new ResponseEntity<>(this.userService.createUser(userRequest), CREATED) ;
    }

}
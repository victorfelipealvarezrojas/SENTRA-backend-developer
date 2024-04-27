package com.valvarez.evaluation.service;

import com.valvarez.evaluation.payload.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user) throws Exception;
}

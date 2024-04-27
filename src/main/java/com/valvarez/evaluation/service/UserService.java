package com.valvarez.evaluation.service;

import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;

public interface UserService {
    UserDtoResponse createUser(UserDto user) throws Exception;
}

package com.valvarez.evaluation.service;

import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDtoResponse createUser(UserDto user) throws Exception;

    UserDtoResponse updateUser(UUID id, UserDto user) throws Exception;

    List<UserDtoResponse> getAllUsers() throws Exception;

    UserDtoResponse getUsersById(UUID id) throws Exception;

    void deleteUser(UUID id) throws Exception;
}

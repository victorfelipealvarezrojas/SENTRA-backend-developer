package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.exception.BlogApiException;
import com.valvarez.evaluation.exception.ResourceNotFoundException;
import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;
import com.valvarez.evaluation.payload.mapper.DtoMapper;
import com.valvarez.evaluation.repository.UserRepository;
import com.valvarez.evaluation.security.JwtTokenProvider;
import com.valvarez.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final DtoMapper dtoMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository, BCryptPasswordEncoder cryptPasswordEncoder,
            JwtTokenProvider jwtTokenProvider, DtoMapper dtoMapper
    ) {
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.dtoMapper = dtoMapper;
    }


    @Override
    public UserDtoResponse createUser(UserDto userDto) throws Exception {
        try {

            if (userRepository.existsByName(userDto.getName()))
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "El Nombre de usuario ya está registrado");

            if (userRepository.existsByEmail(userDto.getEmail()))
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");

            String pass = cryptPasswordEncoder.encode(userDto.getPassword());
            String token = jwtTokenProvider.generateToken(userDto);

            User userDtoToUser = this.dtoMapper.mapToEntity(userDto, pass, token);
            User newUser = this.userRepository.save(userDtoToUser);

            return this.dtoMapper.mapToDtoResponse(newUser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserDtoResponse updateUser(UUID id, UserDto userDto) throws Exception {
        User usuario = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", id));

        String pass = cryptPasswordEncoder.encode(userDto.getPassword());
        usuario.setActive(true);
        usuario.setName(userDto.getName());
        usuario.setEmail(userDto.getEmail());
        usuario.setUpdatedAt(LocalDateTime.now());
        usuario.setPassword(pass);
        return this.dtoMapper.mapToDtoResponse(this.userRepository.save(usuario));
    }

    @Override
    public List<UserDtoResponse> getAllUsers() throws Exception {
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) return null;
        return this.dtoMapper.mapToDtoResponseList(users);
    }

    @Override
    public UserDtoResponse getUsersById(UUID id) throws Exception {
        User user = this.userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario", "id", id));
        return this.dtoMapper.mapToDtoResponse(user);
    }

    @Override
    public void deleteUser(UUID id) throws Exception {
        User usuario = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario", "id", id));
        this.userRepository.delete(usuario);
    }
}

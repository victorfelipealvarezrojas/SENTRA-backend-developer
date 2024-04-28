package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.Phone;
import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.exception.BlogApiException;
import com.valvarez.evaluation.payload.dto.in.PhoneDto;
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

    public static class PhoneMapper {

        public static Phone mapPhoneDtoToPhone(PhoneDto phoneDto) {
            return Phone.builder()
                    .number(phoneDto.getNumber())
                    .citycode(phoneDto.getCitycode())
                    .contrycode(phoneDto.getContrycode())
                    .build();
        }

        public static PhoneDto mapPhoneToPhoneDto(Phone phone) {
            return PhoneDto.builder()
                    .number(phone.getNumber())
                    .citycode(phone.getCitycode())
                    .contrycode(phone.getContrycode())
                    .build();
        }
    }

}

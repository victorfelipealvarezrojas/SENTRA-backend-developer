package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.Phone;
import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.payload.dto.in.PhoneDto;
import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;
import com.valvarez.evaluation.repository.UserRepository;
import com.valvarez.evaluation.security.JwtTokenProvider;
import com.valvarez.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder cryptPasswordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDtoResponse createUser(UserDto userDto) throws Exception {
        try {

            User userDtoToUserMapper = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(cryptPasswordEncoder.encode(userDto.getPassword()))
                    .token(jwtTokenProvider.generateToken(userDto))
                    .isActive(true)
                    .phones(userDto.getPhones().stream()
                            .map(PhoneMapper::mapPhoneDtoToPhone)
                            .collect(Collectors.toList()))
                    .build();

            User newUser = this.userRepository.save(userDtoToUserMapper);
            return UserDtoResponse.builder()
                    .id(newUser.getId())
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .createdAt(newUser.getCreatedAt())
                    .updatedAt(newUser.getUpdatedAt())
                    .lastLogin(newUser.getLastLogin())
                    .isActive(newUser.isActive())
                    .token(newUser.getToken())
                    .phones(newUser.getPhones().stream()
                            .map(PhoneMapper::mapPhoneToPhoneDto)
                            .collect(Collectors.toList()))
                    .build();

        }catch (Exception e){
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

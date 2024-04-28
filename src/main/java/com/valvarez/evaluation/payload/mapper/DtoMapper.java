package com.valvarez.evaluation.payload.mapper;

import com.valvarez.evaluation.entity.Phone;
import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.payload.dto.in.PhoneDto;
import com.valvarez.evaluation.payload.dto.in.UserDto;
import com.valvarez.evaluation.payload.dto.out.UserDtoResponse;
import com.valvarez.evaluation.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoMapper {
    public User mapToEntity(UserDto userDto, String password, String token) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(password)
                .token(token)
                .isActive(true)
                .phones(userDto.getPhones().stream()
                        .map(this::mapPhoneDtoToPhone)
                        .collect(Collectors.toList()))
                .build();
    }

    public UserDtoResponse mapToDtoResponse(User user){
        return UserDtoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .isActive(user.isActive())
                .token(user.getToken())
                .phones(user.getPhones().stream()
                        .map(this::mapPhoneToPhoneDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Phone mapPhoneDtoToPhone(PhoneDto phoneDto) {
        return Phone.builder()
                .number(phoneDto.getNumber())
                .citycode(phoneDto.getCitycode())
                .contrycode(phoneDto.getContrycode())
                .build();
    }

    public PhoneDto mapPhoneToPhoneDto(Phone phone) {
        return PhoneDto.builder()
                .number(phone.getNumber())
                .citycode(phone.getCitycode())
                .contrycode(phone.getContrycode())
                .build();
    }
}


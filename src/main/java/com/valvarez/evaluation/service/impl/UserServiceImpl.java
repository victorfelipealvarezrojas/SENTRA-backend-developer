package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.Phone;
import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.payload.dto.PhoneDto;
import com.valvarez.evaluation.payload.dto.UserDto;
import com.valvarez.evaluation.repository.UserRepository;
import com.valvarez.evaluation.service.UserService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Constructor para UserServiceImpl.
     *
     * @param userRepository Un repositorio para interactuar con entidades de tipo User Entity.
     * @param userRepository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        try {

            User userDtoToUserMapper = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .phones(userDto.getPhones().stream()
                            .map(PhoneMapper::mapPhoneDtoToPhone)
                            .collect(Collectors.toList()))
                    .build();

            User newUser = this.userRepository.save(userDtoToUserMapper);
            return UserDto.builder()
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .password(newUser.getPassword())
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

package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.repository.UserRepository;
import com.valvarez.evaluation.service.UserService;
import org.springframework.stereotype.Service;

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
    public User createUser(User user) throws Exception {
        try {
            return this.userRepository.save(user);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}

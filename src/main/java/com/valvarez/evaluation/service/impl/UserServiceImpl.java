package com.valvarez.evaluation.service.impl;

import com.valvarez.evaluation.entity.User;
import com.valvarez.evaluation.repository.UserRepository;
import com.valvarez.evaluation.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}

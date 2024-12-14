package com.ufmg.petclinic.service;

import com.ufmg.petclinic.model.User;
import com.ufmg.petclinic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user){
        return userRepository.create(user);
    }
}

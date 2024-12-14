package com.ufmg.petclinic.service;

import com.ufmg.petclinic.model.User;
import com.ufmg.petclinic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user){
        return userRepository.create(user);
    }

    public ResponseEntity<String> delete(UUID id){
        var userDelete = userRepository.getUserById(id);
        if (userDelete.isPresent()){
            userRepository.delete(userDelete.get());
            return ResponseEntity.ok("User Deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
        }
    }

    public ResponseEntity<Object> getUserById(UUID id)
    {
        var user = userRepository.getUserById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
        }
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public ResponseEntity<String> update(UUID id, User newUser){
        var oldUser = userRepository.getUserById(id);
        if(oldUser.isPresent()){
            userRepository.update(oldUser.get(), newUser);
            return ResponseEntity.ok("User updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
        }
    }
}

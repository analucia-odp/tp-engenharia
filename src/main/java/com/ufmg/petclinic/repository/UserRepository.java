package com.ufmg.petclinic.repository;

import com.ufmg.petclinic.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User create(User user) {
        users.add(user);
        return user;
    }

    public void delete(User user) {
        users.remove(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(UUID id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public void update(User user, User newUser) {
        users.set(users.indexOf(user), newUser);
    }
}

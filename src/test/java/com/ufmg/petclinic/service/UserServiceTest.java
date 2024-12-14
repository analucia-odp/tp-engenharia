package com.ufmg.petclinic.service;

import com.ufmg.petclinic.model.Animal;
import com.ufmg.petclinic.model.User;
import com.ufmg.petclinic.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("should create user when valid input")
    void shouldCreateUserWhenValidInput() {
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);

        User user = new User("teste",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);

        when(userRepository.create(user)).thenReturn(user);

        User createdUser = userService.create(user);

        assertNotNull(createdUser);
        verify(userRepository, times(1)).create(user);
    }

    @Test
    @DisplayName("should not create user when invalid input")
    void shouldCreateUserWhenInValidInput() {
        User user = null;
        when(userRepository.create(user)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        verify(userRepository, times(1)).create(user);
    }

    @Test
    @DisplayName("should get user by Id when exist")
    void shouldGetUserByIdWhenExist() {
        UUID userId = UUID.randomUUID();
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);

        User user = new User("teste",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));

        var response = userService.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).getUserById(userId);
    }

    @Test
    @DisplayName("should not get user by Id when does not exist")
    void shouldNotGetUserByIdWhenDoesNotExist() {
        UUID userId = UUID.randomUUID();

        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        var response = userService.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userRepository, times(1)).getUserById(userId);
    }

    @Test
    @DisplayName("should delete user when exist")
    void shouldDeleteUserWhenExist() {
        UUID userId = UUID.randomUUID();
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);

        User user = new User("teste",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));

        var response = userService.delete(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @DisplayName("should not delete user when does not exist")
    void shouldNotDeleteUserWhenDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        var response = userService.delete(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userRepository, never()).delete(any());
    }

    @Test
    @DisplayName("should get all users")
    void shouldGetAllUsers() {
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);

        User user = new User("teste",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);
        when(userRepository.getAllUsers()).thenReturn(List.of(user));

        var users = userService.getAllUsers();

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("should return empty user list when not users")
    void shouldReturnEmptyUserListWhenNotUsers() {
        when(userRepository.getAllUsers()).thenReturn(Collections.emptyList());

        List<User> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
        verify(userRepository, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("should update user when exist")
    void shouldUpdateUserWhenExist() {
        UUID userId = UUID.randomUUID();
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);

        User oldUser = new User("teste",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);

        User newUser = new User("teste-update",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(oldUser));

        var response = userService.update(userId, newUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).update(oldUser, newUser);
    }

    @Test
    @DisplayName("should not update user when not exist")
    void shouldNotUpdateUserWhenNotExist() {
        UUID userId = UUID.randomUUID();
        Animal animal = new Animal("animal-teste",
                "gato",
                2,
                17);
        User newUser = new User("teste-update",
                "teste.email@gmail.com",
                "548.173.350-17",
                "12345",
                "Rua teste",
                animal);
        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        var response = userService.update(userId, newUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userRepository, never()).update(any(), any());
    }
}

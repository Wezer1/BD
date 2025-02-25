package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public List<UserDTO> getAllUsers() {
        log.info("Get all Users");
        if (userRepository.findAll().isEmpty()) {
            throw new NoSuchException("No users");
        }
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO getUserById(Integer userId) {
        log.info("Get client by id: {} ", userId);
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchException("There is no user with ID = " + userId + " in DB")));
        return userMapper.toDto(userOptional.get());
    }


//    @Transactional
//    public User registerCli(User user) {
//        // Проверка, существует ли уже пользователь с таким email
//        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
//            throw new NoSuchException("User with email " + client.getEmail() + " already exists.");
//        }
//
//        // Хеширование пароля
//        client.setPassword(passwordEncoder.encode(client.getPassword()));
//
//        // Сохранение пользователя
//        return clientRepository.save(client);
//    }

    @Transactional
    public void deleteUser(Integer userId) {
        log.info("Delete user");
        if (userRepository.findById(userId).isEmpty()) {
            throw new NoSuchException("There is no user with ID = " + userId + " in Database");
        }

        userRepository.deleteById(userId);
    }

    @Transactional
    public UserDTO changeUser(Integer userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NoSuchException("There is no user with ID = " + userId + " in Database");
        } else {
            User existingUser = optionalUser.get();
            User updatedUser = userMapper.toEntity(userDTO);
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setName(updatedUser.getName());
            existingUser.setRole(updatedUser.getRole());

            return userMapper.toDto(userRepository.save(existingUser));
        }
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        log.info("Saving user: {}", userDTO);
        User savedUser = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(savedUser);
    }
}

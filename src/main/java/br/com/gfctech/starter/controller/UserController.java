package br.com.gfctech.starter.controller;

import br.com.gfctech.starter.dto.UserDTO;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers()
                                         .stream()
                                         .map(this::convertToDTO)
                                         .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserEntity user = userService.findUserById(id);
        return ResponseEntity.ok(convertToDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserEntity newUser = userService.createUser(convertToEntity(userDTO));
        return ResponseEntity.ok(convertToDTO(newUser));
    }

    // Métodos auxiliares para conversão entre UserEntity e UserDTO
    private UserDTO convertToDTO(UserEntity user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
                           user.getDateBirth(), user.getProfilePicture(),
                           user.getBio(), user.getDateJoined(), user.getLastLogin(),
                           user.getStatus());
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.getId(), userDTO.getUsername(), null, userDTO.getEmail(),
                              userDTO.getDateBirth(), userDTO.getProfilePicture(),
                              userDTO.getBio(), userDTO.getDateJoined(), userDTO.getLastLogin(),
                              userDTO.getStatus(), null, null);
    }
}

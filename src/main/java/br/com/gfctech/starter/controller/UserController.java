package br.com.gfctech.starter.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.dto.UserDTO;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.service.UserService;

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

    @PostMapping("/newuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserEntity newUser = userService.createUser(convertToEntity(userDTO));
        return ResponseEntity.ok(convertToDTO(newUser));
    }

    // Atualizar perfil do usuário
    @PutMapping("/{id}/profile")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserEntity updatedUser = userService.updateProfile(id, userDTO.getProfilePicture(), userDTO.getBio());
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    // Métodos auxiliares para conversão entre UserEntity e UserDTO
    private UserDTO convertToDTO(UserEntity user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(),user.getPassword(),
                           user.getDateBirth(), user.getProfilePicture(),
                           user.getBio(), user.getDateJoined(), user.getLastLogin(),
                           user.getStatus());
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity(); // Construtor padrão
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setDateBirth(userDTO.getDateBirth());
        userEntity.setProfilePicture(userDTO.getProfilePicture());
        userEntity.setBio(userDTO.getBio());
        userEntity.setDateJoined(userDTO.getDateJoined());
        userEntity.setLastLogin(userDTO.getLastLogin());
        userEntity.setStatus(userDTO.getStatus());
        userEntity.setPassword(userDTO.getPassword()); // Adicione esta linha

        return userEntity;
    }
    
}

package br.com.gfctech.starter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    // Método para buscar todos os usuários
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    // Método para buscar usuário por ID
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Método para criar um novo usuário
    public UserEntity createUser(UserEntity user) {
        // Aqui você pode adicionar lógica para validar ou processar o usuário antes de salvar
        return userRepository.save(user);
    }

    // Método para atualizar o perfil do usuário
    public UserEntity updateProfile(Long id, String newProfilePicture, String newBio) {
        UserEntity user = findUserById(id);
        user.updateProfile(newProfilePicture, newBio);
        return userRepository.save(user);
    }
}

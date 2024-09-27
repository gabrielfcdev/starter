package br.com.gfctech.starter.repository;

import java.util.List; // Importação correta
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfctech.starter.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    
    // Método para buscar usuários por ID do grupo
    List<UserEntity> findByGroupId(Long groupId);
}

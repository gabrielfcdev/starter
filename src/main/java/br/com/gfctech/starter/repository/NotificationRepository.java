package br.com.gfctech.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gfctech.starter.entity.NotificationEntity;
import br.com.gfctech.starter.entity.UserEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>{

    //Buscar todas as notificações do usuário
    List<NotificationEntity> findByUserOrderByCreatedAtDesc(UserEntity user);

    //Contar notificações não lidas
    long countByUserAndIsReadFalse(UserEntity user);
    
}

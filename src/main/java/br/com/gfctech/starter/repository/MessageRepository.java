package br.com.gfctech.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfctech.starter.entity.MessageEntity;
import br.com.gfctech.starter.entity.UserEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByReceiver(UserEntity receiver);
}

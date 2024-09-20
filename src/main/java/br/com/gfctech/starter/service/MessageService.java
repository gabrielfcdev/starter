package br.com.gfctech.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.entity.MessageEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.MessageRepository;
import br.com.gfctech.starter.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    // Enviar mensagem
    @Transactional
    public void sendMessage(Long senderId, Long receiverId, String content) {
        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        UserEntity receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        MessageEntity message = new MessageEntity();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setRead(false); // Inicialmente, a mensagem é não lida

        messageRepository.save(message);
    }

    // Listar mensagens de um usuário (inbox)
    public List<MessageEntity> getMessagesForUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return messageRepository.findByReceiver(user);
    }

    // Marcar mensagem como lida
    @Transactional
    public void markMessageAsRead(Long messageId) {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        
        message.setRead(true);
        messageRepository.save(message);
    }

    // Deletar mensagem
    @Transactional
    public void deleteMessage(Long messageId) {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        
        messageRepository.delete(message);
    }
}

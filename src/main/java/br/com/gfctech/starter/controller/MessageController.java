package br.com.gfctech.starter.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.dto.MessageDTO;
import br.com.gfctech.starter.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Enviar mensagem
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(
            @RequestParam Long senderId, 
            @RequestParam Long receiverId, 
            @RequestParam String content) {
        
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();  // Validação de conteúdo
        }

        messageService.sendMessage(senderId, receiverId, content);
        return ResponseEntity.status(201).build();  // Alterado para 201 Created
    }

    // Obter mensagens de um usuário (Inbox)
    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<MessageDTO>> getMessagesForUser(@PathVariable Long userId) {
        List<MessageDTO> messages = messageService.getMessagesForUser(userId)
            .stream()
            .map(message -> new MessageDTO(
                message.getId(), 
                message.getSender().getId(), 
                message.getReceiver().getId(), 
                message.getContent(), 
                message.getCreatedAt(),  // Ajuste de timestamp para createdAt
                message.getIsRead()))   // Ajuste para o getter correto
            .collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }

    // Marcar mensagem como lida
    @PutMapping("/read/{messageId}")
    public ResponseEntity<Void> markMessageAsRead(@PathVariable Long messageId) {
        messageService.markMessageAsRead(messageId);
        return ResponseEntity.ok().build();
    }

    // Deletar mensagem
    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}

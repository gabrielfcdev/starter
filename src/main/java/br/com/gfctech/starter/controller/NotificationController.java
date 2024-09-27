package br.com.gfctech.starter.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.dto.NotificationDTO;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.UserRepository;
import br.com.gfctech.starter.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository; // Injetando o repositório de usuários


    // Acessar as notificações do usuário
    @GetMapping("/inbox/{userId}")
public ResponseEntity<List<NotificationDTO>> getNotificationsForUser(@PathVariable Long userId) {
    // Busque o UserEntity usando o ID
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found")); // Lidar com o caso de usuário não encontrado

    List<NotificationDTO> notifications = notificationService.getNotificationsForUser(user)
        .stream()
        .map(notification -> new NotificationDTO(
            notification.getId(), 
            notification.getType(),
            notification.getContent(),
            notification.getCreatedAt(),
            notification.getIsRead(),
            notification.getTimestamp(),
            notification.getUrl()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(notifications);
}



    // Marcar notificação como visualizada
    @PutMapping("/read/{notificationId}")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok().build();
    }

    // Deletar notificação
    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }

    // Entrar na notificação (clicar e redirecionar)
    @GetMapping("/view/{notificationId}")
    public ResponseEntity<String> viewNotification(@PathVariable Long notificationId) {
        String url = notificationService.getNotificationUrl(notificationId); // Retorna a URL da notificação
        notificationService.markNotificationAsRead(notificationId); // Marcar como lida ao clicar
        return ResponseEntity.ok(url); // Retorna a URL para o front-end redirecionar
    }
}

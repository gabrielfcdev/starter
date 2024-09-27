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
import br.com.gfctech.starter.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Acessar as notificações do usuário
    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsForUser(@PathVariable UserEntity user) {
        List<NotificationDTO> notifications = notificationService.getNotificationsForUser(user)
            .stream()
            .map(notification -> new NotificationDTO(
                notification.getId(), 
                notification.getSender().getId(), 
                notification.getReceiver().getId(), 
                notification.getContent(),
                notification.getTimestamp(),
                notification.getIsRead(),
                notification.getUrl())) // Adiciona a URL da notificação para redirecionamento
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

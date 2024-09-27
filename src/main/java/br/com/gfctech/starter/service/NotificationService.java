package br.com.gfctech.starter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.entity.NotificationEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Buscar notificações de um usuário
    public List<NotificationEntity> getNotificationsForUser(UserEntity user) {
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    // Marcar uma notificação como lida
    @Transactional
    public void markNotificationAsRead(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    // Deletar uma notificação
    @Transactional
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    // Retornar a URL da notificação para redirecionar o usuário
    public String getNotificationUrl(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId).orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada"));
        return "/posts/" + notification.getRelatedPost().getId(); // Exemplo: URL para redirecionar ao post relacionado
    }
}

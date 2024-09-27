package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String type; // Tipo da notificação, pode ser algo como "like", "comment", etc.
    private UserDTO user; // O usuário que recebeu a notificação
    private PostDTO relatedPost; // O post relacionado à notificação, se aplicável
    private UserDTO relatedUser; // O usuário relacionado à notificação, se aplicável (ex: quem curtiu ou comentou)
    private LocalDateTime createdAt; // Corrigido o nome para createdAt
    private Boolean isRead; // Corrigido o nome para isRead, representando se a notificação foi lida ou não
}

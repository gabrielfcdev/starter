package br.com.gfctech.starter.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToOne
    private UserEntity sender; // Enviador da notificação

    @ManyToOne
    private UserEntity receiver; // Recebedor da notificação

    @ManyToOne
    private PostEntity relatedPost;

    @ManyToOne
    private UserEntity relatedUser;

    @Column(nullable = false) // Não permite nulos
    private String content; // Conteúdo da notificação

    @Column(nullable = false) // Não permite nulos
    private LocalDateTime createdAt; // Data de criação da notificação

    @Column(nullable = false) // Não permite nulos
    private Boolean isRead; // Indica se a notificação foi lida

    @Column(nullable = false) // Não permite nulos
    private LocalDateTime timestamp; // Timestamp da notificação

    @Column(nullable = false) // Não permite nulos
    private String url; // URL para redirecionamento

}

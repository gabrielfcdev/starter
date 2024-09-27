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
    private UserEntity sender; // Mudei para 'sender', para indicar quem enviou a notificação

    @ManyToOne
    private UserEntity receiver; // Mudei para 'receiver', para indicar quem recebe a notificação

    @ManyToOne
    private PostEntity relatedPost;

    @ManyToOne
    private UserEntity relatedUser;

    @Column(nullable = false) // Adiciona a anotação para não permitir nulos
    private String content; // Adicionando o campo content

    @ManyToOne
    private LocalDateTime createdAt;

    @ManyToOne
    private Boolean isRead;

    @ManyToOne
    private LocalDateTime timestamp;

    @Column(nullable = false) // Adiciona a anotação para não permitir nulos
    private String url;

    
}

package br.com.gfctech.starter.entity;

import java.time.LocalDateTime;

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
    private UserEntity user;

    @ManyToOne
    private PostEntity relatedPost;

    @ManyToOne
    private UserEntity relatedUser;

    private LocalDateTime createdAt;

    private Boolean isRead;

    


}

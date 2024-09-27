package br.com.gfctech.starter.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name= "friendship")
@Data
@AllArgsConstructor
public class FriendshipEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private UserEntity user1;  // Usuário que envia a solicitação

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private UserEntity user2;  // Usuário que recebe a solicitação

    private String status;  // "PENDING", "ACCEPTED", "REJECTED", "CANCELLED"
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FriendshipEntity() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public void accept() {
        this.status = "ACCEPTED";
        this.updatedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = "REJECTED";
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = "CANCELLED";
        this.updatedAt = LocalDateTime.now();
    }

    // Getters e Setters
}

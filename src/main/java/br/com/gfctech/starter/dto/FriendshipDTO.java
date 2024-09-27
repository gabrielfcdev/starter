package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDTO {

    private Long id;
    private Long user1Id;
    private Long user2Id;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Construtores, Getters e Setters
}

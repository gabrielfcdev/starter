package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDTO {
    private Long id;
    private String status;

    private User user1;
    private User user2;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

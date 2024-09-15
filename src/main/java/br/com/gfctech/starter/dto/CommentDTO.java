package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

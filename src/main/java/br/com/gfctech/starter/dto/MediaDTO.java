package br.com.gfctech.starter.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {
    
    private Long id;
    private String url;
    private String type;
    private LocalDateTime uploadAt;
    private Long postId;
    private Long userId;

}

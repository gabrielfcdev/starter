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
    private String type;
    private String content; 
    private LocalDateTime createdAt;
    private Boolean isRead;
    private LocalDateTime timestamp;
    private String url;

}

package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private String status;
    private UserDTO user; // Ou outros campos necessários
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public GroupDTO(Long id, String name, String status, UserDTO user, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    // outros métodos (getters, setters, etc.)
}

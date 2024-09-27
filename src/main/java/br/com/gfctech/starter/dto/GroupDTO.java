package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private String status;
    private List<UserDTO> users; // Agora é uma lista de UserDTO
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Construtor que aceita lista de usuários
    public GroupDTO(Long id, String name, String status, List<UserDTO> users, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.users = users;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id; // ID do grupo
    private String name; // Nome do grupo
    private String status; // Status do grupo
    private UserDTO user; // Objeto UserDTO em vez de Long userId
    private LocalDateTime createdAt; // Data de criação
    private LocalDateTime updatedAt; // Data de atualização
}

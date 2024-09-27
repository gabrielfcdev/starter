package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Date dateBirth;
    private String profilePicture;
    private String bio;
    private LocalDateTime dateJoined;
    private LocalDateTime lastLogin;
    private String status;

    public UserDTO(String email, Long id, String username) {
        this.email = email;
        this.id = id;
        this.username = username;
    }


    
}

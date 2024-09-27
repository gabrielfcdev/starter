package br.com.gfctech.starter.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    



    
}

package br.com.gfctech.starter.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Date dateBirth;

    @Column(name="profile_picture")
    private String profilePicture;
    
    private String bio;

    @Column(name="date_joined", nullable = false)
    private LocalDateTime dateJoined;

    @Column(name="last_login")
    private LocalDateTime  lastLogin;

    private String status;

    @OneToMany(mappedBy="autor")
    private List<PostEntity> posts = new ArrayList<>();


    private List<GroupEntity> groups = new ArrayList<>();


}
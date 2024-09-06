package br.com.gfctech.starter.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique=true)
    private String username;

    @Column()
    private String password;
    private String email;
    private String bio;
    private List<Post> posts = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private  List<PostEntity> posts;


}
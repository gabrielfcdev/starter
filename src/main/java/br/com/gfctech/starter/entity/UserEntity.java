package br.com.gfctech.starter.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @Column(name="date_joined", nullable = false, updatable = false)
    private LocalDateTime dateJoined;


    @Column(name="last_login")
    private LocalDateTime  lastLogin;

    private String status;

    @OneToMany(mappedBy="author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_groups",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<GroupEntity> groups = new ArrayList<>();

    
    @ManyToMany
    @JoinTable(
        name = "user_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserEntity> friends = new ArrayList<>();

    public void updateProfile(String newProfilePicture, String newBio) {
        this.profilePicture = newProfilePicture;
        this.bio = newBio;
    }

    @PrePersist
    protected void onCreate(){
        this.dateJoined = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastLogin = LocalDateTime.now();
    }



    public void addFriend(UserEntity friend) {
        this.friends.add(friend);
    }

    public void removeFriend(UserEntity friend) {
        this.friends.remove(friend);
    }
    

}

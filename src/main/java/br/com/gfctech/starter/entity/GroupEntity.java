package br.com.gfctech.starter.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String status;

    // Relacionamento muitos para muitos entre grupos e usuários
    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> users = new ArrayList<>();

    // Método para adicionar usuário ao grupo
    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getGroups().add(this); // Também adiciona o grupo à lista de grupos do usuário
    }

    // Método para remover usuário do grupo
    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.getGroups().remove(this); // Remove o grupo da lista de grupos do usuário
    }

    public void enterGroup(UserEntity user) {
        this.users.add(user);
        
        user.getGroups().add(this);
    }

}

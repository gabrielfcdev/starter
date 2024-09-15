package br.com.gfctech.starter.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

    // Lista de URLs de mídia (imagens, vídeos, etc.)
    @ElementCollection
    @Column(name = "media_urls")
    private List<String> media = new ArrayList<>();

    @Column(name = "likes_count")
    private int likesCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes = new ArrayList<>();

    // Método para adicionar um comentário
    public void addComment(CommentEntity comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    // Método para remover um comentário
    public void removeComment(CommentEntity comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    // Método para adicionar um like
    public void addLike(LikeEntity like) {
        likes.add(like);
        like.setPost(this);
        likesCount++;
    }

    // Método para remover um like
    public void removeLike(LikeEntity like) {
        likes.remove(like);
        like.setPost(null);
        likesCount--;
    }

    // Método para atualizar o conteúdo do post
    public void editPost(String newContent) {
        this.content = newContent;
        this.updatedAt = LocalDateTime.now();
    }
}

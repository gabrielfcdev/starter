package br.com.gfctech.starter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.dto.CommentDTO;
import br.com.gfctech.starter.entity.CommentEntity;
import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.CommentRepository;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Criar comentário
    @Transactional
    public CommentDTO createComment(Long userId, Long postId, String content) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        CommentEntity comment = new CommentEntity();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return new CommentDTO(comment.getId(), comment.getContent(), user.getId(), post.getId(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    // Buscar comentários de um post
    public List<CommentDTO> getCommentsByPost(Long postId) {
        return commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getPost().getId().equals(postId))
                .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getAuthor().getId(), comment.getPost().getId(), comment.getCreatedAt(), comment.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    // Editar comentário
    @Transactional
    public CommentDTO editComment(Long commentId, Long userId, String newContent) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User is not the author of this comment");
        }

        comment.setContent(newContent);
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return new CommentDTO(comment.getId(), comment.getContent(), comment.getAuthor().getId(), comment.getPost().getId(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    // Deletar comentário
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User is not the author of this comment");
        }

        commentRepository.delete(comment);
    }
}

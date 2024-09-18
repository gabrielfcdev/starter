package br.com.gfctech.starter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.dto.CommentDTO;
import br.com.gfctech.starter.dto.LikeDTO;
import br.com.gfctech.starter.entity.CommentEntity;
import br.com.gfctech.starter.entity.LikeEntity;
import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.CommentRepository;
import br.com.gfctech.starter.repository.LikeRepository;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class LikeService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Criar comentário
    @Transactional
    public LikeDTO createLike(Long userId, Long postId, String content) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        LikeEntity like = new LikeEntity();
        like.setAuthor(user);
        like.setPost(post);
        likeRepository.save(like);

        return new LikeDTO(like.getId(), like.getContent(), user.getId(), post.getId());
    }

    // Buscar likes de um post
    public List<LikeDTO> getLikesByPost(Long postId) {
        return likeRepository.findAll()
                .stream()
                .filter(like -> like.getPost().getId().equals(postId))
                .map(like -> new LikeDTO(like.getId(), like.getContent(), like.getAuthor().getId(), like.getPost().getId(), like.getCreatedAt(), like.getUpdatedAt()))
                .collect(Collectors.toList());
    }


    // Deletar comentário
    @Transactional
    public void deleteLike(Long likeId, Long userId) {
        LikeEntity like = likeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!like.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User is not the author of this comment");
        }

        commentRepository.delete(like);
    }
}

package br.com.gfctech.starter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.dto.LikeDTO;
import br.com.gfctech.starter.entity.LikeEntity;
import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.LikeRepository;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Adicionar like
    @Transactional
    public LikeDTO addLike(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        LikeEntity like = new LikeEntity();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);

        return new LikeDTO(like.getId(), user.getId(), post.getId());
    }

    // Buscar likes de um post
    public List<LikeDTO> getLikesByPost(Long postId) {
        return likeRepository.findAll()
                .stream()
                .filter(like -> like.getPost().getId().equals(postId))
                .map(like -> new LikeDTO(like.getId(), like.getUser().getId(), like.getPost().getId()))
                .collect(Collectors.toList());
    }

    // Deletar like
    @Transactional
    public void removeLike(Long likeId, Long userId) {
        LikeEntity like = likeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        if (!like.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is not the author of this like");
        }

        likeRepository.delete(like);
    }
}

package br.com.gfctech.starter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Método para criar um novo post
    public PostEntity createPost(long userId, String content) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity newPost = new PostEntity();
        newPost.setAuthor(user);
        newPost.setContent(content);
        return postRepository.save(newPost);
    }

    // Método para buscar um post por ID
    public Optional<PostEntity> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    // Método para buscar todos os posts de um usuário
    public List<PostEntity> getPostsByUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return postRepository.findByAuthor(user);
    }

    // Método para editar um post
    public PostEntity editPost(Long userId, Long postId, String newContent) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));

        // Verifica se o usuário é o autor do post
        if (!post.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("User is not the author of the post");
        }

        // Atualiza o conteúdo do post
        post.setContent(newContent);
        return postRepository.save(post);
    }

    // Método para deletar um post
    public void deletePost(Long userId, Long postId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));

        // Verifica se o usuário é o autor do post
        if (!post.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("User is not the author of the post");
        }

        postRepository.delete(post);
    }
}

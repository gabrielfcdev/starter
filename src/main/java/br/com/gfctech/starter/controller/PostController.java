package br.com.gfctech.starter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Criar post
    @PostMapping("/create")
    public ResponseEntity<PostEntity> createPost(@RequestParam long userId, @RequestParam String content) {
        PostEntity post = postService.createPost(userId, content);
        return ResponseEntity.ok(post);
    }

    // Buscar post por ID
    @GetMapping("/{postId}")
    public ResponseEntity<Optional<PostEntity>> getPostById(@PathVariable Long postId) {
        Optional<PostEntity> post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // Buscar todos os posts de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostEntity>> getPostsByUser(@PathVariable Long userId) {
        List<PostEntity> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    // Editar post
    @PutMapping("/edit/{postId}")
    public ResponseEntity<PostEntity> editPost(@RequestParam Long userId, @PathVariable Long postId, @RequestParam String content) {
        PostEntity post = postService.editPost(userId, postId, content);
        return ResponseEntity.ok(post);
    }

    // Deletar post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@RequestParam Long userId, @PathVariable Long postId) {
        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }
}

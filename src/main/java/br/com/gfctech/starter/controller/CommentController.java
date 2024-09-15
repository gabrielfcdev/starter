package br.com.gfctech.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.dto.CommentDTO;
import br.com.gfctech.starter.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Criar um novo comentário
    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestParam Long userId, @RequestParam Long postId, @RequestBody String content) {
        CommentDTO createdComment = commentService.createComment(userId, postId, content);
        return ResponseEntity.ok(createdComment);
    }

    // Buscar comentários de um post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    // Editar comentário
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable Long commentId, @RequestParam Long userId, @RequestBody String content) {
        CommentDTO updatedComment = commentService.editComment(commentId, userId, content);
        return ResponseEntity.ok(updatedComment);
    }

    // Deletar comentário
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}

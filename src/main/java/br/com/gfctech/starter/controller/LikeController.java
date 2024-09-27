package br.com.gfctech.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfctech.starter.dto.LikeDTO;
import br.com.gfctech.starter.service.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/add")
    public ResponseEntity<LikeDTO> addLike(@RequestParam Long userId, @RequestParam Long postId) {
        LikeDTO addedLike = likeService.addLike(userId, postId);
        return ResponseEntity.ok(addedLike);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeDTO>> getLikesByPost(@PathVariable Long postId) {
        List<LikeDTO> likes = likeService.getLikesByPost(postId);
        return ResponseEntity.ok(likes);
    }

    @DeleteMapping("/remove/{likeId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long likeId, @RequestParam Long userId) {
        likeService.removeLike(likeId, userId);
        return ResponseEntity.noContent().build();
    }
}

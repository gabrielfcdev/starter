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
import br.com.gfctech.starter.dto.FriendshipDTO;
import br.com.gfctech.starter.repository.FriendshipRepository;
import br.com.gfctech.starter.service.FriendshipService;


@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    // Adicionar amigo
    @PostMapping("/add")
    public ResponseEntity<FriendshipDTO> addFriend(@RequestParam Long userId, @RequestParam Long friendshipId) {
        FriendshipDTO createdComment = friendshipService.addFriend(userId, friendshipId);
        return ResponseEntity.ok(addFriend);
    }

    // Buscar amigos
    @GetMapping("/friends/{friendshipId}")
    public ResponseEntity<List<FriendshipDTO>> getCommentsByPost(@PathVariable Long friendshipId) {
        List<FriendshipDTO> friends = friendshipService.getfriendships(friendshipId);
        return ResponseEntity.ok(friends);
    }

    // Deletar amizade
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long friendshipId, @RequestParam Long userId) {
        friendshipService.deleteFriend(friendshipId, userId);
        return ResponseEntity.noContent().build();
    }
}

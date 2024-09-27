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

import br.com.gfctech.starter.dto.FriendshipDTO;
import br.com.gfctech.starter.entity.FriendshipEntity;
import br.com.gfctech.starter.service.FriendshipService;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<FriendshipDTO> createFriendship(@RequestBody FriendshipDTO friendshipDTO) {
        FriendshipDTO createdFriendship = friendshipService.createFriendship(friendshipDTO);
        return ResponseEntity.ok(createdFriendship);
    }

    @GetMapping
    public List<FriendshipEntity> getAllFriendships() {
        return friendshipService.getAllFriendships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendshipEntity> getFriendshipById(@PathVariable Long id) {
        return friendshipService.getFriendshipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/action")
    public ResponseEntity<FriendshipDTO> updateFriendshipStatus(@PathVariable Long id, @RequestParam String action) {
        FriendshipDTO updatedFriendship = friendshipService.updateFriendshipStatus(id, action);
        return ResponseEntity.ok(updatedFriendship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.noContent().build();
    }
}

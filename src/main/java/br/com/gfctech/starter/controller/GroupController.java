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
import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.repository.FriendshipRepository;
import br.com.gfctech.starter.service.FriendshipService;
import br.com.gfctech.starter.service.GroupService;


@RestController
@RequestMapping("/api/friends")
public class GroupController {

    @Autowired
    private GroupService groupService;
    // entrar no grupo
    @PostMapping("/enter")
    public ResponseEntity<GroupDTO> addFriend(@RequestParam Long userId, @RequestParam Long groupId) {
        GroupDTO enterGroup = groupService.addFriend(userId, groupId);
        return ResponseEntity.ok(addFriend);
    }

    // buscar grupos
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<List<GroupDTO>> getGroups(@PathVariable Long groupId) {
        List<GroupDTO> groups = groupService.getGroups(groupId);
        return ResponseEntity.ok(groups);
    }

    // sair do
    @DeleteMapping("/remove/{groupId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long groupId, @RequestParam Long userId) {
        groupService.removeGroup(groupId, userId);
        return ResponseEntity.noContent().build();
    }
}

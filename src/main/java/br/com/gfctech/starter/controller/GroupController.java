package br.com.gfctech.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.service.GroupService;

@RestController
@RequestMapping("/api/friends")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Entrar no grupo
    @PostMapping("/enter")
    public ResponseEntity<GroupDTO> enterGroup(@RequestParam Long userId, @RequestParam Long groupId) {
        GroupDTO groupDTO = groupService.enterGroup(userId, groupId);
        return ResponseEntity.ok(groupDTO);
    }

    // Buscar grupo por ID
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long groupId) {
        GroupDTO group = groupService.getGroupById(groupId);
        return ResponseEntity.ok(group);
    }

    // Sair do grupo
    @DeleteMapping("/remove/{groupId}")
    public ResponseEntity<Void> removeUser(@PathVariable Long groupId, @RequestParam Long userId) {
        groupService.removeGroup(groupId, userId);
        return ResponseEntity.noContent().build();
    }

    // Buscar usuários do grupo
    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<List<GroupDTO>> getUsersByGroup(@PathVariable Long groupId) {
        List<GroupDTO> users = groupService.getUsersByGroup(groupId);
        return ResponseEntity.ok(users);
    }
}

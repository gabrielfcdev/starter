package br.com.gfctech.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

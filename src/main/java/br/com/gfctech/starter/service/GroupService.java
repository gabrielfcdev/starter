package br.com.gfctech.starter.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.entity.GroupEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.GroupRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // Entrar no grupo
    @Transactional
    public GroupDTO enterGroup(Long userId, Long groupId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.addUser(user);
        groupRepository.save(group);

        return new GroupDTO(group.getId(), group.getName(), group.getStatus(), null, LocalDateTime.now(), LocalDateTime.now());
    }



    // Buscar grupo por ID
    public GroupDTO getGroupById(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    
        // Retorna o GroupDTO com o status e o ID do grupo
        return new GroupDTO(group.getId(), group.getName(), group.getStatus(), null, LocalDateTime.now(), LocalDateTime.now());
    }
    

    // Sair do grupo
    @Transactional
    public void removeGroup(Long groupId, Long userId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        group.getUsers().remove(user); // Removendo o usuário do grupo
        groupRepository.save(group);
    }
    
}

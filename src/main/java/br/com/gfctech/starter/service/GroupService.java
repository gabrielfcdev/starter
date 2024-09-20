package br.com.gfctech.starter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.dto.GroupDTO;
import br.com.gfctech.starter.entity.CommentEntity;
import br.com.gfctech.starter.entity.GroupEntity;
import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;
import br.com.gfctech.starter.repository.CommentRepository;
import br.com.gfctech.starter.repository.FriendshipRepository;
import br.com.gfctech.starter.repository.GroupRepository;
import br.com.gfctech.starter.repository.PostRepository;
import br.com.gfctech.starter.repository.UserRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // entrar no grupo
    @Transactional
    public GroupDTO enterGroup(Long userId, Long groupId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.addUser(user);
        groupRepository.save(group);
    }

    // Buscar usuarios do grupo
    public List<GroupDTO> getUsersByGroup(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId)
        .orElseThrow(() -> new RuntimeException( "Group not found"));

        return group.getUsers()
                .stream()
               .map(user -> new GroupDTO(group.getId(), group.getName(), user.getId(), LocalDateTime.now()))
               .collect(Collectors.toList());
    }
   

    // sair do grupo
    @Transactional
    public void removeGroup(Long groupId, Long userId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));


        groupRepository.removeUser(user);
        groupRepository.save(group);
    }
}

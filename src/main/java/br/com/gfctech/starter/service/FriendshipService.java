package br.com.gfctech.starter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gfctech.starter.dto.FriendshipDTO;
import br.com.gfctech.starter.entity.FriendshipEntity;
import br.com.gfctech.starter.repository.FriendshipRepository;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public FriendshipDTO createFriendship(FriendshipDTO friendshipDTO) {
        FriendshipEntity friendship = new FriendshipEntity();
        //friendship.setUser1(userRepository.findById(friendshipDTO.getUser1Id()).orElseThrow());
        //friendship.setUser2(userRepository.findById(friendshipDTO.getUser2Id()).orElseThrow());

        FriendshipEntity savedFriendship = friendshipRepository.save(friendship);
        return new FriendshipDTO(savedFriendship.getId(), friendshipDTO.getUser1Id(), friendshipDTO.getUser2Id(),
                savedFriendship.getStatus(), savedFriendship.getCreatedAt(), savedFriendship.getUpdatedAt());
    }

    public List<FriendshipEntity> getAllFriendships() {
        return friendshipRepository.findAll();
    }

    public Optional<FriendshipEntity> getFriendshipById(Long id) {
        return friendshipRepository.findById(id);
    }

    public FriendshipDTO updateFriendshipStatus(Long id, String action) {
        FriendshipEntity friendship = friendshipRepository.findById(id).orElseThrow();

        switch (action.toLowerCase()) {
            case "accept":
                friendship.accept();
                break;
            case "reject":
                friendship.reject();
                break;
            case "cancel":
                friendship.cancel();
                break;
            default:
                throw new IllegalArgumentException("Ação inválida");
        }

        friendshipRepository.save(friendship);

        return new FriendshipDTO(friendship.getId(), friendship.getUser1().getId(), friendship.getUser2().getId(),
                friendship.getStatus(), friendship.getCreatedAt(), friendship.getUpdatedAt());
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }
}

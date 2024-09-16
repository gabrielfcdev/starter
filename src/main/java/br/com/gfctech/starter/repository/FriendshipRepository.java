package br.com.gfctech.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfctech.starter.entity.FriendshipEntity;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
}

package br.com.gfctech.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gfctech.starter.entity.PostEntity;
import br.com.gfctech.starter.entity.UserEntity;

public interface  PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByAuthor(UserEntity author);
}

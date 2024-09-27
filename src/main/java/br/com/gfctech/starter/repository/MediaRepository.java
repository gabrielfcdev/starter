package br.com.gfctech.starter.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfctech.starter.entity.MediaEntity;
@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
}

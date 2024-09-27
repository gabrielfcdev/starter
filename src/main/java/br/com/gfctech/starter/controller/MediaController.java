package br.com.gfctech.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.com.gfctech.starter.dto.MediaDTO;
import br.com.gfctech.starter.entity.MediaEntity;
import br.com.gfctech.starter.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired

    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<MediaDTO> createMedia(@RequestBody MediaDTO mediaDTO) {
        MediaDTO createdMedia = mediaService.createMedia(mediaDTO);
        return ResponseEntity.ok(createdMedia);
    }

    @GetMapping
    public List<MediaEntity> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaEntity> getMediaById(@PathVariable Long id) {
        return mediaService.getMediaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long mediaId) {
        mediaService.deleteMedia(mediaId);
        return ResponseEntity.noContent().build();
    }
}

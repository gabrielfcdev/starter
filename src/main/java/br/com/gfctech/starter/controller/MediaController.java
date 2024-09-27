package br.com.gfctech.starter.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gfctech.starter.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    // Endpoint para upload de mídia
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedia(@RequestParam("file") MultipartFile file) {
        try {
            String filename = mediaService.uploadMedia(file);
            return ResponseEntity.ok("Arquivo salvo com sucesso: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    // Endpoint para download de mídia
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadMedia(@PathVariable String filename) {
        try {
            Path filePath = mediaService.downloadMedia(filename);
            byte[] fileContent = Files.readAllBytes(filePath);

            String contentType = Files.probeContentType(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<Void> deleteMedia(@PathVariable String filename) {
        try {
            mediaService.deleteMedia(filename);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

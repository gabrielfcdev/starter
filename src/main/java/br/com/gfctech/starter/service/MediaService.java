package br.com.gfctech.starter.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

    private final String uploadDir = "uploads/";

    // Método para salvar arquivo de mídia (imagens, vídeos)
    public String uploadMedia(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("O arquivo está vazio");
        }

        // Validação do tipo de arquivo (somente imagens e vídeos, por exemplo)
        String contentType = file.getContentType();
        if (!isSupportedMediaType(contentType)) {
            throw new RuntimeException("Tipo de arquivo não suportado: " + contentType);
        }

        // Gerar nome único para o arquivo
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + filename);

        // Salvar o arquivo
        Files.copy(file.getInputStream(), filePath);

        return filename; // Retornar o nome do arquivo salvo
    }

    // Método para deletar arquivo de mídia
    public void deleteMedia(String filename) throws IOException {
        Path filePath = Paths.get(uploadDir + filename);
        Files.deleteIfExists(filePath);
    }

    // Método para baixar arquivo de mídia
    public Path downloadMedia(String filename) {
        Path filePath = Paths.get(uploadDir + filename);
        if (Files.exists(filePath)) {
            return filePath;
        } else {
            throw new RuntimeException("Arquivo não encontrado: " + filename);
        }
    }

    // Valida tipos de arquivo suportados (somente imagens e vídeos neste exemplo)
    private boolean isSupportedMediaType(String contentType) {
        return contentType.equals("image/jpeg") || 
               contentType.equals("image/png") || 
               contentType.equals("video/mp4");
    }
}

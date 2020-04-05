package com.firsttest.autho.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Facture {
@Id
private String id;
    private String codeExp;
    private String codeClt;
    private String dateFac;
    private int numFac;
    private  float mntTtc;
    private  float mntHt;
    private MultipartFile file_pdf;

    public void saveUploadedFile() throws IOException {
        if (!file_pdf.isEmpty()) {
            byte[] bytes = file_pdf.getBytes();
            Path path = Paths.get("C:/Users/aabair/Documents/autho/" + file_pdf.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

}

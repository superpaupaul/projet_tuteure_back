package projet.depta.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class PDFController {

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable int id) throws IOException {
        ClassPathResource pdfFile = new ClassPathResource("A._MARTIN_DUT_S3.pdf");
        return ResponseEntity
                .ok()
                .contentLength(pdfFile.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));

    }
}

package formacion.block11uploaddownloadfiles.file.controller;

import formacion.block11uploaddownloadfiles.exception.StorageException;
import formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import formacion.block11uploaddownloadfiles.file.application.FileService;
import formacion.block11uploaddownloadfiles.file.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class FileController {
    @Autowired
    FileService fileService;


    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable String id) {
        Resource file = null;
        try {
            file = fileService.loadAsResourceById(id);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (StorageFileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filename/{filename}")
    public ResponseEntity<Resource> downloadFileByFilename(@PathVariable String filename) {
        try {
            Resource file = fileService.loadAsResourceByFilename(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (StorageFileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/setpath/{path}")
    public ResponseEntity<String> setPath(@PathVariable String path) {
        fileService.setPath(path);
        fileService.init();
        return ResponseEntity.ok().body("El nuevo directorio es: " + path);
    }

    @PostMapping("/")
    public ResponseEntity<File> uploadAnyFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws StorageException {
        try {
            redirectAttributes.addFlashAttribute("mensaje", "Has subido satisfactoriamente " + file.getName() + "!");
            return ResponseEntity.ok().body(fileService.store(file));

        } catch (StorageException e) {
            throw new StorageException(e.getMessage());
        }
    }

    @PostMapping("/upload/{tipo}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String tipo) {
        try {
            String fileName = file.getOriginalFilename().toString();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);

            if (!tipo.equals(fileType)) {
                return ResponseEntity.badRequest().body("Solo se permite subir ficheros con extensión '" + tipo + "'");
            }

            return ResponseEntity.ok().body(fileService.store(file));

        } catch (StorageException e) {
            throw new StorageException(e.getMessage());
        }

    }
}

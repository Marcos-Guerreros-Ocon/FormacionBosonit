package formacion.block11uploaddownloadfiles.file.application;


import formacion.block11uploaddownloadfiles.exception.StorageException;
import formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import formacion.block11uploaddownloadfiles.file.domain.File;
import formacion.block11uploaddownloadfiles.file.repository.FileRepository;
import formacion.block11uploaddownloadfiles.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private Path root;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StorageProperties storageProperties;

    @Value("${ruta.archivo}")
    private String rutaArchivo;

    public FileServiceImpl(StorageProperties properties) {
        this.root = Paths.get(properties.getLocation());
    }

    @Override
    public Path load(String filename) {
        return root.resolve(filename);
    }

    @Override
    public Resource loadAsResourceById(String id) throws StorageFileNotFoundException {
        try {
            Optional<File> optionalFile = fileRepository.findById(Integer.parseInt(id));

            if (optionalFile.isEmpty())
                throw new StorageFileNotFoundException("No se ha encontrado un fichero con el id: " + id);

            String fichero = optionalFile.get().getNombre();
            Path file = load(fichero);
            Resource resource = null;

            resource = new UrlResource(file.toUri());


            if (!resource.exists() || !resource.isReadable()) {
                throw new StorageFileNotFoundException("Could not read file with id: " + id);
            }

            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource loadAsResourceByFilename(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new StorageFileNotFoundException("Could not read file with id: " + filename);
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file with id: " + filename, e);
        }
    }

    @Override
    public File store(MultipartFile file) {
        Path finalLocation = this.root;
        if (!rutaArchivo.isBlank()) {
            finalLocation = Paths.get(rutaArchivo);
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException("Error al intentar almacenar un fichero vacío");
            }

            Path destinationFile = finalLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(finalLocation.toAbsolutePath())) {
                throw new StorageException("No se puede almacenar el archivo fuera del directorio actual");
            }


            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                File fichero = new File();
                fichero.setNombre(file.getOriginalFilename());
                fichero.setCategoria(file.getContentType());
                fichero.setFecha_subida(Date.valueOf(LocalDate.now()).toString());

                return fileRepository.save(fichero);
            }
        } catch (IOException e) {
            throw new StorageException("Error al almacenar el archivo", e);
        }
    }

    @Override
    public void setPath(String path) {
        storageProperties.setLocation(path);
        this.root = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
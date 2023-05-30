package formacion.block11uploaddownloadfiles.file.application;

import formacion.block11uploaddownloadfiles.exception.StorageFileNotFoundException;
import formacion.block11uploaddownloadfiles.file.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileService {
    Path load(String filename);

    Resource loadAsResourceById(String id) throws StorageFileNotFoundException;

    Resource loadAsResourceByFilename(String filename) throws StorageFileNotFoundException;

    File store(MultipartFile file);

    void setPath(String path);

    void deleteAll();

    void init();
}
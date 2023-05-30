package formacion.block11uploaddownloadfiles.file.repository;

import formacion.block11uploaddownloadfiles.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}

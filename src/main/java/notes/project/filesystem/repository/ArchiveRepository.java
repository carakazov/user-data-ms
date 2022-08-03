package notes.project.filesystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import notes.project.filesystem.model.Archive;
import notes.project.filesystem.model.CreatedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    List<Archive> findAllByCreatedFile(CreatedFile file);

    Optional<Archive> findByVersionFileGuid(UUID versionFileGuid);
}

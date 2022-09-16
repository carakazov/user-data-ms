package notes.project.userdatasystem.repository;

import java.util.Optional;

import notes.project.userdatasystem.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {
    boolean existsBySystemName(String systemName);

    Optional<System> findBySystemName(String systemName);
}

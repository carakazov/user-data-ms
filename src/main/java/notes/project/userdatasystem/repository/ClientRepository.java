package notes.project.userdatasystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import org.apache.commons.io.filefilter.SymbolicLinkFileFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findBySystemSystemNameAndEmail(String systenName, String email);

    Optional<Client> findByExternalId(UUID externalId);

    List<Client> findAllBySystem(System system);

    Boolean existsBySystemAndEmail(System system, String email);
}

package notes.project.userdatasystem.repository;

import java.util.Optional;
import java.util.UUID;

import notes.project.userdatasystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findBySystemSystemNameAndEmail(String systenName, String email);

    Optional<Client> findByExternalId(UUID externalId);
}

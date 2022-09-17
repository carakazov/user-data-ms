package notes.project.userdatasystem.repository;

import java.util.List;

import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
    List<AdditionalInfo> findByClient(Client client);
}

package notes.project.userdatasystem.repository;

import notes.project.userdatasystem.model.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
}

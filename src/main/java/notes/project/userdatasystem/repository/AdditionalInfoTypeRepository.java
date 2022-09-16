package notes.project.userdatasystem.repository;

import java.util.Optional;

import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoTypeRepository extends JpaRepository<AdditionalInfoType, Long> {
    boolean existsBySystemAndType(System system, String type);

    Optional<AdditionalInfoType> findBySystemAndType(System system, String type);
}

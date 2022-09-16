package notes.project.userdatasystem.service.api;

import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.System;

public interface AdditionalInfoTypeService {
    AdditionalInfoType save(AdditionalInfoType additionalInfoType);

    AdditionalInfoType findBySystemAndType(System system, String type);

    Boolean existsBySystemAndType(System system, String type);
}

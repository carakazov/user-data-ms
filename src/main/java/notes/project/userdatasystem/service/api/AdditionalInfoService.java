package notes.project.userdatasystem.service.api;

import java.util.List;

import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;

public interface AdditionalInfoService {
    AdditionalInfo save(AdditionalInfo additionalInfo);

    List<AdditionalInfo> findByClient(Client client);

    AdditionalInfo changeValue(AdditionalInfo info, String newValue);
}

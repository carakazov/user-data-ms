package notes.project.userdatasystem.service.api.impl;

import java.util.List;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.repository.AdditionalInfoRepository;
import notes.project.userdatasystem.service.api.AdditionalInfoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdditionalInfoServiceImpl implements AdditionalInfoService {
    private final AdditionalInfoRepository repository;

    @Override
    @Transactional
    public AdditionalInfo save(AdditionalInfo additionalInfo) {
        return repository.save(additionalInfo);
    }

    @Override
    @Transactional
    public List<AdditionalInfo> findByClient(Client client) {
        return repository.findByClient(client);
    }

    @Override
    @Transactional
    public AdditionalInfo changeValue(AdditionalInfo info, String newValue) {
        return info.setInfo(newValue);
    }
}

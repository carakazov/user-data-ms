package notes.project.userdatasystem.service.api.impl;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.model.AdditionalInfo;
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
}

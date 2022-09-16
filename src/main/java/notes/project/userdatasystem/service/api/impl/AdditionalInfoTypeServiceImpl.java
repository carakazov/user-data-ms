package notes.project.userdatasystem.service.api.impl;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.repository.AdditionalInfoTypeRepository;
import notes.project.userdatasystem.service.api.AdditionalInfoTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdditionalInfoTypeServiceImpl implements AdditionalInfoTypeService {
    private final AdditionalInfoTypeRepository repository;

    @Override
    @Transactional
    public AdditionalInfoType save(AdditionalInfoType additionalInfoType) {
        return repository.save(additionalInfoType);
    }

    @Override
    @Transactional
    public AdditionalInfoType findBySystemAndType(System system, String type) {
        return repository.findBySystemAndType(system, type).orElseThrow(
            () -> new NotFoundException("Type " + type + " in system " + system.getSystemName() + " not found")
        );
    }

    @Override
    public Boolean existsBySystemAndType(System system, String type) {
        return repository.existsBySystemAndType(system, type);
    }
}

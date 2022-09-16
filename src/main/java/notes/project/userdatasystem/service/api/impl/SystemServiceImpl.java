package notes.project.userdatasystem.service.api.impl;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.repository.SystemRepository;
import notes.project.userdatasystem.service.api.SystemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final SystemRepository repository;

    @Override
    @Transactional
    public System save(System system) {
        return repository.save(system);
    }

    @Override
    public Boolean existsBySystemName(String systemName) {
        return repository.existsBySystemName(systemName);
    }

    @Override
    public System findBySystemName(String systemName) {
        return repository.findBySystemName(systemName).orElseThrow(
            () -> new NotFoundException("System " + systemName + " not found")
        );
    }
}

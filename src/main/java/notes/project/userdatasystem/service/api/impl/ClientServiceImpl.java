package notes.project.userdatasystem.service.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import liquibase.pro.packaged.C;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.dto.ClientDto;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.repository.ClientRepository;
import notes.project.userdatasystem.service.api.AdditionalInfoService;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.utils.mapper.api.ClientDtoMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final AdditionalInfoService additionalInfoService;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    @Transactional
    public Client findBySystemNameAndEmail(String systemName, String email) {
        return repository.findBySystemSystemNameAndEmail(systemName, email).orElseThrow(
            () -> new NotFoundException("Can not find client with email " + email + " in system " + systemName)
        );
    }

    @Override
    @Transactional
    public Client findClientByExternalId(UUID externalId) {
        return repository.findByExternalId(externalId).orElseThrow(
            () -> new NotFoundException("Can not find client with external id " + externalId)
        );
    }

    @Override
    @Transactional
    public ClientDto getSingleClient(UUID externalId) {
        Client client = findClientByExternalId(externalId);
        List<AdditionalInfo> info = additionalInfoService.findByClient(client);
        return clientDtoMapper.to(client, info);
    }
}

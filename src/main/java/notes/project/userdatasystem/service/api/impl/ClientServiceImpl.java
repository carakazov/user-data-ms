package notes.project.userdatasystem.service.api.impl;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.dto.*;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.repository.ClientRepository;
import notes.project.userdatasystem.service.api.AdditionalInfoService;
import notes.project.userdatasystem.service.api.AdditionalInfoTypeService;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.service.api.SystemService;
import notes.project.userdatasystem.utils.mapper.api.AdditionalInfoMapper;
import notes.project.userdatasystem.utils.mapper.api.AdditionalInfoTypeMapper;
import notes.project.userdatasystem.utils.mapper.api.ClientDtoMapper;
import notes.project.userdatasystem.validation.Validator;
import notes.project.userdatasystem.validation.dto.ChangeClientPersonalInfoValidationDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final AdditionalInfoService additionalInfoService;
    private final ClientDtoMapper clientDtoMapper;
    private final SystemService systemService;
    private final Validator<ChangeClientPersonalInfoValidationDto> changeClientPersonalInfoValidator;
    private final AdditionalInfoTypeService additionalInfoTypeService;
    private final AdditionalInfoTypeMapper additionalInfoTypeMapper;
    private final AdditionalInfoMapper additionalInfoMapper;

    private static final Map<ChangePersonalInfo, ClientPersonalInfoChanger> PERSONAL_INFO_CHANGE = Map.of(
        ChangePersonalInfo.NEW_NAME, Client::setName,
        ChangePersonalInfo.NEW_SURNAME, Client::setSurname,
        ChangePersonalInfo.NEW_MIDDLE_NAME, Client::setMiddleName,
        ChangePersonalInfo.NEW_EMAIL, Client::setEmail
    );

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

    @Override
    @Transactional
    public SystemClientListResponseDto getAllClientsOfSystem(String systemName) {
        System system = systemService.findBySystemName(systemName);
        List<Client> clients = repository.findAllBySystem(system);
        return new SystemClientListResponseDto(
            system.getSystemName(),
            clients.stream().map(item -> clientDtoMapper.toItem(
                item,
                additionalInfoService.findByClient(item)
            )).collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public ClientDto changePersonalInfo(ChangeClientInfoRequestDto request, Boolean addNewParameter) {
        Client client = findClientByExternalId(request.getClientExternalId());
        List<AdditionalInfo> additionalInfo = additionalInfoService.findByClient(client);
        changeClientPersonalInfoValidator.validate(
            new ChangeClientPersonalInfoValidationDto(
                repository.existsBySystemAndEmail(client.getSystem(), request.getClientInfo().getNewValues().get(ChangePersonalInfo.NEW_EMAIL)),
                additionalInfo.stream().map(item -> item.getType().getType()).collect(Collectors.toList()),
                request.getClientInfo().getChangeAdditionalInfo().stream().map(ChangeAdditionalInfoDto::getType).collect(Collectors.toList()),
                addNewParameter
            )
        );
        request.getClientInfo().getNewValues().forEach((key, value) -> PERSONAL_INFO_CHANGE.get(key).change(client, value));
        request.getClientInfo().getChangeAdditionalInfo().forEach(item -> {
            AdditionalInfo info = additionalInfo.stream()
                .filter(innerInfo -> innerInfo.getType().getType().equals(item.getType()))
                .findFirst().orElse(null);
            if(Objects.isNull(info)) {
                AdditionalInfoType type = additionalInfoTypeService.save(additionalInfoTypeMapper.to(client.getSystem(), item.getType()));
                AdditionalInfo newInfo = additionalInfoService.save(additionalInfoMapper.from(type, client, item.getNewValue()));
                additionalInfo.add(newInfo);
            } else {
                additionalInfoService.changeValue(info, item.getNewValue());
            }
        });
        return clientDtoMapper.to(client, additionalInfo);
    }

    @FunctionalInterface
    private interface ClientPersonalInfoChanger {
        void change(Client client, String newValue);
    }
}

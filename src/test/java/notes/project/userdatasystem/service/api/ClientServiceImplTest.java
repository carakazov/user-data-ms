package notes.project.userdatasystem.service.api;

import java.util.Collections;
import java.util.Optional;

import liquibase.pro.packaged.D;
import notes.project.userdatasystem.dto.ChangeClientInfoRequestDto;
import notes.project.userdatasystem.dto.ChangePersonalInfo;
import notes.project.userdatasystem.dto.ClientDto;
import notes.project.userdatasystem.dto.SystemClientListResponseDto;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.repository.ClientRepository;
import notes.project.userdatasystem.service.api.impl.ClientServiceImpl;
import notes.project.userdatasystem.utils.ApiUtils;
import notes.project.userdatasystem.utils.DbUtils;
import notes.project.userdatasystem.utils.TestUtils;
import notes.project.userdatasystem.utils.mapper.api.AdditionalInfoMapper;
import notes.project.userdatasystem.utils.mapper.api.AdditionalInfoTypeMapper;
import notes.project.userdatasystem.utils.mapper.api.ClientDtoMapper;
import notes.project.userdatasystem.validation.Validator;
import notes.project.userdatasystem.validation.dto.ChangeClientPersonalInfoValidationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private AdditionalInfoService additionalInfoService;
    @Mock
    private SystemService systemService;
    @Mock
    private Validator<ChangeClientPersonalInfoValidationDto> changeClientPersonalInfoValidator;
    @Mock
    private AdditionalInfoTypeService additionalInfoTypeService;
    private ClientService service;

    @BeforeEach
    void init() {
        service = new ClientServiceImpl(
            repository,
            additionalInfoService,
            TestUtils.getComplexMapper(ClientDtoMapper.class),
            systemService,
            changeClientPersonalInfoValidator,
            additionalInfoTypeService,
            Mappers.getMapper(AdditionalInfoTypeMapper.class),
            Mappers.getMapper(AdditionalInfoMapper.class)
        );
    }

    @Test
    void saveSuccess() {
        Client request = DbUtils.client().setId(null);
        Client expected = DbUtils.client();

        when(repository.save(any())).thenReturn(expected);

        Client actual = service.save(request);

        assertEquals(expected, actual);

        verify(repository).save(request);
    }

    @Test
    void findBySystemNameAndEmailSuccess() {
        Client expected = DbUtils.client();

        when(repository.findBySystemSystemNameAndEmail(any(), any())).thenReturn(Optional.of(expected));

        Client actual = service.findBySystemNameAndEmail(SYSTEM_NAME, EMAIL);

        assertEquals(expected, actual);

        verify(repository).findBySystemSystemNameAndEmail(SYSTEM_NAME, EMAIL);
    }

    @Test
    void findBySystemNameAndEmailWhenThrow() {
        when(repository.findBySystemSystemNameAndEmail(any(), any())).thenReturn(Optional.empty());

        assertThrows(
            NotFoundException.class,
            () -> service.findBySystemNameAndEmail(SYSTEM_NAME, EMAIL)
        );

        verify(repository).findBySystemSystemNameAndEmail(SYSTEM_NAME, EMAIL);
    }

    @Test
    void findClientByExternalIdSuccess() {
        Client expected = DbUtils.client();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(expected));

        Client actual = service.findClientByExternalId(EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(EXTERNAL_ID);
    }

    @Test
    void findClientByExternalIdWhenThrow() {
        when(repository.findByExternalId(any())).thenReturn(Optional.empty());

        assertThrows(
            NotFoundException.class,
            () -> service.findClientByExternalId(EXTERNAL_ID)
        );

        verify(repository).findByExternalId(EXTERNAL_ID);
    }

    @Test
    void getSingleClientSuccess() {
        Client client = DbUtils.client();
        ClientDto expected = ApiUtils.clientDto();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(client));
        when(additionalInfoService.findByClient(any())).thenReturn(Collections.singletonList(DbUtils.additionalInfo()));

        ClientDto actual = service.getSingleClient(EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(client.getExternalId());
        verify(additionalInfoService).findByClient(client);
    }

    @Test
    void getAllClientsOfSystemSuccess() {
        System system = DbUtils.system();
        Client client = DbUtils.client();

        SystemClientListResponseDto expected = ApiUtils.systemClientListResponseDto();

        when(systemService.findBySystemName(any())).thenReturn(system);
        when(repository.findAllBySystem(any())).thenReturn(Collections.singletonList(client));
        when(additionalInfoService.findByClient(any())).thenReturn(Collections.singletonList(DbUtils.additionalInfo()));

        SystemClientListResponseDto actual = service.getAllClientsOfSystem(SYSTEM_NAME);

        assertEquals(expected, actual);

        verify(systemService).findBySystemName(system.getSystemName());
        verify(repository).findAllBySystem(system);
        verify(additionalInfoService).findByClient(client);
    }

    @Test
    void changePersonalInfoSuccess() {
        ChangeClientInfoRequestDto request = ApiUtils.changeClientInfoRequestDto();
        AdditionalInfo info = DbUtils.additionalInfo().setInfo(NEW_ADDITIONAL_INFO_VALUE);
        Client client = DbUtils.client();
        ClientDto expected = ApiUtils.clientDto();
        expected.setName(NEW_NAME);
        expected.setSurname(NEW_SURNAME);
        expected.getAdditionalInfo().get(0).setValue(NEW_ADDITIONAL_INFO_VALUE);

        when(repository.findByExternalId(any())).thenReturn(Optional.of(client));
        when(additionalInfoService.findByClient(any())).thenReturn(Collections.singletonList(info));
        when(repository.existsBySystemAndEmail(any(), any())).thenReturn(Boolean.FALSE);
        when(additionalInfoService.changeValue(any(), any())).thenReturn(info);

        ClientDto actual = service.changePersonalInfo(request, Boolean.TRUE);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(client.getExternalId());
        verify(additionalInfoService).findByClient(client);
        verify(repository).existsBySystemAndEmail(client.getSystem(), request.getClientInfo().getNewValues().get(
            ChangePersonalInfo.NEW_EMAIL));
        verify(additionalInfoService).changeValue(info, NEW_ADDITIONAL_INFO_VALUE);
    }
}

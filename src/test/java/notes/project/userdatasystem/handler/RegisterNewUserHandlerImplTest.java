package notes.project.userdatasystem.handler;

import dto.integration.kafka.UserInfoDto;
import notes.project.userdatasystem.handler.impl.RegisterNewUserHandlerImpl;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.service.api.AdditionalInfoService;
import notes.project.userdatasystem.service.api.AdditionalInfoTypeService;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.service.api.SystemService;
import notes.project.userdatasystem.utils.DbUtils;
import notes.project.userdatasystem.utils.IntegrationUtils;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationAdditionalInfoMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationAdditionalInfoTypeMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationClientMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationSystemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static notes.project.userdatasystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class RegisterNewUserHandlerImplTest {
    @Mock
    private AdditionalInfoService additionalInfoService;
    @Mock
    private AdditionalInfoTypeService additionalInfoTypeService;
    @Mock
    private SystemService systemService;
    @Mock
    private ClientService clientService;

    private RegisterNewUserHandler handler;

    @BeforeEach
    void init() {
        handler = new RegisterNewUserHandlerImpl(
            additionalInfoService,
            additionalInfoTypeService,
            systemService,
            clientService,
            Mappers.getMapper(UserRegistrationSystemMapper.class),
            Mappers.getMapper(UserRegistrationAdditionalInfoMapper.class),
            Mappers.getMapper(UserRegistrationAdditionalInfoTypeMapper.class),
            Mappers.getMapper(UserRegistrationClientMapper.class)
        );
    }

    @Test
    void handleSuccessWhenNewSystem() {
        UserInfoDto request = IntegrationUtils.userInfoDto();
        System system = DbUtils.system();
        Client client = DbUtils.client();
        AdditionalInfoType type = DbUtils.additionalInfoType();
        AdditionalInfo info = DbUtils.additionalInfo();

        when(systemService.existsBySystemName(any())).thenReturn(Boolean.FALSE);
        when(systemService.save(any())).thenReturn(system);
        when(clientService.save(any())).thenReturn(client);
        when(additionalInfoTypeService.existsBySystemAndType(any(), any())).thenReturn(Boolean.FALSE);
        when(additionalInfoTypeService.save(any())).thenReturn(type);
        when(additionalInfoTypeService.findBySystemAndType(any(), any())).thenReturn(type);
        when(additionalInfoService.save(any())).thenReturn(info);

        handler.handle(request);

        verify(systemService).existsBySystemName(system.getSystemName());
        verify(systemService).save(system.setId(null));
        system.setId(ID);
        verify(clientService).save(client.setId(null));
        client.setId(ID);
        verify(additionalInfoTypeService).existsBySystemAndType(system, type.getType());
        verify(additionalInfoTypeService).save(type.setId(null));
        type.setId(ID);
        verify(additionalInfoTypeService).findBySystemAndType(system, type.getType());
        verify(additionalInfoService).save(info.setId(null));
    }

    @Test
    void handleSuccessWhenSystemExists() {
        UserInfoDto request = IntegrationUtils.userInfoDto();
        System system = DbUtils.system();
        Client client = DbUtils.client();
        AdditionalInfoType type = DbUtils.additionalInfoType();
        AdditionalInfo info = DbUtils.additionalInfo();

        when(systemService.existsBySystemName(any())).thenReturn(Boolean.TRUE);
        when(systemService.findBySystemName(any())).thenReturn(system);
        when(clientService.save(any())).thenReturn(client);
        when(additionalInfoTypeService.existsBySystemAndType(any(), any())).thenReturn(Boolean.TRUE);
        when(additionalInfoTypeService.findBySystemAndType(any(), any())).thenReturn(type);
        when(additionalInfoService.save(any())).thenReturn(info);

        handler.handle(request);

        verify(systemService).existsBySystemName(system.getSystemName());
        verify(systemService).findBySystemName(system.getSystemName());
        verify(clientService).save(client.setId(null));
        client.setId(ID);
        verify(additionalInfoTypeService).existsBySystemAndType(system, type.getType());
        verify(additionalInfoTypeService).findBySystemAndType(system, type.getType());
        verify(additionalInfoService).save(info.setId(null));
    }
}

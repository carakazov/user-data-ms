package notes.project.userdatasystem.handler.impl;

import javax.transaction.Transactional;

import dto.integration.kafka.UserInfoDto;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.handler.RegisterNewUserHandler;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.service.api.AdditionalInfoService;
import notes.project.userdatasystem.service.api.AdditionalInfoTypeService;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.service.api.SystemService;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationAdditionalInfoMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationAdditionalInfoTypeMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationClientMapper;
import notes.project.userdatasystem.utils.mapper.handler.UserRegistrationSystemMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterNewUserHandlerImpl implements RegisterNewUserHandler {
    private final AdditionalInfoService additionalInfoService;
    private final AdditionalInfoTypeService additionalInfoTypeService;
    private final SystemService systemService;
    private final ClientService clientService;
    private final UserRegistrationSystemMapper userRegistrationSystemMapper;
    private final UserRegistrationAdditionalInfoMapper userRegistrationAdditionalInfoMapper;
    private final UserRegistrationAdditionalInfoTypeMapper userRegistrationAdditionalInfoTypeMapper;
    private final UserRegistrationClientMapper userRegistrationClientMapper;

    @Override
    @Transactional
    public void handle(UserInfoDto userInfo) {
        System system = proceedSystem(userInfo);
        Client client = clientService.save(userRegistrationClientMapper.to(userInfo, system));
        proceedTypes(userInfo, system);
        saveAdditionalInfo(userInfo, client, system);
        int a = 5;
    }

    private void saveAdditionalInfo(UserInfoDto userInfo, Client client, System system) {
        userInfo.getAdditionalInfo().forEach(item -> {
            AdditionalInfoType type = additionalInfoTypeService.findBySystemAndType(system, item.getField());
            additionalInfoService.save(userRegistrationAdditionalInfoMapper.to(item, type, client));
        });
    }

    private System proceedSystem(UserInfoDto userInfo) {
        if(Boolean.TRUE.equals(systemService.existsBySystemName(userInfo.getSystemName()))) {
            return systemService.findBySystemName(userInfo.getSystemName());
        }
        System system = userRegistrationSystemMapper.to(userInfo);
        return systemService.save(system);
    }

    private void proceedTypes(UserInfoDto userInfo, System system) {
        userInfo.getAdditionalInfo().forEach(item -> {
            if(Boolean.FALSE.equals(additionalInfoTypeService.existsBySystemAndType(system, item.getField()))) {
                additionalInfoTypeService.save(userRegistrationAdditionalInfoTypeMapper.to(item, system));
            }
        });
    }

}

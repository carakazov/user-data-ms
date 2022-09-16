package notes.project.userdatasystem.utils.mapper.handler;

import dto.integration.kafka.UserInfoDto;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "system", source = "system")
    @Mapping(target = "name", source = "userInfo.name")
    @Mapping(target = "surname", source = "userInfo.surname")
    @Mapping(target = "middleName", source = "userInfo.middleName")
    @Mapping(target = "email", source = "userInfo.email")
    @Mapping(target = "externalId", source = "userInfo.externalId")
    @Mapping(target = "dateOfBirth", source = "userInfo.dateOfBirth")
    @Mapping(target = "registrationDate", source = "userInfo.registrationDate")
    Client to(UserInfoDto userInfo, System system);
}

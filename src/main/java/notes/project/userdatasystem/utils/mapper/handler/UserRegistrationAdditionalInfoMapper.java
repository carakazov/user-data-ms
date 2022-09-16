package notes.project.userdatasystem.utils.mapper.handler;

import dto.integration.kafka.UserInfoAdditionalInfoDto;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationAdditionalInfoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "value", expression = "java(info.getValue().toString())")
    AdditionalInfo to(UserInfoAdditionalInfoDto info, AdditionalInfoType type, Client client);
}

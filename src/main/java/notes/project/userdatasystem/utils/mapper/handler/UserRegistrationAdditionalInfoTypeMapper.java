package notes.project.userdatasystem.utils.mapper.handler;

import dto.integration.kafka.UserInfoAdditionalInfoDto;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.System;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationAdditionalInfoTypeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "system", source = "system")
    @Mapping(target = "type", source = "info.field")
    AdditionalInfoType to(UserInfoAdditionalInfoDto info, System system);
}

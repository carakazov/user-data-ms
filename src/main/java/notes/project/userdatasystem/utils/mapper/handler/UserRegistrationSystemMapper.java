package notes.project.userdatasystem.utils.mapper.handler;

import dto.integration.kafka.UserInfoDto;
import notes.project.userdatasystem.model.System;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserRegistrationSystemMapper {
    System to(UserInfoDto source);
}

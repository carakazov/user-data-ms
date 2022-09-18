package notes.project.userdatasystem.utils.mapper.api;

import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.System;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalInfoTypeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "system", source = "system")
    @Mapping(target = "type", source = "type")
    AdditionalInfoType to(System system, String type);
}

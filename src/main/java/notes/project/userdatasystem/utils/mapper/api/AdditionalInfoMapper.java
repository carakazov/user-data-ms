package notes.project.userdatasystem.utils.mapper.api;

import java.util.List;

import notes.project.userdatasystem.dto.AdditionalInfoDto;
import notes.project.userdatasystem.model.AdditionalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "type", source = "type.type")
    @Mapping(target = "value", source = "info")
    AdditionalInfoDto to(AdditionalInfo source);

    List<AdditionalInfoDto> to(List<AdditionalInfo> source);
}

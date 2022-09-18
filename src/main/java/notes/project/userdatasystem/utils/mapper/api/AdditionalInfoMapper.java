package notes.project.userdatasystem.utils.mapper.api;

import java.util.List;

import liquibase.pro.packaged.M;
import notes.project.userdatasystem.dto.AdditionalInfoDto;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "type", source = "type.type")
    @Mapping(target = "value", source = "info")
    AdditionalInfoDto to(AdditionalInfo source);

    List<AdditionalInfoDto> to(List<AdditionalInfo> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "type")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "info", source = "info")
    AdditionalInfo from(AdditionalInfoType type, Client client, String info);
}

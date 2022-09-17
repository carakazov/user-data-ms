package notes.project.userdatasystem.utils.mapper.api;

import java.util.List;

import liquibase.pro.packaged.C;
import notes.project.userdatasystem.dto.ClientDto;
import notes.project.userdatasystem.dto.ClientListItemDto;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AdditionalInfoMapper.class)
public interface ClientDtoMapper {

    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "surname", source = "client.surname")
    @Mapping(target = "middleName", source = "client.middleName")
    @Mapping(target = "birthDate", source = "client.dateOfBirth")
    @Mapping(target = "externalId", source = "client.externalId")
    @Mapping(target = "additionalInfo", source = "info")
    ClientDto to(Client client, List<AdditionalInfo> info);

    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "surname", source = "client.surname")
    @Mapping(target = "middleName", source = "client.middleName")
    @Mapping(target = "birthDate", source = "client.dateOfBirth")
    @Mapping(target = "externalId", source = "client.externalId")
    @Mapping(target = "additionalInfo", source = "info")
    @Mapping(target = "registrationDate", source = "client.registrationDate")
    ClientListItemDto toItem(Client client, List<AdditionalInfo> info);
}

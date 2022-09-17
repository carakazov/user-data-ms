package notes.project.userdatasystem.utils.mapper.mail;

import javax.inject.Inject;

import notes.project.userdatasystem.config.ApplicationProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.mail.SimpleMailMessage;

@Mapper(componentModel = "spring")
public abstract class SimpleMailMessageMapper {
    @Inject
    protected ApplicationProperties properties;

    @Mapping(target = "from", expression = "java(properties.getSender())")
    @Mapping(target = "subject", expression = "java(properties.getTopic())")
    @Mapping(target = "to", expression = "java(client)")
    public abstract SimpleMailMessage to(String text, String client);
}

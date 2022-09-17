package notes.project.userdatasystem.service.integration.impl;

import java.io.StringReader;
import java.util.function.Consumer;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dto.integration.kafka.RestorePasswordRequestDto;
import dto.integration.kafka.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notes.project.userdatasystem.exception.UserDataSystemException;
import notes.project.userdatasystem.handler.RestorePasswordHandler;
import notes.project.userdatasystem.service.integration.RestorePasswordListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestorePasswordListenerImpl implements RestorePasswordListener {
    private final Unmarshaller unmarshaller;
    private final RestorePasswordHandler handler;

    @Override
    public void listen(String message) {
        try {
            log.info("Accepted message {}", message);
            StringReader stringReader = new StringReader(message);
            RestorePasswordRequestDto restorePasswordRequest = (RestorePasswordRequestDto) unmarshaller.unmarshal(stringReader);
            handler.handle(restorePasswordRequest);
        } catch(JAXBException exception) {
            log.error("Can not unmarshall message {}", message);
            throw new UserDataSystemException(exception.getMessage());
        } catch(Exception exception) {
            log.error("Exception occurred {}", exception.getMessage());
            throw new UserDataSystemException(exception.getMessage());
        }
    }

    @Bean
    public Consumer<String> restorePasswordListener() {
        return this::listen;
    }
}

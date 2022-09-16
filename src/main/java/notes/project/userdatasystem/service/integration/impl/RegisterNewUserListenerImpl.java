package notes.project.userdatasystem.service.integration.impl;

import java.io.StringReader;
import java.util.function.Consumer;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dto.integration.kafka.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notes.project.userdatasystem.exception.UserDataSystemException;
import notes.project.userdatasystem.handler.RegisterNewUserHandler;
import notes.project.userdatasystem.service.integration.RegisterNewUserListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterNewUserListenerImpl implements RegisterNewUserListener {
    private final Unmarshaller unmarshaller;
    private final RegisterNewUserHandler handler;

    @Override
    public void listen(String message) {
        try {
            log.info("Accepted message {}", message);
            StringReader stringReader = new StringReader(message);
            UserInfoDto userInfo = (UserInfoDto) unmarshaller.unmarshal(stringReader);
            handler.handle(userInfo);
        } catch(JAXBException exception) {
            log.error("Can not unmarshall message {}", message);
            throw new UserDataSystemException(exception.getMessage());
        } catch(Exception exception) {
            log.error("Exception occurred {}", exception.getMessage());
            throw new UserDataSystemException(exception.getMessage());
        }
    }

    @Bean
    public Consumer<String> newUserListener() {
        return this::listen;
    }
}

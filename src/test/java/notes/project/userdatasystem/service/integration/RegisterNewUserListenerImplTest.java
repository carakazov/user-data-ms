package notes.project.userdatasystem.service.integration;

import java.io.StringReader;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dto.integration.kafka.RestorePasswordRequestDto;
import dto.integration.kafka.UserInfoAdditionalInfoDto;
import dto.integration.kafka.UserInfoDto;
import notes.project.userdatasystem.exception.UserDataSystemException;
import notes.project.userdatasystem.handler.RegisterNewUserHandler;
import notes.project.userdatasystem.service.integration.impl.RegisterNewUserListenerImpl;
import notes.project.userdatasystem.utils.IntegrationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static notes.project.userdatasystem.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterNewUserListenerImplTest {
    private Unmarshaller unmarshaller;
    @Mock
    private RegisterNewUserHandler handler;
    @Captor
    private ArgumentCaptor<UserInfoDto> userInfoDtoCaptor;

    private RegisterNewUserListener listener;

    @BeforeEach
    void init() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(
            UserInfoDto.class,
            UserInfoAdditionalInfoDto.class,
            RestorePasswordRequestDto.class
        );
        unmarshaller = context.createUnmarshaller();
        listener = new RegisterNewUserListenerImpl(unmarshaller, handler);
    }

    @Test
    void listerSuccess() {
        UserInfoDto expected = IntegrationUtils.userInfoDto();

        assertDoesNotThrow(() -> listener.listen(REGISTER_NEW_USER_CORRECT_MESSAGE));

        verify(handler).handle(userInfoDtoCaptor.capture());

        UserInfoDto actual = userInfoDtoCaptor.getValue();

        assertEquals(expected.getSystemName(), actual.getSystemName());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals(expected.getAdditionalInfo().get(0).getField(), actual.getAdditionalInfo().get(0).getField());
        assertEquals(expected.getAdditionalInfo().get(0).getValue(), actual.getAdditionalInfo().get(0).getValue());
        assertEquals(expected.getExternalId(), actual.getExternalId());
        assertEquals(expected.getRegistrationDate(), actual.getRegistrationDate());
    }

    @Test
    void listenThrowWhenIncorrectMessage() {
        assertThrows(
            UserDataSystemException.class,
            () -> listener.listen("Incorrect message")
        );

        verifyNoInteractions(handler);
    }
}

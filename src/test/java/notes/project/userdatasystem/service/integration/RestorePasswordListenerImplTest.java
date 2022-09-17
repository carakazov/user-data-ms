package notes.project.userdatasystem.service.integration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dto.integration.kafka.RestorePasswordRequestDto;
import dto.integration.kafka.UserInfoAdditionalInfoDto;
import dto.integration.kafka.UserInfoDto;
import notes.project.userdatasystem.exception.UserDataSystemException;
import notes.project.userdatasystem.handler.RestorePasswordHandler;
import notes.project.userdatasystem.service.integration.impl.RestorePasswordListenerImpl;
import notes.project.userdatasystem.utils.IntegrationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static notes.project.userdatasystem.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class RestorePasswordListenerImplTest {
    @Mock
    private RestorePasswordHandler handler;
    @Captor
    private ArgumentCaptor<RestorePasswordRequestDto> captor;

    private RestorePasswordListener listener;

    @BeforeEach
    void init() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(
            UserInfoDto.class,
            UserInfoAdditionalInfoDto.class,
            RestorePasswordRequestDto.class
        );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        listener = new RestorePasswordListenerImpl(unmarshaller, handler);
    }

    @Test
    void listenSuccess() {
        RestorePasswordRequestDto expected = IntegrationUtils.restorePasswordRequestDto();

        assertDoesNotThrow(() -> listener.listen(RESTORE_PASSWORD_CORRECT_MESSAGE));

        verify(handler).handle(captor.capture());

        RestorePasswordRequestDto actual = captor.getValue();

        assertEquals(expected.getClientId(), actual.getClientId());
        assertEquals(expected.getRestoreCode(), actual.getRestoreCode());
        assertEquals(expected.getContact(), actual.getContact());
    }

    @Test
    void listenWhenJaxbExceptionThrow() {
        assertThrows(
            UserDataSystemException.class,
            () -> listener.listen("Incorrect message")
        );

        verifyNoInteractions(handler);
    }

}

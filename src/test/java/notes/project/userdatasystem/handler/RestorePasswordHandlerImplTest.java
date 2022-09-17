package notes.project.userdatasystem.handler;

import dto.integration.kafka.RestorePasswordRequestDto;
import notes.project.userdatasystem.handler.impl.RestorePasswordHandlerImpl;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.utils.DbUtils;
import notes.project.userdatasystem.utils.IntegrationUtils;
import notes.project.userdatasystem.utils.mail.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestorePasswordHandlerImplTest {
    @Mock
    private ClientService clientService;
    @Mock
    private MailSender mailSender;

    private RestorePasswordHandler handler;

    @BeforeEach
    void init() {
        handler = new RestorePasswordHandlerImpl(clientService, mailSender);
    }

    @Test
    void handleSuccess() {
        Client client = DbUtils.client();
        RestorePasswordRequestDto request = IntegrationUtils.restorePasswordRequestDto();
        when(clientService.findBySystemNameAndEmail(any(), any())).thenReturn(client);

        handler.handle(request);

        verify(clientService).findBySystemNameAndEmail(request.getClientId(), request.getContact());
        verify(mailSender).sendEmail(request, client);
    }
}

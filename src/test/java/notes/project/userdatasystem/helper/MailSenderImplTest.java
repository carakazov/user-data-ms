package notes.project.userdatasystem.helper;

import java.util.concurrent.ExecutorService;

import dto.integration.kafka.RestorePasswordRequestDto;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.utils.DbUtils;
import notes.project.userdatasystem.utils.IntegrationUtils;
import notes.project.userdatasystem.utils.TestUtils;
import notes.project.userdatasystem.utils.mail.MailSender;
import notes.project.userdatasystem.utils.mail.MailTemplateHelper;
import notes.project.userdatasystem.utils.mail.impl.MailSenderImpl;
import notes.project.userdatasystem.utils.mapper.mail.SimpleMailMessageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static notes.project.userdatasystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class MailSenderImplTest {
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private ExecutorService executorService;
    @Mock
    private MailTemplateHelper mailTemplateHelper;

    private MailSender sender;

    @BeforeEach
    void init() {
        sender = new MailSenderImpl(
            javaMailSender,
            executorService,
            mailTemplateHelper,
            TestUtils.getComplexMapper(SimpleMailMessageMapper.class)
        );
    }

    @Test
    void sendEmailSuccess() {
        when(mailTemplateHelper.resolveMailText(any(), any())).thenReturn(RESTORE_PASSWORD_MESSAGE_RESOLVED);

        RestorePasswordRequestDto request = IntegrationUtils.restorePasswordRequestDto();
        Client client = DbUtils.client();

        sender.sendEmail(request, client);

        verify(mailTemplateHelper).resolveMailText(request.getRestoreCode(), client.getExternalId());
    }
}

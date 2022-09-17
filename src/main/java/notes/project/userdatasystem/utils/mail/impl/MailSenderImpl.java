package notes.project.userdatasystem.utils.mail.impl;

import java.util.concurrent.ExecutorService;

import dto.integration.kafka.RestorePasswordRequestDto;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.utils.mail.MailSender;
import notes.project.userdatasystem.utils.mail.MailTemplateHelper;
import notes.project.userdatasystem.utils.mapper.mail.SimpleMailMessageMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender javaMailSender;
    private final ExecutorService executorService;
    private final MailTemplateHelper mailTemplateHelper;
    private final SimpleMailMessageMapper simpleMailMessageMapper;

    @Override
    public void sendEmail(RestorePasswordRequestDto restoreRequest, Client client) {
        String message = mailTemplateHelper.resolveMailText(restoreRequest.getRestoreCode(), client.getExternalId());
        SimpleMailMessage mail = simpleMailMessageMapper.to(message, client.getEmail());
        executorService.execute(() -> javaMailSender.send(mail));
    }
}

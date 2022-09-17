package notes.project.userdatasystem.handler.impl;

import javax.transaction.Transactional;

import dto.integration.kafka.RestorePasswordRequestDto;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.handler.RestorePasswordHandler;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.service.api.ClientService;
import notes.project.userdatasystem.utils.mail.MailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestorePasswordHandlerImpl implements RestorePasswordHandler {
    private final ClientService clientService;
    private final MailSender mailSender;

    @Override
    @Transactional
    public void handle(RestorePasswordRequestDto restorePasswordRequest) {
        Client client = clientService.findBySystemNameAndEmail(restorePasswordRequest.getClientId(), restorePasswordRequest.getContact());
        mailSender.sendEmail(restorePasswordRequest, client);
    }
}

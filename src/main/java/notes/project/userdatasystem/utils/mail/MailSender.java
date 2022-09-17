package notes.project.userdatasystem.utils.mail;

import dto.integration.kafka.RestorePasswordRequestDto;
import notes.project.userdatasystem.model.Client;

public interface MailSender {
    void sendEmail(RestorePasswordRequestDto restoreRequest, Client client);
}

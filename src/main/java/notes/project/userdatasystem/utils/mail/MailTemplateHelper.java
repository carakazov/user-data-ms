package notes.project.userdatasystem.utils.mail;

import java.util.UUID;

public interface MailTemplateHelper {
    String resolveMailText(String restoreCode, UUID externalId);
}

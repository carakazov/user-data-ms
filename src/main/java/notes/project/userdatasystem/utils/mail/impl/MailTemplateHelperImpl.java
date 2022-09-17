package notes.project.userdatasystem.utils.mail.impl;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.config.ApplicationProperties;
import notes.project.userdatasystem.utils.mail.MailTemplateHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailTemplateHelperImpl implements MailTemplateHelper {
    private final ApplicationProperties properties;

    private static final String RESTORE_CODE_PLACEHOLDER = "{restoreCode}";
    private static final String CLIENT_EXTERNAL_ID_PLACEHOLDER = "{clientExternalId}";

    @Override
    public String resolveMailText(String restoreCode, UUID externalId) {
        return properties.getRestorePasswordMessage()
            .replace(RESTORE_CODE_PLACEHOLDER, restoreCode)
            .replace(CLIENT_EXTERNAL_ID_PLACEHOLDER, externalId.toString());
    }
}

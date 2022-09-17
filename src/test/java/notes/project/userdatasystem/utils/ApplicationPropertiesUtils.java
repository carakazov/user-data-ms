package notes.project.userdatasystem.utils;

import lombok.experimental.UtilityClass;
import notes.project.userdatasystem.config.ApplicationProperties;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties()
            .setSender(SENDER)
            .setTopic(TOPIC);
    }

    public static ApplicationProperties applicationPropertiesForMailTemplateHelper() {
        return new ApplicationProperties().setRestorePasswordMessage(RESTORE_PASSWORD_MESSAGE_TEMPLATE);
    }
}

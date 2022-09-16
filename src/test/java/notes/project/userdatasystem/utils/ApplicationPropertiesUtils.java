package notes.project.userdatasystem.utils;

import lombok.experimental.UtilityClass;
import notes.project.userdatasystem.config.ApplicationProperties;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }
}

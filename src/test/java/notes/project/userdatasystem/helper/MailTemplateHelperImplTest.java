package notes.project.userdatasystem.helper;

import notes.project.userdatasystem.utils.ApplicationPropertiesUtils;
import notes.project.userdatasystem.utils.mail.MailTemplateHelper;
import notes.project.userdatasystem.utils.mail.impl.MailTemplateHelperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static notes.project.userdatasystem.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MailTemplateHelperImplTest {
    private MailTemplateHelper helper;

    @BeforeEach
    void init() {
        helper = new MailTemplateHelperImpl(ApplicationPropertiesUtils.applicationPropertiesForMailTemplateHelper());
    }

    @Test
    void resolveMailTextSuccess() {
        String actual = helper.resolveMailText(RESTORE_PASSWORD_CODE, EXTERNAL_ID);

        assertEquals(RESTORE_PASSWORD_MESSAGE_RESOLVED, actual);
    }
}

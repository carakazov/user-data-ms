package notes.project.userdatasystem.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataConstants {
    //Common
    public static Long ID = 1L;
    public static Long ID_2 = 2L;
    public static final String EXCEPTION_CODE = "unexpectedErrorWhileCreationOperation";
    public static final String EXCEPTION_MESSAGE = "exception message";

    //System
    public static final String SYSTEM_NAME = "system name";

    //Additional info
    public static final String ADDITIONAL_INFO_TYPE = "type";
    public static final String ADDITIONAL_INFO_VALUE = "value";

    //Client
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MIDDLE_NAME = "middle name";
    public static final String EMAIL = "email@box.ru";
    public static final UUID EXTERNAL_ID = UUID.fromString("f725f44e-4377-4a9a-89b5-60086d8a91e1");
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 8, 21);
    public static final LocalDateTime REGISTRATION_DATE = LocalDateTime.of(2022, 10, 12, 10, 10, 10);


    public static final String REGISTER_NEW_USER_CORRECT_MESSAGE =
        "<userInfo>\n" +
        "    <systemName>system name</systemName>\n" +
        "    <name>name</name>\n" +
        "    <surname>surname</surname>\n" +
        "    <middleName>middle name</middleName>\n" +
        "    <email>email@box.ru</email>\n" +
        "    <dateOfBirth>2000-08-21</dateOfBirth>\n" +
        "    <additionalInfo>\n" +
        "        <field>type</field>\n" +
        "        <value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">value</value>\n" +
        "    </additionalInfo>\n" +
        "    <externalId>f725f44e-4377-4a9a-89b5-60086d8a91e1</externalId>\n" +
        "    <registrationDate>2022-10-12T10:10:10</registrationDate>\n" +
        "</userInfo>";

    public static final String RESTORE_PASSWORD_CODE = "8IIH01lmrGbA6c2c3IyBmUegz";
    public static final String RESTORE_PASSWORD_MESSAGE_TEMPLATE = "Для подтверждения нового пароля перейдите по ссылке http://localhost:8080/client/{restoreCode}/{clientExternalId}";
    public static final String RESTORE_PASSWORD_MESSAGE_RESOLVED = "Для подтверждения нового пароля перейдите по ссылке http://localhost:8080/client/8IIH01lmrGbA6c2c3IyBmUegz/f725f44e-4377-4a9a-89b5-60086d8a91e1";
    public static final String SENDER = "admin.box@mail.ru";
    public static final String TOPIC = "password restoring";

    public static final String RESTORE_PASSWORD_CORRECT_MESSAGE =
        "<restorePasswordRequest>\n" +
        "    <clientId>system name</clientId>\n" +
        "    <restoreCode>8IIH01lmrGbA6c2c3IyBmUegz</restoreCode>\n" +
        "    <contact>email@box.ru</contact>\n" +
        "</restorePasswordRequest>\n";
}

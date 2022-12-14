package notes.project.userdatasystem.utils;

import dto.integration.kafka.RestorePasswordRequestDto;
import dto.integration.kafka.UserInfoAdditionalInfoDto;
import dto.integration.kafka.UserInfoDto;
import org.springframework.mail.SimpleMailMessage;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

public class IntegrationUtils {
    public static UserInfoAdditionalInfoDto userInfoAdditionalInfoDto() {
        UserInfoAdditionalInfoDto dto = new UserInfoAdditionalInfoDto();
        dto.setField(ADDITIONAL_INFO_TYPE);
        dto.setValue(ADDITIONAL_INFO_VALUE);
        return dto;
    }

    public static UserInfoDto userInfoDto() {
        UserInfoDto dto = new UserInfoDto();
        dto.setSystemName(SYSTEM_NAME);
        dto.setName(NAME);
        dto.setSurname(SURNAME);
        dto.setMiddleName(MIDDLE_NAME);
        dto.setEmail(EMAIL);
        dto.setDateOfBirth(DATE_OF_BIRTH);
        dto.setExternalId(EXTERNAL_ID);
        dto.setRegistrationDate(REGISTRATION_DATE);
        dto.getAdditionalInfo().add(userInfoAdditionalInfoDto());
        return dto;
    }

    public static RestorePasswordRequestDto restorePasswordRequestDto() {
        RestorePasswordRequestDto dto = new RestorePasswordRequestDto();
        dto.setClientId(SYSTEM_NAME);
        dto.setRestoreCode(RESTORE_PASSWORD_CODE);
        dto.setContact(EMAIL);
        return dto;
    }
}

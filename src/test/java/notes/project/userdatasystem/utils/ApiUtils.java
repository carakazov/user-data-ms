package notes.project.userdatasystem.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.userdatasystem.dto.*;
import notes.project.userdatasystem.exception.ExceptionCode;
import notes.project.userdatasystem.exception.ValidationException;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static ClientDto clientDto() {
        return new ClientDto()
            .setName(NAME)
            .setSurname(SURNAME)
            .setMiddleName(MIDDLE_NAME)
            .setBirthDate(DATE_OF_BIRTH)
            .setExternalId(EXTERNAL_ID)
            .setAdditionalInfo(Collections.singletonList(additionalInfoDto()));
    }

    public static AdditionalInfoDto additionalInfoDto() {
        return new AdditionalInfoDto()
            .setType(ADDITIONAL_INFO_TYPE)
            .setValue(ADDITIONAL_INFO_VALUE);
    }

    public static ErrorDto errorDto() {
        return new ErrorDto()
                .setCode(EXCEPTION_CODE)
                .setMessage(EXCEPTION_MESSAGE);
    }

    public static ValidationErrorDto validationErrorDto() {
        return new ValidationErrorDto(Collections.singletonList(errorDto()));
    }

    public static ValidationException validationException() {
        ValidationException validationException = new ValidationException();
        validationException.addCode(ExceptionCode.INTERNAL_ERROR);
        return validationException;
    }
}

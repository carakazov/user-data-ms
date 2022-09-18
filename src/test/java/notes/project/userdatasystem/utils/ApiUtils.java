package notes.project.userdatasystem.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.userdatasystem.dto.*;
import notes.project.userdatasystem.exception.ExceptionCode;
import notes.project.userdatasystem.exception.ValidationException;
import notes.project.userdatasystem.validation.dto.ChangeClientPersonalInfoValidationDto;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static ChangeClientPersonalInfoValidationDto changeClientPersonalInfoValidationDto() {
        return new ChangeClientPersonalInfoValidationDto()
            .setEmailAlreadyInUse(Boolean.FALSE)
            .setExistingTypes(Collections.singletonList(ADDITIONAL_INFO_TYPE))
            .setTypesFromRequest(Collections.singletonList(ADDITIONAL_INFO_TYPE))
            .setCreateNewTypes(Boolean.TRUE);
    }

    public static ChangeClientInfoRequestDto changeClientInfoRequestDto() {
        return new ChangeClientInfoRequestDto()
            .setClientExternalId(EXTERNAL_ID)
            .setClientInfo(changePersonalInfo());
    }

    public static ChangePersonalInfoDto changePersonalInfo() {
        return new ChangePersonalInfoDto()
            .setNewValues(NEW_VALUES)
            .setChangeAdditionalInfo(Collections.singletonList(changeAdditionalInfoDto()));
    }

    public static ChangeAdditionalInfoDto changeAdditionalInfoDto() {
        return new ChangeAdditionalInfoDto()
            .setType(ADDITIONAL_INFO_TYPE)
            .setNewValue(NEW_ADDITIONAL_INFO_VALUE);
    }

    public static SystemClientListResponseDto systemClientListResponseDto() {
        return new SystemClientListResponseDto()
            .setSystemName(SYSTEM_NAME)
            .setClients(Collections.singletonList(clientListItemDto()));
    }

    public static ClientListItemDto clientListItemDto() {
        ClientListItemDto dto = new ClientListItemDto();
        dto.setName(NAME);
        dto.setSurname(SURNAME);
        dto.setMiddleName(MIDDLE_NAME);
        dto.setBirthDate(DATE_OF_BIRTH);
        dto.setExternalId(EXTERNAL_ID);
        dto.setAdditionalInfo(Collections.singletonList(additionalInfoDto()));
        dto.setRegistrationDate(REGISTRATION_DATE);
        return dto;
    }

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

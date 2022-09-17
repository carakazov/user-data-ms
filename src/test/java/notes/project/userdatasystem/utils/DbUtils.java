package notes.project.userdatasystem.utils;

import lombok.experimental.UtilityClass;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.model.System;

import static notes.project.userdatasystem.utils.TestDataConstants.*;

@UtilityClass
public class DbUtils {
    public static System system() {
        return new System()
            .setId(ID)
            .setSystemName(SYSTEM_NAME);
    }

    public static Client client() {
        return new Client()
            .setId(ID)
            .setSystem(system())
            .setName(NAME)
            .setSurname(SURNAME)
            .setMiddleName(MIDDLE_NAME)
            .setEmail(EMAIL)
            .setExternalId(EXTERNAL_ID)
            .setDateOfBirth(DATE_OF_BIRTH)
            .setRegistrationDate(REGISTRATION_DATE);
    }

    public static AdditionalInfoType additionalInfoType() {
        return new AdditionalInfoType()
            .setId(ID)
            .setSystem(system())
            .setType(ADDITIONAL_INFO_TYPE);
    }

    public static AdditionalInfo additionalInfo() {
        return new AdditionalInfo()
            .setId(ID)
            .setType(additionalInfoType())
            .setClient(client())
            .setInfo(ADDITIONAL_INFO_VALUE);
    }
}

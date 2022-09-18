package notes.project.userdatasystem.validation.impl;

import java.util.Objects;

import notes.project.userdatasystem.exception.ExceptionCode;
import notes.project.userdatasystem.utils.ValidationHelper;
import notes.project.userdatasystem.validation.Validator;
import notes.project.userdatasystem.validation.dto.ChangeClientPersonalInfoValidationDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeClientPersonalInfoValidator implements Validator<ChangeClientPersonalInfoValidationDto> {
    @Override
    public void validate(ChangeClientPersonalInfoValidationDto source) {
        ValidationHelper validationHelper = ValidationHelper.getInstance();
        validationHelper.validate(
                source.getEmailAlreadyInUse(),
                Boolean.FALSE::equals,
                ExceptionCode.EMAIL_ALREADY_IN_USER
            );
        if(Boolean.FALSE.equals(source.getCreateNewTypes())) {
            validationHelper.validate(
                source,
                item -> item.getExistingTypes().containsAll(item.getTypesFromRequest()),
                ExceptionCode.UNRECOGNIZED_TYPES
            );
        }
        validationHelper.throwIfNotValid();
    }
}

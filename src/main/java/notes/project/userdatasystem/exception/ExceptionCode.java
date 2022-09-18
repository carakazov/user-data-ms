package notes.project.userdatasystem.exception;

import lombok.Getter;

public enum ExceptionCode {
    INTERNAL_ERROR("internalError"),
    WRONG_REQUEST_PARAMETERS("wrongRequestParameters"),
    RESOURCE_NOT_FOUND("resourceNotFound"),
    EMAIL_ALREADY_IN_USER("emailAlreadyInUse"),
    UNRECOGNIZED_TYPES("unrecognizedTypes");
    @Getter
    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }
}

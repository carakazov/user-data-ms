package notes.project.userdatasystem.exception;

import lombok.Getter;

public enum ExceptionCode {
    INTERNAL_ERROR("internalError"),
    WRONG_REQUEST_PARAMETERS("wrongRequestParameters"),
    RESOURCE_NOT_FOUND("resourceNotFound");
    @Getter
    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }
}

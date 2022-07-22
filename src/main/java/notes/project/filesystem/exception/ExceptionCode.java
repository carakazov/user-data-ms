package notes.project.filesystem.exception;

import lombok.Getter;

public enum ExceptionCode {
    CREATION_ERROR("unexpectedErrorWhileCreationOperation"),
    CLUSTER_DOES_NOT_EXISTS("clusterDoesNotExists"),
    DIRECTORY_DOES_NOT_EXISTS("directoryDoesNotExists"),
    INCORRECT_TITLE_LENGTH("incorrectTitleLength"),
    INCORRECT_CONTENT_LENGTH("incorrectContentLength"),
    RESOURCE_NOT_FOUND("resourceNotFound"),
    WRONG_REQUEST_PARAMETERS("wrongRequestParameters");
    @Getter
    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }
}

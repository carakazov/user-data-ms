package notes.project.filesystem.exception;

import lombok.Getter;

public enum ExceptionCode {
    CREATION_ERROR("unexpectedErrorWhileCreationOperation"),
    DELETION_ERROR("deletionError"),
    READING_ERROR("unexpectedErrorWhileReadingOperation"),
    FILE_DOES_NOT_EXISTS("fileDoesNotExist"),
    DIRECTORY_DOES_NOT_EXIST("directoryDoesNotExist"),
    CLUSTER_DOES_NOT_EXIST("clusterDoesNotExist"),
    INCORRECT_TITLE_LENGTH("incorrectTitleLength"),
    INCORRECT_CONTENT_LENGTH("incorrectContentLength"),
    RESOURCE_NOT_FOUND("resourceNotFound"),
    WRONG_REQUEST_PARAMETERS("wrongRequestParameters"),
    FILE_ALREADY_DELETED("fileAlreadyDeleted"),
    DIRECTORY_ALREADY_DELETED("directoryAlreadyDeleted"),
    CLUSTER_ALREADY_DELETED("clusterAlreadyDeleted"),
    REPLACING_ERROR("replacingError");
    @Getter
    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }
}

package notes.project.userdatasystem.exception;

import lombok.Getter;

public class UserDataSystemException extends RuntimeException {
    @Getter
    private ExceptionCode code;

    public UserDataSystemException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public UserDataSystemException(String message) {
        super(message);
    }
}

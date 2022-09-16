package notes.project.userdatasystem.exception;

import java.util.*;

public class ValidationException extends RuntimeException {
    private final List<ExceptionCode> codes = new ArrayList<>();


    public void addCode(ExceptionCode code) {
        codes.add(code);
    }

    public List<ExceptionCode> getCodes() {
        return new ArrayList<>(codes);
    }


}

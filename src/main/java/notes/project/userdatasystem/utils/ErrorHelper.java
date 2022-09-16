package notes.project.userdatasystem.utils;

import notes.project.userdatasystem.dto.ErrorDto;
import notes.project.userdatasystem.dto.ValidationErrorDto;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.exception.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorHelper {
    ValidationErrorDto from(ValidationException validationException);
    ErrorDto from(Exception exception);
    ErrorDto from(MethodArgumentNotValidException exception);
    ErrorDto from(NotFoundException exception);
}

package notes.project.filesystem.utils;

import notes.project.filesystem.dto.ErrorDto;
import notes.project.filesystem.dto.ValidationErrorDto;
import notes.project.filesystem.exception.FileSystemException;
import notes.project.filesystem.exception.ResourceNotFoundException;
import notes.project.filesystem.exception.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorHelper {
    ErrorDto from(FileSystemException exception);
    ValidationErrorDto from(ValidationException validationException);
    ErrorDto from(Exception exception);
    ErrorDto from(ResourceNotFoundException exception);
    ErrorDto from(MethodArgumentNotValidException exception);
}

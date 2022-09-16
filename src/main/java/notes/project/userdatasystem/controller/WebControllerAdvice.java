package notes.project.userdatasystem.controller;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.dto.ErrorDto;
import notes.project.userdatasystem.dto.ValidationErrorDto;
import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.exception.ValidationException;
import notes.project.userdatasystem.utils.ErrorHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class WebControllerAdvice {
    private final ErrorHelper errorHelper;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleCommonException(Exception exception) {
        ErrorDto errorDto = errorHelper.from(exception);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleNotValidArgument(MethodArgumentNotValidException exception) {
        ErrorDto errorDto = errorHelper.from(exception);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorDto> handleValidationError(ValidationException exception) {
        ValidationErrorDto validationErrorDto = errorHelper.from(exception);
        return new ResponseEntity<>(validationErrorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException exception) {
        ErrorDto errorDto = errorHelper.from(exception);
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}

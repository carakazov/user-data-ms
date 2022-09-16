package notes.project.userdatasystem.validation;

public interface Validator<T> {
    void validate(T source);
}

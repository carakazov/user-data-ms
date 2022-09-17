package notes.project.userdatasystem.handler;

import dto.integration.kafka.RestorePasswordRequestDto;

public interface RestorePasswordHandler {
    void handle(RestorePasswordRequestDto restorePasswordRequest);
}

package notes.project.userdatasystem.service.integration;

import dto.integration.kafka.RestorePasswordRequestDto;

public interface RestorePasswordListener {
    void listen(String message);
}

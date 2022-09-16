package notes.project.userdatasystem.handler;

import dto.integration.kafka.UserInfoDto;

public interface RegisterNewUserHandler {
    void handle(UserInfoDto userInfo);
}

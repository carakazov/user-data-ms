package notes.project.userdatasystem.service.api;

import notes.project.userdatasystem.model.System;

public interface SystemService {
    System save(System system);

    Boolean existsBySystemName(String systemName);

    System findBySystemName(String systemName);
}

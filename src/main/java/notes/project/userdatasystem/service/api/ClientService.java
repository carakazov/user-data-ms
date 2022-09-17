package notes.project.userdatasystem.service.api;

import java.util.UUID;

import notes.project.userdatasystem.dto.ClientDto;
import notes.project.userdatasystem.dto.SystemClientListResponseDto;
import notes.project.userdatasystem.model.Client;

public interface ClientService {
    Client save(Client client);

    Client findBySystemNameAndEmail(String systemName, String email);

    Client findClientByExternalId(UUID externalId);

    ClientDto getSingleClient(UUID externalId);

    SystemClientListResponseDto getAllClientsOfSystem(String systemName);
}

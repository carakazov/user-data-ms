package notes.project.userdatasystem.service.api;

import notes.project.userdatasystem.model.Client;

public interface ClientService {
    Client save(Client client);

    Client findBySystemNameAndEmail(String systemName, String email);
}

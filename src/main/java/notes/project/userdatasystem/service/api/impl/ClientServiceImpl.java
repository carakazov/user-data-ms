package notes.project.userdatasystem.service.api.impl;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.repository.ClientRepository;
import notes.project.userdatasystem.service.api.ClientService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    @Override
    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }
}

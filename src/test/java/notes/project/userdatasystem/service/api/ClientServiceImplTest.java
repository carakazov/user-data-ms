package notes.project.userdatasystem.service.api;

import notes.project.userdatasystem.model.Client;
import notes.project.userdatasystem.repository.ClientRepository;
import notes.project.userdatasystem.service.api.impl.ClientServiceImpl;
import notes.project.userdatasystem.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository repository;

    private ClientService service;

    @BeforeEach
    void init() {
        service = new ClientServiceImpl(repository);
    }

    @Test
    void saveSuccess() {
        Client request = DbUtils.client().setId(null);
        Client expected = DbUtils.client();

        when(repository.save(any())).thenReturn(expected);

        Client actual = service.save(request);

        assertEquals(expected, actual);

        verify(repository).save(request);
    }
}

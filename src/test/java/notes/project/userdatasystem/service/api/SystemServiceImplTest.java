package notes.project.userdatasystem.service.api;

import java.util.Optional;

import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.System;
import notes.project.userdatasystem.repository.SystemRepository;
import notes.project.userdatasystem.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import notes.project.userdatasystem.service.api.impl.SystemServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static notes.project.userdatasystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class SystemServiceImplTest {
    @Mock
    private SystemRepository repository;

    private SystemService service;

    @BeforeEach
    void init() {
        service = new SystemServiceImpl(repository);
    }

    @Test
    void saveSuccess() {
        System system = DbUtils.system().setId(null);

        System expected = DbUtils.system();

        when(repository.save(any())).thenReturn(expected);

        System actual = service.save(system);

        assertEquals(expected, actual);

        verify(repository).save(system);
    }

    @Test
    void existsBySystemNameSuccess() {
        when(repository.existsBySystemName(any())).thenReturn(Boolean.TRUE);

        assertTrue(service.existsBySystemName(SYSTEM_NAME));

        verify(repository).existsBySystemName(SYSTEM_NAME);
    }

    @Test
    void findBySystemNameSuccess() {
        System expected = DbUtils.system();

        when(repository.findBySystemName(any())).thenReturn(Optional.of(expected));

        System actual = service.findBySystemName(SYSTEM_NAME);

        assertEquals(expected, actual);

        verify(repository).findBySystemName(SYSTEM_NAME);
    }

    @Test
    void findBySystemNameWhenThrow() {
        when(repository.findBySystemName(any())).thenReturn(Optional.empty());

        assertThrows(
            NotFoundException.class,
            () -> service.findBySystemName(SYSTEM_NAME)
        );

        verify(repository).findBySystemName(SYSTEM_NAME);
    }
}

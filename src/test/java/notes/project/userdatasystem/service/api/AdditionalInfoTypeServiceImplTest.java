package notes.project.userdatasystem.service.api;

import java.util.Optional;

import notes.project.userdatasystem.exception.NotFoundException;
import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.model.AdditionalInfoType;
import notes.project.userdatasystem.repository.AdditionalInfoTypeRepository;
import notes.project.userdatasystem.service.api.impl.AdditionalInfoTypeServiceImpl;
import notes.project.userdatasystem.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static notes.project.userdatasystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class AdditionalInfoTypeServiceImplTest {
    @Mock
    private AdditionalInfoTypeRepository repository;

    private AdditionalInfoTypeService service;

    @BeforeEach
    void init() {
        service = new AdditionalInfoTypeServiceImpl(repository);
    }

    @Test
    void saveSuccess() {
        AdditionalInfoType request = DbUtils.additionalInfoType().setId(null);
        AdditionalInfoType expected = DbUtils.additionalInfoType();

        when(repository.save(any())).thenReturn(expected);

        AdditionalInfoType actual = service.save(request);

        assertEquals(expected, actual);

        verify(repository).save(request);
    }

    @Test
    void findBySystemAndTypeSuccess() {
        AdditionalInfoType expected = DbUtils.additionalInfoType();

        when(repository.findBySystemAndType(any(), any())).thenReturn(Optional.of(expected));

        AdditionalInfoType actual = service.findBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE);

        assertEquals(expected, actual);

        verify(repository).findBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE);
    }

    @Test
    void findBySystemAndTypeWhenThrow() {
        when(repository.findBySystemAndType(any(), any())).thenReturn(Optional.empty());

        assertThrows(
            NotFoundException.class,
            () -> service.findBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE)
        );

        verify(repository).findBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE);
    }

    @Test
    void existsBySystemAndTypeSuccess() {
        when(repository.existsBySystemAndType(any(), any())).thenReturn(Boolean.TRUE);

        assertTrue(service.existsBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE));

        verify(repository).existsBySystemAndType(DbUtils.system(), ADDITIONAL_INFO_TYPE);
    }
}

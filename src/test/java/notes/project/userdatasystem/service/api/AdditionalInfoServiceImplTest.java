package notes.project.userdatasystem.service.api;

import notes.project.userdatasystem.model.AdditionalInfo;
import notes.project.userdatasystem.repository.AdditionalInfoRepository;
import notes.project.userdatasystem.service.api.impl.AdditionalInfoServiceImpl;
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
class AdditionalInfoServiceImplTest {
    @Mock
    private AdditionalInfoRepository repository;

    private AdditionalInfoService service;

    @BeforeEach
    void init() {
        service = new AdditionalInfoServiceImpl(repository);
    }

    @Test
    void saveSuccess() {
        AdditionalInfo request = DbUtils.additionalInfo().setId(null);
        AdditionalInfo expected = DbUtils.additionalInfo();

        when(repository.save(any())).thenReturn(expected);

        AdditionalInfo actual = service.save(request);

        assertEquals(expected, actual);

        verify(repository).save(request);
    }
}

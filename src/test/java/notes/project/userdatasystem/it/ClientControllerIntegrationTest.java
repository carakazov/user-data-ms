package notes.project.userdatasystem.it;

import java.io.IOException;
import javax.inject.Inject;
import javax.transaction.Transactional;

import notes.project.userdatasystem.controller.ClientController;
import notes.project.userdatasystem.utils.DbUtils;
import notes.project.userdatasystem.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("it")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(AbstractIntegrationTest.IntegrationTestConfiguration.class)
class ClientControllerIntegrationTest extends AbstractIntegrationTest {
    private MockMvc mockMvc;

    @Inject
    private ClientController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
            .build();
    }

    @Test
    void getSingleClientSuccess() throws Exception {
        testEntityManager.merge(DbUtils.system());
        testEntityManager.merge(DbUtils.additionalInfoType());
        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.additionalInfo());

        String expected = TestUtils.getClasspathResource("/api/Client.json");
        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/client/f725f44e-4377-4a9a-89b5-60086d8a91e1"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, false);
    }
}

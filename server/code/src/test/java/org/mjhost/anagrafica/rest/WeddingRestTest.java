package org.mjhost.anagrafica.rest;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mjhost.anagrafica.repository.WeddingRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeddingRestTest extends WeddingRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindPeopleByName() {
        try {
            String queryString = URLEncoder.encode("{people(fn:\"Anne\",ln:\"Ruiz\"){firstName,sex,birthDate}}", "UTF-8");
            mockMvc
                .perform(get("/people/find?query=" + queryString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("Anne"))
                .andExpect(jsonPath("$[0].lastName").doesNotExist())
                .andExpect(jsonPath("$[0].sex").value("F"))
                .andExpect(jsonPath("$[0].birthDate").isEmpty())
                .andDo(print());
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }
}

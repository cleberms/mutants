package br.com.mutants.gene.http;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.http.json.DnaJsonRequest;
import br.com.mutants.gene.usecase.SaveDna;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {MutantController.class})
public class MutantControllerIntTest {

    @MockBean
    private SaveDna saveDna;

    @Autowired
    private MockMvc mockMvc;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnNoContent() throws Exception {

        DnaJsonRequest json = new DnaJsonRequest(new String[] { "AAAACA" });

        when(saveDna.save(any())).thenReturn(new Dna(new String[] { "AAAACA" }, true));

        mockMvcPerform(toString(json))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnForbidden() throws Exception {

        DnaJsonRequest json = new DnaJsonRequest(new String[] { "ATGACA" });

        when(saveDna.save(any())).thenReturn(new Dna(new String[] { "ATGACA" }, false));

        mockMvcPerform(toString(json))
                .andExpect(status().isForbidden());
    }

    private ResultActions mockMvcPerform(final String json) throws Exception {

        final MockHttpServletRequestBuilder builder = post("/mutant") //
                .accept(MediaType.APPLICATION_JSON) //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(json);

        return mockMvc.perform(builder);
    }

    private String toString(DnaJsonRequest json) {
        final ObjectMapper jsonMapper = new ObjectMapper();

        try {
            return jsonMapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
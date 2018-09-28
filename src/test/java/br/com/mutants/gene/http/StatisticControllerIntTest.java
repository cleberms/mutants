package br.com.mutants.gene.http;

import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.usecase.CountDnaStatistic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {StatisticController.class})
public class StatisticControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountDnaStatistic countDnaStatistic;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test public void testGetStatistic() throws Exception {
        when(countDnaStatistic.count()).thenReturn(new DnaStatistic(0L, 0L, 0d));


        mockMvcPerform()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(0)))
                .andExpect(jsonPath("$.count_human_dna", is(0)))
                .andExpect(jsonPath("$.ratio", is(0d)));
    }

    private ResultActions mockMvcPerform() throws Exception {

        final MockHttpServletRequestBuilder builder = get("/stats") //
                .accept(MediaType.APPLICATION_JSON) //
                .contentType(MediaType.APPLICATION_JSON);

        return mockMvc.perform(builder);
    }
}
package br.com.mutants.gene.http.json.mapper;

import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.http.json.DnaStatisticJson;
import br.com.mutants.gene.http.json.mapper.DnaStatisticMappers.DnaStatisticMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DnaStatisticMapperUnitTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldMapperDomainToJson() {

        DnaStatistic dnaStatistic = new DnaStatistic(40, 100, 0.4);

        DnaStatisticJson json = DnaStatisticMapper.Mapper.dnaStatisticDomainToJson(dnaStatistic);

        assertNotNull(json);
        assertEquals(dnaStatistic.getCount_human_dna(), json.getCount_human_dna());
        assertEquals(dnaStatistic.getCount_mutant_dna(), json.getCount_mutant_dna());
        assertEquals(dnaStatistic.getRatio(), json.getRatio(), 0.1);
    }
}
package br.com.mutants.gene.usecase;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.usecase.exception.NoDnaFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CountDnaStatisticUnitTest {
    @Mock
    DnaGatewayMongo mongo;
    @InjectMocks
    CountDnaStatistic countDnaStatistic;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnStatisticSuccessfully() throws Exception {

        List<Dna> dnaList = new ArrayList<>();
        dnaList.add(new Dna(new String[]{"dna"}, true));
        dnaList.add(new Dna(new String[]{"dna"}, false));
        dnaList.add(new Dna(new String[]{"dna"}, false));

        when(mongo.findAll()).thenReturn(Optional.of(dnaList));

        DnaStatistic result = countDnaStatistic.count();
        assertEquals(new DnaStatistic(1, 2, 0.5d), result);
    }

    @Test(expected = NoDnaFoundException.class)
    public void shouldReturnException() throws Exception {

        when(mongo.findAll()).thenReturn(Optional.empty());

        try {
            countDnaStatistic.count();
        } catch (NoDnaFoundException ex) {

            assertEquals("No dna was found!", ex.getMessage());

            throw ex;
        }
    }
}
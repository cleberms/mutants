package br.com.mutants.gene.gateways.mongo;

import br.com.mutants.gene.configs.SpringMongoConfiguration;
import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.gateways.exception.DnaDatabaseGatewayException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringMongoConfiguration.class, DnaGatewayMongoImpl.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DnaGatewayMongoImplIntTest {

    @Autowired
    private DnaGatewayMongo gateway;

    @Mock
    private DnaRepository dnaRepository;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveAndFindDna() throws Exception {

        Dna dna = new Dna(new String[] { "dnaSequence" }, true);

        gateway.save(dna);

        Dna savedDna = gateway.findByDna(dna.getDna());

        assertEquals(dna, savedDna);
    }

    @Test
    public void shouldSaveWithException() {
        doThrow(new RuntimeException()).when(dnaRepository).save(any(Dna.class));

        try {
            gateway.save(new Dna(new String[] { "dnaSequence" }, true));
        } catch (DnaDatabaseGatewayException ex) {
            assertEquals("Error to save dna", ex.getMessage());
            verify(dnaRepository, VerificationModeFactory.times(1)).save(any(Dna.class));
        }
    }

    @Test
    public void shouldFindByDnaWithException() {
        doThrow(new RuntimeException()).when(dnaRepository).findByDna(any());

        try {
            gateway.findByDna(new String[] { "dnaSequence" });
        } catch (DnaDatabaseGatewayException ex) {
            assertEquals("Error to find dna", ex.getMessage());
            verify(dnaRepository, VerificationModeFactory.times(1)).findByDna(any());
        }
    }

    @Test
    public void shouldFindAll() throws Exception {

        gateway.save(new Dna(new String[] { "dnaSequence" }, true));
        gateway.save(new Dna(new String[] { "dnaSequence2" }, false));
        gateway.save(new Dna(new String[] { "dnaSequence3" }, true));

        List<Dna> result = gateway.findAll();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void shouldFindAllWithException() throws Exception {

        doThrow(new RuntimeException()).when(dnaRepository).findAll();

        try {
            gateway.findAll();
        } catch (DnaDatabaseGatewayException ex) {
            assertEquals("Error to find all dna", ex.getMessage());
            verify(dnaRepository, VerificationModeFactory.times(1)).findByDna(any());
        }
    }
}
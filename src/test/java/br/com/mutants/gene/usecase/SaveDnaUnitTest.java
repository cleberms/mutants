package br.com.mutants.gene.usecase;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.gateways.exception.DnaDatabaseGatewayException;
import br.com.mutants.gene.usecase.exception.SaveDnaException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SaveDnaUnitTest {

    @Mock
    private ValidateMutantGene validateMutantGene;

    @Mock
    private DnaGatewayMongo dnaMongo;

    @InjectMocks
    private SaveDna saveDna;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveDnaSuccesfully() throws Exception {
        when(validateMutantGene.isMutant(any())).thenReturn(true);

        Dna result = saveDna.save(new Dna(new String[] { "dna" }, true));

        assertEquals(new Dna(new String[] { "dna" }, true), result);
        verify(validateMutantGene, VerificationModeFactory.times(1)).isMutant(any());
        verify(dnaMongo, VerificationModeFactory.times(1)).save(any(Dna.class));
    }

    @Test(expected = SaveDnaException.class)
    public void shouldReturnExceptionOnSaveDna(){
        Dna dna = new Dna();

        dna.setDna(new String[] { "dna" });
        dna.setMutant(true);

        when(validateMutantGene.isMutant(any())).thenReturn(true);

        doThrow(new DnaDatabaseGatewayException("save")).when(dnaMongo).save(any(Dna.class));
        Dna result = null;

        try {
            saveDna.save(dna);
        } catch (SaveDnaException ex) {
            assertEquals("Error to save DNA.", ex.getMessage());
            verify(validateMutantGene, VerificationModeFactory.times(1)).isMutant(any());
            verify(dnaMongo, VerificationModeFactory.times(1)).save(any(Dna.class));

            throw ex;
        }
    }
}
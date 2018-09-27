package br.com.mutants.gene.usecase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidateMutantGeneUnitTest {

    @InjectMocks
    private ValidateMutantGene validateMutantGene;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindMutantGenesDiagonal(){
        String[] dnaSequence = {"CTAAGC", "TCGTAG", "ATCGTA", "CTACGT", "CTCAGT", "GAACGA"};

        boolean isMutant = validateMutantGene.isMutant(dnaSequence);

        assertTrue(isMutant);
    }

    @Test
    public void shouldFindMutantGenesHorizontal(){
        String[] dnaSequence = {"CTAGTA", "TGCACT", "CATTTT", "TCAGCA", "ATCAGT", "TCGGTA"};

        boolean isMutant = validateMutantGene.isMutant(dnaSequence);

        assertTrue(isMutant);
    }

    @Test
    public void shouldFindMutantGenesVertical(){
        String[] dnaSequence = {"ATCGAT", "CGATGC", "TCAGCT", "CTATGC", "TGAGTA", "ATCTAC"};

        boolean isMutant = validateMutantGene.isMutant(dnaSequence);

        assertTrue(isMutant);
    }

    @Test
    public void shouldNotFindMutantGenes(){
        String[] dnaSequence = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

        boolean isMutant = validateMutantGene.isMutant(dnaSequence);

        assertFalse(isMutant);
    }
}

package br.com.mutants.gene.domains;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DnaStatistic {

    private long count_mutant_dna;

    private long count_human_dna;

    private double ratio;
}

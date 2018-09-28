package br.com.mutants.gene.http.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonSerialize
public class DnaStatisticJson {

    private int count_mutant_dna;

    private int count_human_dna;

    private double ratio;
}

package br.com.mutants.gene.http.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class DnaJsonRequest {

    private String[] dna;
}

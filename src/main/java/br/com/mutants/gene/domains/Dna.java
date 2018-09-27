package br.com.mutants.gene.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "dna")
@AllArgsConstructor
@NoArgsConstructor
public class Dna implements Serializable {

    private String[] dna;
    private boolean mutant;

}

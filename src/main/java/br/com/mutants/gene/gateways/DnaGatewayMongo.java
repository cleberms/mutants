package br.com.mutants.gene.gateways;

import br.com.mutants.gene.domains.Dna;

import java.util.List;

public interface DnaGatewayMongo {

    Dna findByDna(String[] dnaSequence);

    void save(Dna dna);

    List<Dna> findAll();
}

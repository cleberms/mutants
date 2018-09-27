package br.com.mutants.gene.gateways;

import br.com.mutants.gene.domains.Dna;

import java.util.List;
import java.util.Optional;

public interface DnaGatewayMongo {

    Optional<Dna> findByDna(String[] dnaSequence);

    void save(Dna dna);

    Optional<List<Dna>> findAll();
}

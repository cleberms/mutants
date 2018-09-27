package br.com.mutants.gene.gateways.mongo;

import br.com.mutants.gene.domains.Dna;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DnaRepository extends MongoRepository<Dna, String> {

    Dna findByDna(String[] dna);
}

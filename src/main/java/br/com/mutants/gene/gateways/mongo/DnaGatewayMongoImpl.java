package br.com.mutants.gene.gateways.mongo;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.gateways.exception.DnaDatabaseGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DnaGatewayMongoImpl implements DnaGatewayMongo {

    @Autowired
    private DnaRepository dnaRepository;

    public Dna findByDna(String[] dnaSequence) {

        try {
            return dnaRepository.findByDna(dnaSequence);
        } catch (Exception e) {
            throw new DnaDatabaseGatewayException("find");
        }
    }

    public void save(Dna dna) {

        try {
            dnaRepository.save(dna);
        } catch (Exception e) {
            throw new DnaDatabaseGatewayException("save");
        }
    }

    public List<Dna> findAll() {
        try {
            return dnaRepository.findAll();
        } catch (Exception e) {
            throw new DnaDatabaseGatewayException("find all");
        }
    }
}

package br.com.mutants.gene.usecase;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.usecase.exception.NoDnaFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountDnaStatistic {

    @Autowired
    private DnaGatewayMongo mongo;

    public DnaStatistic count() {

        Optional<List<Dna>> optionalDnaList = mongo.findAll();

        if(optionalDnaList.isPresent()){

            List<Dna> dnaList = optionalDnaList.get();

            long mutantDna = dnaList.stream().filter(Dna::isMutant).count();
            long humanDna = dnaList.size() - mutantDna;
            double ratio = humanDna != 0? new Double(mutantDna) / new Double(humanDna) : mutantDna;

            return new DnaStatistic(mutantDna, humanDna, ratio);

        }

        throw new NoDnaFoundException();

    }
}

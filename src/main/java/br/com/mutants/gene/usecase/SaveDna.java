package br.com.mutants.gene.usecase;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.gateways.DnaGatewayMongo;
import br.com.mutants.gene.gateways.exception.DnaDatabaseGatewayException;
import br.com.mutants.gene.usecase.exception.DnaAlreadyValidatedException;
import br.com.mutants.gene.usecase.exception.SaveDnaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveDna {

    @Autowired
    private ValidateMutantGene validateMutantGene;

    @Autowired
    private DnaGatewayMongo dnaMongo;

    public Dna save(Dna dna) {

        try {
            if(dnaMongo.findByDna(dna.getDna()).isPresent()) {
                throw new DnaAlreadyValidatedException();
            }

            dna.setMutant(validateMutantGene.isMutant(dna.getDna()));
            dnaMongo.save(dna);
        } catch (DnaDatabaseGatewayException ex) {
            throw new SaveDnaException();
        }

        return dna;
    }
}

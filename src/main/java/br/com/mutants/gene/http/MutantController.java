package br.com.mutants.gene.http;

import br.com.mutants.gene.domains.Dna;
import br.com.mutants.gene.http.json.DnaJsonRequest;
import br.com.mutants.gene.usecase.SaveDna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private SaveDna saveDna;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity checkDna(@RequestBody DnaJsonRequest dnaJson) {

        Dna dna = saveDna.save(convertToDomain(dnaJson));

        if (dna.isMutant()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private Dna convertToDomain(DnaJsonRequest dnaJson) {
        return new Dna(dnaJson.getDna(), false);
    }
}

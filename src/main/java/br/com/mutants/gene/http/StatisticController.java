package br.com.mutants.gene.http;

import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.http.json.DnaStatisticJson;
import br.com.mutants.gene.http.json.mapper.DnaStatisticMappers.DnaStatisticMapper;
import br.com.mutants.gene.usecase.CountDnaStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    private CountDnaStatistic countDnaStatistic;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DnaStatisticJson> getStatistic() {

        DnaStatisticJson json = buildJson(countDnaStatistic.count());

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    private DnaStatisticJson buildJson(DnaStatistic dnaStatistic) {
        return DnaStatisticMapper.Mapper.dnaStatisticDomainToJson(dnaStatistic);
    }
}

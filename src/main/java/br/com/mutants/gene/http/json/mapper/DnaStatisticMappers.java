package br.com.mutants.gene.http.json.mapper;

import br.com.mutants.gene.domains.DnaStatistic;
import br.com.mutants.gene.http.json.DnaStatisticJson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DnaStatisticMappers {

    DnaStatisticJson dnaStatisticDomainToJson(DnaStatistic dnaStatistic);

    enum DnaStatisticMapper {
        ;

        public static final DnaStatisticMappers Mapper = Mappers.getMapper(DnaStatisticMappers.class);
    }
}

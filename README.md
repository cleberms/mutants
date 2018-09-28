# Mutant Gene
Api para identificar genes mutantes dado uma sequencia de DNA

## Cloud
A aplicação foi hospedada no Heroku

O banco mongo também está no Heroku

SWAGGER : https://mutantgene.herokuapp.com/swagger-ui.html#/ 

Exemplos:

**/mutant**

```
curl -X POST \
  https://mutantgene.herokuapp.com/mutant \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: c7d7c876-3fae-4381-ca04-ee26a3a226d1' \
  -d '{
	"dna": ["ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"]
}'

```
**/stats**

```
curl -X POST \
  https://mutantgene.herokuapp.com/stats \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 614c51e4-6b25-bb39-fe83-f8eded5cceda'

```

## Getting Started

Para iniciar a aplicação local deve rodar o seguinte comando:

```

mvn spring-boot:run

```

## Coverage

A Cobertura do codigo está em 93%

![coverage](https://github.com/cleberms/mutants/blob/master/coverage/coverage.png)

## Author

Cleber Santaterra
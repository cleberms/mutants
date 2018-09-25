package br.com.mutants.mutants.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mutant")
public class MutantsController {


    @GetMapping
    public ResponseEntity<String> teste(){

        return new ResponseEntity("Teste", HttpStatus.OK);
    }
}

package br.com.mutants.gene.usecase.exception;

import org.springframework.http.HttpStatus;

public class SaveDnaException extends RuntimeException {

    private static final String MESSAGE = "Error to save DNA.";

    public SaveDnaException(){
        super(MESSAGE);
    }

}

package br.com.mutants.gene.usecase.exception;

import org.springframework.http.HttpStatus;

public class DnaAlreadyValidatedException extends RuntimeException {

    private static String MESSAGE = "The DNA sequence is already validated!";

    public DnaAlreadyValidatedException() {
        super(MESSAGE);
    }

    public HttpStatus httpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public int httpStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY.value();
    }
}

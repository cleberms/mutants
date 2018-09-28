package br.com.mutants.gene.usecase.exception;

public class NoDnaFoundException extends RuntimeException {

    private static final String MESSAGE = "No dna was found!";

    public NoDnaFoundException() {
        super(MESSAGE);
    }
}

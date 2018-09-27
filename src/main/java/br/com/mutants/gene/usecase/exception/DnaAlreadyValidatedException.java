package br.com.mutants.gene.usecase.exception;

public class DnaAlreadyValidatedException extends RuntimeException {

    private static String MESSAGE = "The DNA sequence is already validated!";

    public DnaAlreadyValidatedException() {
        super(MESSAGE);
    }
}

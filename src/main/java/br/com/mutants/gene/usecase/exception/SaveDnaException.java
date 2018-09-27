package br.com.mutants.gene.usecase.exception;

public class SaveDnaException extends RuntimeException {

    private static final String MESSAGE = "Error to save DNA.";

    public SaveDnaException(){
        super(MESSAGE);
    }
}

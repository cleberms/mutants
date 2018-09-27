package br.com.mutants.gene.gateways.exception;

public class DnaDatabaseGatewayException extends RuntimeException {

    private static final String MESSAGE = "Error to %s dna";

    public DnaDatabaseGatewayException(final String method){
        super(String.format(MESSAGE, method));
    }
}

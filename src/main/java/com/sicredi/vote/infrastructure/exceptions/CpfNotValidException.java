package com.sicredi.vote.infrastructure.exceptions;

public class CpfNotValidException extends RuntimeException {
    public CpfNotValidException(){
        super("Cpf not valid");
    }
}
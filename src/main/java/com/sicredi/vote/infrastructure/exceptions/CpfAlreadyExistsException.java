package com.sicredi.vote.infrastructure.exceptions;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(){
        super("Cpf Already Exists");
    }
}
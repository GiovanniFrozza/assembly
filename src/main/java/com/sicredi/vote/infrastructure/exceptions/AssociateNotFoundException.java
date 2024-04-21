package com.sicredi.vote.infrastructure.exceptions;

public class AssociateNotFoundException extends RuntimeException {
    public AssociateNotFoundException(){
        super("Associate not Found");
    }
}
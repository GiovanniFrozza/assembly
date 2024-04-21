package com.sicredi.vote.infrastructure.exceptions;

public class AssociateUnableToVoteException extends RuntimeException {
    public AssociateUnableToVoteException(){
        super("Associate unable to vote");
    }
}
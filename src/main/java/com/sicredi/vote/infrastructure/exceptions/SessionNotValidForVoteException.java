package com.sicredi.vote.infrastructure.exceptions;

public class SessionNotValidForVoteException extends RuntimeException {
    public SessionNotValidForVoteException(){
        super("Session not valid");
    }
}
package com.sicredi.vote.infrastructure.exceptions;

public class SessionNotAvailableException extends RuntimeException {
    public SessionNotAvailableException(){
        super("Session not available");
    }
}
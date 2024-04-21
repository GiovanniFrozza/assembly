package com.sicredi.vote.infrastructure.exceptions;

public class UserAlreadyVotedException extends RuntimeException {
    public UserAlreadyVotedException(){
        super("The user has already voted in this session");
    }
}
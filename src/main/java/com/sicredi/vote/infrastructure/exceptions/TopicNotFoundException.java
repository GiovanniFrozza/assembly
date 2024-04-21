package com.sicredi.vote.infrastructure.exceptions;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(){
        super("Topic not Found");
    }
}
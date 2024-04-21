package com.sicredi.vote.infrastructure.exceptions;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var error = exception.getBindingResult().getFieldErrors().get(0);

        return new ResponseEntity<>(error.getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SessionNotValidForVoteException.class)
    public ResponseEntity<Object> handleSessionNotValidForVoteException(SessionNotValidForVoteException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AssociateNotFoundException.class)
    public ResponseEntity<Object> handleAssociateNotFoundException(AssociateNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AssociateUnableToVoteException.class)
    public ResponseEntity<Object> handleAssociateUnableToVoteException(AssociateUnableToVoteException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CpfAlreadyExistsException.class)
    public ResponseEntity<Object> handleCpfAlreadyExistsException(CpfAlreadyExistsException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CpfNotValidException.class)
    public ResponseEntity<Object> handleCpfNotValidException(CpfNotValidException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SessionNotAvailableException.class)
    public ResponseEntity<Object> handleSessionNotAvailableException(SessionNotAvailableException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TopicNotFoundException.class)
    public ResponseEntity<Object> handleTopicNotFoundException(TopicNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyVotedException.class)
    public ResponseEntity<Object> handleUserAlreadyVotedException(UserAlreadyVotedException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.valueOf(exception.status()));
    }

}

package com.curso.api.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String message){
        super(message);
    }
    InvalidPasswordException(String message,Throwable causa){
        super(message, causa);
    }
}

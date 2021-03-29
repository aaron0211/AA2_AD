package com.aaron.AA2_AD.exception;

public class AuthorNotFoundException extends RuntimeException{

    public  AuthorNotFoundException(){
        super();
    }

    public AuthorNotFoundException(String message){
        super(message);
    }

    public AuthorNotFoundException(long id){
        super("Author not found: " + id);
    }
}

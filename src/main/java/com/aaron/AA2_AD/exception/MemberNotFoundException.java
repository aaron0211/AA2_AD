package com.aaron.AA2_AD.exception;

public class MemberNotFoundException extends RuntimeException{

    public  MemberNotFoundException(){
        super();
    }

    public MemberNotFoundException(String message){
        super(message);
    }

    public MemberNotFoundException(long id){
        super("Member not found: " + id);
    }
}

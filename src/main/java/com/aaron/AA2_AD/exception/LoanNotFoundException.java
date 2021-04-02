package com.aaron.AA2_AD.exception;

public class LoanNotFoundException extends RuntimeException{

    public  LoanNotFoundException(){
        super();
    }

    public LoanNotFoundException(String message){
        super(message);
    }

    public LoanNotFoundException(long id){
        super("Loan not found: " + id);
    }
}

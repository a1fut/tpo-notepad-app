package org.example.tpoapi.exceptions;

public class NoRecordInDatabaseException extends IllegalArgumentException{
    public NoRecordInDatabaseException(String errorMessage){
        super(errorMessage);
    }
}

package com.progresssoft.clustered_data_warehouse.exceptions;

public class InvalidRequestFieldException extends RuntimeException{
    public InvalidRequestFieldException(String errMsg){
        super(errMsg);
    }
}

package com.progresssoft.clustered_data_warehouse.exceptions;

public class DuplicateRequestException extends RuntimeException{
    public DuplicateRequestException(String errMsg){
        super(errMsg);
    }
}

package com.progresssoft.clustered_data_warehouse.exceptions;

import com.progresssoft.clustered_data_warehouse.dto.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<Object> handleDuplicateDealException(DuplicateRequestException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(CustomResponse.builder()
                        .responseCode("41")
                        .responseMsg("ERR: DUPLICATE REQUEST")
                        .responseDetails(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(InvalidRequestFieldException.class)
    public ResponseEntity<Object> handleDuplicateDealException(InvalidRequestFieldException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(CustomResponse.builder()
                        .responseCode("99")
                        .responseMsg("ERR: THIS FIELD IS REQUIRED")
                        .responseDetails(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CustomResponse.builder()
                        .responseMsg(e.getMessage())
                        .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .build()
                );
    }
}

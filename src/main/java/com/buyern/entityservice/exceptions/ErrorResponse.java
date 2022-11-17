package com.buyern.entityservice.exceptions;


import com.buyern.entityservice.dtos.ResponseDTO;

public class ErrorResponse extends ResponseDTO<Object> {

    public ErrorResponse(String code, String message, Object data) {
        super(code, message, data);
    }

    public ErrorResponse(String code, String message) {
        super(code, message);
    }
}

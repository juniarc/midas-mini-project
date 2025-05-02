package dev.codejar.payload.response;


import lombok.Data;

@Data
public class BaseResponse <T>{

    private String message;

    private boolean status;

    private T data;

}

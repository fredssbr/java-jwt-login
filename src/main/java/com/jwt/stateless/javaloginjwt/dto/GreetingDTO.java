package com.jwt.stateless.javaloginjwt.dto;

public class GreetingDTO {

    private String message;

    public GreetingDTO(){}

    public GreetingDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

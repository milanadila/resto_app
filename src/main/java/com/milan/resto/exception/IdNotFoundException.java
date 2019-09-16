package com.milan.resto.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Id not Found!");
    }
}

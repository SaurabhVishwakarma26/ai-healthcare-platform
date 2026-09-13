package com.sam.healthcare.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("A patient already exists with email: " + email);
    }
}
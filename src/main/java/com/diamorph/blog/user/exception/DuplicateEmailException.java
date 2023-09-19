package com.diamorph.blog.user.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {super(message);}
}

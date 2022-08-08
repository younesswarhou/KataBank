package com.kata.exception;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = -991371606970765803L;

	public InsufficientBalanceException(String message) {
        super(message);
    }
}

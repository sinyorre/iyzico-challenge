package com.iyzico.challenge.exception;

public class StockDepletedException extends RuntimeException {
    public StockDepletedException(String message) {
        super(message);
    }

    public StockDepletedException(String message, Throwable cause) {
        super(message, cause);
    }
}

package org.example.exceptions;

public class InvalidBotCountException extends Exception {
    public InvalidBotCountException(String message) {
        super(message);
    }
}

// Please read about checked vs unchecked exceptions again

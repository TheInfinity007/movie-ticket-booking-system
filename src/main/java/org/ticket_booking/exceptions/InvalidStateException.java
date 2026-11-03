package org.ticket_booking.exceptions;

public class InvalidStateException extends RuntimeException{
    public InvalidStateException(String message){
        super(message);
    }
}

package org.ticket_booking.exceptions;

public class SeatTemporarilyUnavailableException extends RuntimeException {
    public SeatTemporarilyUnavailableException(String message) {
        super(message);
    }
}

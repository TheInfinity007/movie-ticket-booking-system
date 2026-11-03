package org.ticket_booking.model;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired(){
        Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        Instant now = Instant.now();

        return lockInstant.isBefore(now);
    }
}


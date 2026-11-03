package org.ticket_booking.provider;

import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;

import java.util.List;
import java.util.Set;

public interface SeatLockProvider {
    Set<String> getLockedSeats(Show show);

    void lockSeats(Show show, List<Seat> seats, String user);
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLock(Show show, Seat seat, String user);
}

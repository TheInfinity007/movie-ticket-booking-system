package org.ticket_booking.provider;

import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;

import java.util.List;

public interface SeatLockProvider {
    List<Seat> getLockedSeats(Show show);
}

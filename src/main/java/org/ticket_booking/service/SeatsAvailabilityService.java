package org.ticket_booking.service;

import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;

import java.util.List;

public interface SeatsAvailabilityService {
    List<Seat> getAvailableSeats(Show show);
}

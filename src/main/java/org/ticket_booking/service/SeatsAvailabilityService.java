package org.ticket_booking.service;

import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;

import java.util.List;
import java.util.Set;

public interface SeatsAvailabilityService {
    Set<String> getAvailableSeatIds(Show show);
}

package org.ticket_booking.service;

import org.ticket_booking.model.Show;

public interface TheatreService {
    Object getSeat(String seatId, Show show);
}

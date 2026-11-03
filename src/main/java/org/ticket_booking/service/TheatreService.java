package org.ticket_booking.service;

import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.model.Theatre;

public interface TheatreService {
    Object getSeat(String seatId, Show show);

    Theatre create(String theatreName);

    Theatre getTheatre(String theatreId);

    Screen createScreen(String screenName, Theatre theatre);

    Screen getScreen(String screenId);

    Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen);
}

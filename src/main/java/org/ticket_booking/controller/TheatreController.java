package org.ticket_booking.controller;

import lombok.AllArgsConstructor;
import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Theatre;
import org.ticket_booking.service.TheatreService;

@AllArgsConstructor
public class TheatreController {
    private TheatreService theatreService;

    public Theatre create(String theatreName) {
        return theatreService.create(theatreName);
    }

    public String createScreenInTheatre(String screenName, String theatreId) {
        Theatre theatre = theatreService.getTheatre(theatreId);
        Screen screen = theatreService.createScreen(screenName, theatre);
        return screen.getId();
    }

    public String createSeatInScreen(Integer rowNo, Integer seatNo, String screenId) {
        Screen screen = theatreService.getScreen(screenId);
        Seat seat = theatreService.createSeatInScreen(rowNo, seatNo, screen);
        return seat.getId();
    }
}

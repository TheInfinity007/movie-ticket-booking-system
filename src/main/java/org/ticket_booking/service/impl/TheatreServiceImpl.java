package org.ticket_booking.service.impl;

import lombok.RequiredArgsConstructor;
import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.model.Theatre;
import org.ticket_booking.service.ScreenService;
import org.ticket_booking.service.TheatreService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    Map<String, Theatre> theatres = new ConcurrentHashMap<>();
    Map<String, Seat> seats = new ConcurrentHashMap<>();
    private final ScreenService screenService;


    @Override
    public Seat getSeat(String seatId, Show show) {
        List<Seat> seats = show.getScreen().getSeats();

        Seat seat = seats.stream().filter(s -> s.getId().equals(seatId))
                .findFirst()
                .orElse(null);
        return seat;
    }

    @Override
    public Theatre create(String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    @Override
    public Theatre getTheatre(String theatreId) {
        return theatres.get(theatreId);
    }

    @Override
    public Screen createScreen(String screenName, Theatre theatre) {
        Screen screen = screenService.create(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    @Override
    public Screen getScreen(String screenId) {
        return screenService.getScreen(screenId);
    }

    @Override
    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeat(seat);

        return seat;
    }
}

package org.ticket_booking.service.impl;

import lombok.AllArgsConstructor;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.provider.SeatLockProvider;
import org.ticket_booking.service.BookingService;
import org.ticket_booking.service.SeatsAvailabilityService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SeatsAvailabiliityServiceImpl implements SeatsAvailabilityService {

    private BookingService bookingService;
    private SeatLockProvider seatLockProvider;

    @Override
    public List<Seat> getAvailableSeats(Show show) {
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);

        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}

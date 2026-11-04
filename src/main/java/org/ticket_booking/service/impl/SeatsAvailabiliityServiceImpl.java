package org.ticket_booking.service.impl;

import lombok.AllArgsConstructor;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.provider.SeatLockProvider;
import org.ticket_booking.service.BookingService;
import org.ticket_booking.service.SeatsAvailabilityService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SeatsAvailabiliityServiceImpl implements SeatsAvailabilityService {

    private BookingService bookingService;
    private SeatLockProvider seatLockProvider;

    @Override
    public Set<String> getAvailableSeatIds(Show show) {
        Set<String> allSeatsIds = show.getScreen().getSeats().stream().map(Seat::getId).collect(Collectors.toSet());
        Set<String> unavailableSeats = getUnavailableSeatIds(show);

        Set<String> availableSeats = new HashSet<>(allSeatsIds);
        availableSeats.removeAll(unavailableSeats);

        return availableSeats;
    }

    private Set<String> getUnavailableSeatIds(Show show) {
        List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        Set<String> unavailableSeatsIds = unavailableSeats.stream().map(Seat::getId).collect(Collectors.toSet());
        unavailableSeatsIds.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeatsIds;
    }
}

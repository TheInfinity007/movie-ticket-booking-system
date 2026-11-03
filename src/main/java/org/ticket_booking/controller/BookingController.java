package org.ticket_booking.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ticket_booking.model.Booking;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.BookingService;
import org.ticket_booking.service.ShowService;
import org.ticket_booking.service.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    private BookingService bookingService;
    private ShowService showService;
    private TheatreService theatreService;

    public String create(@NonNull String userId, @NonNull String showId, @NonNull List<String> seatIds) {
        Show show = showService.getShow(showId);
        List<Seat> seats = seatIds.stream().map(seatId -> theatreService.getSeat(seatId, show)).collect(Collectors.toList());

        Booking booking = bookingService.createBooking(userId, show, seats);
        return booking.getId();
    }
}

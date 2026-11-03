package org.ticket_booking.service.impl;

import org.ticket_booking.model.Booking;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.BookingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final Map<String, List<Booking>> bookings = new HashMap<>(); // Map of showid and list of bookings

    @Override
    public Booking createBooking(String userId, Show show, List<Seat> seats) {
        return null;
    }

    @Override
    public Booking getBooking(String bookingId) {
        return null;
    }

    @Override
    public List<Seat> getBookedSeats(Show show) {
        List<Booking> showBookings = bookings.get(show.getId());

        if (showBookings == null) {
            throw new RuntimeException("Booking not found for show " + show);
        }

        List<Seat> bookedSeats = showBookings.stream().flatMap(b -> b.getSeatsBooked().stream()).collect(Collectors.toList());
        return bookedSeats;
    }
}

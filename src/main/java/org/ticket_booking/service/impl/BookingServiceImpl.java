package org.ticket_booking.service.impl;

import org.ticket_booking.model.Booking;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.BookingService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final Map<String, List<Booking>> bookings = new ConcurrentHashMap<>(); // Map of showid and list of bookings

    @Override
    public Booking createBooking(String userId, Show show, List<Seat> seats) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, userId, seats);

        // Collections.synchronizedList make the arraylist thread safe
        bookings.computeIfAbsent(bookingId, k -> Collections.synchronizedList(new ArrayList<>()))
                .add(booking);

        return booking;
    }

    @Override
    public Booking getBooking(String bookingId) {
        return bookings.values().stream()
                .flatMap(allBookings -> allBookings.stream())
                .filter(booking-> booking.getId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Seat> getBookedSeats(Show show) {
        List<Booking> showBookings = bookings.get(show.getId());

        if (showBookings == null || showBookings.isEmpty()) {
            return Collections.emptyList();
        }

        List<Seat> bookedSeats = showBookings.stream().flatMap(b -> b.getSeatsBooked().stream()).collect(Collectors.toList());
        return bookedSeats;
    }
}

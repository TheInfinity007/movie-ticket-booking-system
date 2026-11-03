package org.ticket_booking.service.impl;

import org.ticket_booking.exceptions.BadRequestException;
import org.ticket_booking.exceptions.SeatNotAvailableException;
import org.ticket_booking.model.Booking;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.provider.SeatLockProvider;
import org.ticket_booking.service.BookingService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>(); // Map of showid and list of bookings
    private SeatLockProvider seatLockProvider;

    @Override
    public Booking createBooking(String userId, Show show, List<Seat> seats) {

        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatNotAvailableException("Seat already booked");
        }

        seatLockProvider.lockSeats(show, seats, userId);

        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, userId, seats);

        // Collections.synchronizedList make the arraylist thread safe
        bookings.put(bookingId, booking);
        return booking;

        // TODO: Create timer for booking expiry
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            boolean isBooked = bookedSeats.stream().anyMatch(s -> s.getId().equals(seat.getId()));
            if (isBooked) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public List<Seat> getBookedSeats(Show show) {

        return getAllBookings(show)
                .stream().filter(booking -> booking.isConfirmed())
                .map(booking -> booking.getSeatsBooked())
                .flatMap(seatsBooked -> seatsBooked.stream())
                .collect(Collectors.toList());
    }

    private List<Booking> getAllBookings(Show show) {
        List<Booking> showBookings = bookings.values().stream()
                .filter(booking -> booking.getShow().getId().equals(show.getId()))
                .collect(Collectors.toList());
        return showBookings;
    }

    @Override
    public void confirmBooking(Booking booking, String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException("Invalid booking");
        }

        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new SeatNotAvailableException("Seat already booked");
            }
        }

        booking.confirmBooking();
    }
}

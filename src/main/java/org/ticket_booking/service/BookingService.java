package org.ticket_booking.service;

import lombok.NonNull;
import org.ticket_booking.model.Booking;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;

import java.util.List;

public interface BookingService {
    Booking createBooking(String userId, Show show, List<Seat> seats);

    Booking getBooking(String bookingId);

    List<Seat> getBookedSeats(Show show);
}

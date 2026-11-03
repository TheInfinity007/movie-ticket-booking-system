package org.ticket_booking.service;

import org.ticket_booking.model.Booking;

public interface PaymentsService {
    void processPaymentFailed(Booking booking, String user);

    void confirmBooking(Booking booking, String user);
}

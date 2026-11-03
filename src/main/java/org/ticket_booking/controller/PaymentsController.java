package org.ticket_booking.controller;

import lombok.AllArgsConstructor;
import org.ticket_booking.model.Booking;
import org.ticket_booking.service.BookingService;
import org.ticket_booking.service.PaymentsService;

@AllArgsConstructor
public class PaymentsController {
    private PaymentsService paymentsService;
    private BookingService bookingService;

    public void paymentFailed(String bookingId, String user){
        Booking booking = bookingService.getBooking(bookingId);
        paymentsService.processPaymentFailed(booking, user);
    }

    public void paymentSuccess(String bookingId, String user){
        Booking booking = bookingService.getBooking(bookingId);
        paymentsService.confirmBooking(booking, user);
    }
}

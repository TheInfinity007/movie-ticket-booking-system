package org.ticket_booking.service.impl;

import lombok.RequiredArgsConstructor;
import org.ticket_booking.exceptions.BadRequestException;
import org.ticket_booking.model.Booking;
import org.ticket_booking.provider.SeatLockProvider;
import org.ticket_booking.service.PaymentsService;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentsService {

    Map<String, Integer> bookingFailures = new HashMap<>();
    private final SeatLockProvider seatLockProvider;
    private final Integer allowedRetries = 3;

    @Override
    public void processPaymentFailed(Booking booking, String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException("Invalid user");
        }

        if (!bookingFailures.containsKey(booking.getId())) {
            bookingFailures.put(booking.getId(), 0);
        }

        Integer currentFailuresCount = bookingFailures.get(booking.getId());
        Integer newFailuresCount = currentFailuresCount + 1;

        bookingFailures.put(booking.getId(), newFailuresCount);


        if(newFailuresCount > allowedRetries){
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }
}

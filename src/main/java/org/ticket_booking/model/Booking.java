package org.ticket_booking.model;

import lombok.Getter;
import org.ticket_booking.exceptions.InvalidStateException;

import java.util.List;

@Getter
public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(String id, Show show,  String user, List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.user = user;
        this.seatsBooked = seatsBooked;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isBooked(){
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking(){
        if(this.bookingStatus != BookingStatus.Created){
            throw new InvalidStateException("Booking can't be confirmed");
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking(){
        if(this.bookingStatus != BookingStatus.Created){
            throw new InvalidStateException("Booking can't be confirmed");
        }

        this.bookingStatus = BookingStatus.Expired;
    }
}

package org.ticket_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;

//    boolean isLocked;
//    boolean isBooked;
}

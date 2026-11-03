package org.ticket_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor()
@Getter
public class Movie {
    private final String id;
    private final String title;

    // Other metadata like duration, genre, cast
}

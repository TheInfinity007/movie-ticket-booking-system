package org.ticket_booking.service;

import lombok.NonNull;
import org.ticket_booking.model.Movie;
import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Show;

import java.util.Date;

public interface ShowService {
    Show getShow(String showId);

    Show create(Movie movie, Screen screen, Date startTime, Integer durationInSeconds);
}

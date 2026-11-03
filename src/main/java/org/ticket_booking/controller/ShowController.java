package org.ticket_booking.controller;

import lombok.AllArgsConstructor;
import org.ticket_booking.model.Movie;
import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.MovieService;
import org.ticket_booking.service.ScreenService;
import org.ticket_booking.service.SeatsAvailabilityService;
import org.ticket_booking.service.ShowService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {

    ScreenService screenService;
    MovieService movieService;
    ShowService showService;
    SeatsAvailabilityService seatsAvailabilityService;

    public Show create(String movieId, String screenId, Date startTime, Integer durationInSeconds) {
        Screen screen = screenService.getScreen(screenId);
        Movie movie = movieService.getMovie(movieId);

        return showService.create(movie, screen, startTime, durationInSeconds);
    }

    public List<String> getAvailableSeats(String showId) {
        Show show = showService.getShow(showId);
        Set<String> availableSeats = seatsAvailabilityService.getAvailableSeatIds(show);
        return new ArrayList<>(availableSeats);
    }
}

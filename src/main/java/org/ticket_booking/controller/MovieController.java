package org.ticket_booking.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ticket_booking.model.Movie;
import org.ticket_booking.service.MovieService;

@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    public Movie create(@NonNull String name){
        return movieService.create(name);
    }
}

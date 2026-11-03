package org.ticket_booking.service.impl;

import lombok.NonNull;
import org.ticket_booking.exceptions.NotFoundException;
import org.ticket_booking.model.Movie;
import org.ticket_booking.service.MovieService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MovieServiceImpl implements MovieService {

    private final Map<String, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public Movie create(@NonNull String name) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, name);
        movies.put(movieId, movie);
        return movie;
    }

    @Override
    public Movie getMovie(String movieId) {

        if(!movies.containsKey(movieId)) {
            throw new NotFoundException("Movie not found with id: " + movieId);
        }

        return movies.get(movieId);
    }
}

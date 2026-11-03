package org.ticket_booking.service.impl;

import org.ticket_booking.model.Movie;
import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.ShowService;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ShowServiceImpl implements ShowService {

    private final Map<String, Show> shows = new ConcurrentHashMap<>();

    @Override
    public Show getShow(String showId) {
        return shows.get(showId);
    }

    @Override
    public Show create(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        shows.put(showId, show);
        return show;
    }
}

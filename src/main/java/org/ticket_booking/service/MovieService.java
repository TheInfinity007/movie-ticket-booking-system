package org.ticket_booking.service;

import lombok.NonNull;
import org.ticket_booking.model.Movie;

public interface MovieService {
    Movie create(@NonNull String name);
}

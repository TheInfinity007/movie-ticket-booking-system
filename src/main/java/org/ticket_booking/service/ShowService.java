package org.ticket_booking.service;

import lombok.NonNull;
import org.ticket_booking.model.Show;

public interface ShowService {
    Show getShow(String showId);
}

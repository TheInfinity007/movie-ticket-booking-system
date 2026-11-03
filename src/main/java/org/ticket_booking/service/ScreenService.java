package org.ticket_booking.service;

import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Theatre;

public interface ScreenService {
    Screen getScreen(String screenId);

    Screen create(String screenName, Theatre theatre);
}

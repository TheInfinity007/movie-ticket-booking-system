package org.ticket_booking.service.impl;

import org.ticket_booking.model.Screen;
import org.ticket_booking.model.Theatre;
import org.ticket_booking.service.ScreenService;

import java.util.UUID;

public class ScreenServiceImpl implements ScreenService {
    @Override
    public Screen getScreen(String screenId) {
        return null;
    }

    @Override
    public Screen create(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        return screen;
    }
}

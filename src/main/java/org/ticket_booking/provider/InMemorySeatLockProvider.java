package org.ticket_booking.provider;

import lombok.AllArgsConstructor;
import org.ticket_booking.exceptions.SeatTemporarilyUnavailableException;
import org.ticket_booking.model.Seat;
import org.ticket_booking.model.SeatLock;
import org.ticket_booking.model.Show;
import org.ticket_booking.service.BookingService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class InMemorySeatLockProvider implements SeatLockProvider {

    private BookingService bookingService;
    private final Integer lockTimeout = 300;
    private final Integer timeoutInSeconds = 300;
    private final Map<String, Map<String, SeatLock>> locks = new ConcurrentHashMap<>();

    @Override
    public Set<String> getLockedSeats(Show show) {
        if(!locks.containsKey(show.getId())){
            return new HashSet<>();
        }

        final Set<String> lockedSeatsIds = new HashSet<>();
        for(String seatId: locks.get(show.getId()).keySet()){
            if(isSeatLocked(show, seatId)){
                lockedSeatsIds.add(seatId);
            }
        }
        return lockedSeatsIds;
    }

    private void lockSeat(Show show, Seat seat, String user, Integer lockTimeout) {
        if (!locks.containsKey(show.getId())) {
            locks.put(show.getId(), new ConcurrentHashMap<>());
        }

        SeatLock seatLock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show.getId()).put(seat.getId(), seatLock);
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat.getId())) {
                throw new SeatTemporarilyUnavailableException("Seat is locked");
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }

    }

    private boolean isSeatLocked(Show show, String seatId) {
        return locks.containsKey(show.getId())
                && locks.get(show.getId()).containsKey(seatId)
                && !locks.get(show.getId()).get(seatId).isLockExpired();
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    private void unlockSeat(Show show, Seat seat) {
        if (locks.containsKey(show.getId())) {
            locks.get(show.getId()).remove(seat.getId());
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat.getId()) && locks.get(show.getId()).get(seat.getId()).getLockedBy().equals(user);
    }
}

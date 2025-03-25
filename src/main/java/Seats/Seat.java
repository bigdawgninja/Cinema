package Seats;

import java.util.ArrayList;

public class Seat {
    private SeatState state;
    private String seatId;
    public Seat(String seatId) {
        this.state = new AvailableState();
        this.seatId = seatId;
    }

    public SeatState getState() {
        return state;
    }

    public void setState(SeatState state) {
        this.state = state;

    }
    public String getSeatId() {
        return seatId;
    }
}

package Seats;

public class ReservedState implements SeatState {

    public void reservedSeat(Seat seat) {

    }

    @Override
    public void cancelReservation(Seat seat) {
        seat.setState(new AvailableState());
    }
}

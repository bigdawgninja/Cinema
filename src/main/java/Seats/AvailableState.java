package Seats;

public class AvailableState implements SeatState{
    @Override
    public void reservedSeat(Seat seat) {
        seat.setState(new ReservedState());
    }

    public void cancelReservation(Seat seat){}
}

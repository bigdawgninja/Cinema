package Halls;

import Seats.Seat;

public class Hall {
    private int hallNumber;

    private Seat[][] seats;

    public Hall(int numRows, int numColumns, int hallNumber){
        this.seats = new Seat[numRows][numColumns];
        this.hallNumber = hallNumber;
        initializeSeats();
    }
    private void initializeSeats(){
        char rowChar = 'A';

        for (int i =0; i < seats.length; i++){
            if(i == 1){
                rowChar = 'B';
            }
            for (int j = 0; j < seats[i].length; j++){
                String seatId = Character.toString(rowChar) + (j+1);
                seats[i][j] = new Seat(seatId);
            }
        }
    }
    public Seat[][] getSeats() {
        return seats;
    }
    public int getHallNumber(){return hallNumber;}


}

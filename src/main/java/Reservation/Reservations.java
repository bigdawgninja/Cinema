package Reservation;

import Builder.Movie;
import Seats.Seat;

import java.time.LocalDate;
import java.util.Queue;

public class Reservations {
    private Movie movie;
    private Queue<Seat> seats;
    private LocalDate date;
    private String customerName;


    public Reservations(Movie movie,  LocalDate date, String customerName) {
        this.movie = movie;
        this.date = date;
        this.customerName = customerName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Queue<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Queue<Seat> seats) {
        this.seats = seats;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void displayReservation() {

    }
}

package org.example.daracinema;

import Builder.Movie;
import Builder.MovieEngineer;
import Halls.Hall;
import Seats.AvailableState;
import Seats.ReservedState;
import Seats.Seat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class SeatsController {
    private Hall hall;
    private Queue<Seat> selectedSeats = new LinkedList<>();
    private Movie movie;
    private MovieEngineer movieEngineer;

    public void setHall(Hall hall){
        this.hall = hall;
        updateLabels();
    }


    @FXML
    private Label seatOneLabel,seatTwoLabel,seatThreeLabel,seatFourLabel,seatFiveLabel,seatSixLabel,seatSevenLabel,seatEightLabel,seatNineLabel,seatTenLabel,movieLabel;
    @FXML
    private ImageView seatOne, seatTwo,seatThree,seatFour,seatFive,seatSix,seatSeven,seatEight,seatNine,seatTen;
    @FXML
    private MenuButton loginButton1;
    @FXML
    private MenuItem loginMenuItem;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private Button mainMenuButton;

    private MainController mainController;

    @FXML
    public void initialize(){
        seatOne.setOnMouseClicked(event -> handleSeatClicked(0, 0));
        seatTwo.setOnMouseClicked(event -> handleSeatClicked(0, 1));
        seatThree.setOnMouseClicked(event -> handleSeatClicked(0, 2));
        seatFour.setOnMouseClicked(event -> handleSeatClicked(0, 3));
        seatFive.setOnMouseClicked(event -> handleSeatClicked(0, 4));

        seatSix.setOnMouseClicked(event -> handleSeatClicked(1, 0));
        seatSeven.setOnMouseClicked(event -> handleSeatClicked(1, 1));
        seatEight.setOnMouseClicked(event -> handleSeatClicked(1, 2));
        seatNine.setOnMouseClicked(event -> handleSeatClicked(1, 3));
        seatTen.setOnMouseClicked(event -> handleSeatClicked(1, 4));

    }

    private void handleSeatClicked(int row, int column) {
        Seat seat = hall.getSeats()[row][column];
        seatChangeState(row, column);

        if(seat.getState() instanceof ReservedState){
            selectedSeats.add(seat);
        }else {
            selectedSeats.remove();
        }
        System.out.println(seat.getState());

    }

    @FXML
    public void seatChangeState(int row, int column) {
        Seat seat = hall.getSeats()[row][column];

        if (seat.getState() instanceof ReservedState) {
            seat.setState(new AvailableState());
            updateSeatImage(row, column, "/org/example/Images/Seats.png");
        } else {
            seat.setState(new ReservedState());
            updateSeatImage(row, column, "/org/example/Images/TakenSeats.png");
        }
        System.out.println(seat.getState());
    }

    private void updateSeatImage(int row, int column, String imagePath) {
        ImageView imageViewToUpdate = null;

        switch (row) {
            case 0:
                switch (column) {
                    case 0:
                        imageViewToUpdate = seatOne;
                        break;
                    case 1:
                        imageViewToUpdate = seatTwo;
                        break;
                    case 2:
                        imageViewToUpdate = seatThree;
                        break;
                    case 3:
                        imageViewToUpdate = seatFour;
                        break;
                    case 4:
                        imageViewToUpdate = seatFive;
                        break;
                }
                break;
            case 1:
                switch (column) {
                    case 0:
                        imageViewToUpdate = seatSix;
                        break;
                    case 1:
                        imageViewToUpdate = seatSeven;
                        break;
                    case 2:
                        imageViewToUpdate = seatEight;
                        break;
                    case 3:
                        imageViewToUpdate = seatNine;
                        break;
                    case 4:
                        imageViewToUpdate = seatTen;
                        break;
                }
                break;
        }

        if (imageViewToUpdate != null) {
            imageViewToUpdate.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        }
    }
    @FXML
    public void setEngineerSeats(MovieEngineer engineer, Movie movie){
        this.movieEngineer = engineer;
        this.movie = movie;
        movieLabel.setText(movie.getTitle());
    }
    @FXML
    public void setContinueButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentPage.fxml"));
            Parent root = fxmlLoader.load();

            PaymentController paymentController = fxmlLoader.getController();
            paymentController.setPaymentMainController(mainController);
            paymentController.setSelectedSeats(selectedSeats);
            paymentController.setEngineer(movieEngineer, movie);
            paymentController.setHall(hall);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    private void updateLabels() {
        Seat[][] seats = hall.getSeats();
        seatOneLabel.setText(seats[0][0].getSeatId());
        seatTwoLabel.setText(seats[0][1].getSeatId());
        seatThreeLabel.setText(seats[0][2].getSeatId());
        seatFourLabel.setText(seats[0][3].getSeatId());
        seatFiveLabel.setText(seats[0][4].getSeatId());
        seatSixLabel.setText(seats[1][0].getSeatId());
        seatSevenLabel.setText(seats[1][1].getSeatId());
        seatEightLabel.setText(seats[1][2].getSeatId());
        seatNineLabel.setText(seats[1][3].getSeatId());
        seatTenLabel.setText(seats[1][4].getSeatId());

    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        updateLoginButton();
    }

    @FXML
    public void loginButton() {
        if (mainController.isLoggedIn()) {
            mainController.logoutAction();
        } else {
            mainController.loginPage();
        }
        updateLoginButton();
    }

    private void updateLoginButton() {
        if (mainController.isLoggedIn()) {
            loginButton1.setText(mainController.getLoggedInUser());
            loginMenuItem.setVisible(false);
            logoutMenuItem.setVisible(true);
        } else {
            loginButton1.setText("User");
            loginMenuItem.setVisible(true);
            logoutMenuItem.setVisible(false);
        }
    }

    @FXML
    private void logoutAction() {
        mainController.logoutAction();
        updateLoginButton();
    }
    @FXML
    public void mainMenuButtonAction() {
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main-Page.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

package org.example.daracinema;

import Builder.Movie;
import Builder.MovieEngineer;
import Halls.Hall;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceiptController {

    @FXML
    private Label nameLabel,movieLabel,seatLabel,timeLabel,priceLabel,hallLabel;
    @FXML
    private MenuButton loginButton;
    @FXML
    private MenuItem loginMenuItem;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private Button mainMenuButton;
    private MainController mainController;
    private Hall hall;

    public void setLabels(String name,MovieEngineer movieEngineer, Movie movie, String seats, String time, int price) {
        setNameLabel(name);
        setMovieLabel(movie.getTitle());
        setSeatLabel(seats);
        setTimeLabel(time);
        setPriceLabel(price);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        updateLoginButton();
    }
    private void setNameLabel(String name) {
        nameLabel.setText("Name: " + name);
    }

    private void setMovieLabel(String title) {
        movieLabel.setText("Movie: " + title);
    }

    private void setSeatLabel(String seats) {
        seatLabel.setText("Seats: " + seats);
    }

    private void setTimeLabel(String time) {
        timeLabel.setText("Time: " + time);
    }

    private void setPriceLabel(int price) {
        priceLabel.setText("Price: $" + price);
    }

    private void updateLoginButton() {
        if (mainController.isLoggedIn()) {
            loginButton.setText(mainController.getLoggedInUser());
            loginMenuItem.setVisible(false);
            logoutMenuItem.setVisible(true);
        } else {
            loginButton.setText("User");
            loginMenuItem.setVisible(true);
            logoutMenuItem.setVisible(false);
        }
    }

    public void setHall(Hall hall) {
        this.hall = hall;
        hallLabel.setText("Hall number: "+String.valueOf(hall.getHallNumber()));
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

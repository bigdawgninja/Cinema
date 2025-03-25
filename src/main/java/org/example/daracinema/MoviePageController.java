package org.example.daracinema;

import Builder.Movie;
import Builder.MovieEngineer;
import Halls.Hall;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MoviePageController {
    private MovieEngineer engineer;
    private Movie movie;
    Hall hall = new Hall(2,5, 1);

    @FXML
    public void setEngineer(MovieEngineer engineer){
        this.engineer = engineer;
        this.movie = engineer.getMovie();

        movieTitle.setText(movie.getTitle());
        sypnosisID.setText(movie.getSynopsis());
        movieDuration.setText("Duration of the movie "+movie.getDuration());
        ageMovie.setText("Age recommendation "+movie.getAgeRecommendation());

    }


    @FXML
    private ImageView posterImage;
    @FXML
    private Label movieTitle, movieDuration,ageMovie;
    @FXML
    private Button mainMenuButton,reserveSeat;
    @FXML
    private TextArea sypnosisID;
    @FXML
    private MainController mainController;
    @FXML
    private MenuButton loginButton;
    @FXML
    private MenuItem loginMenuItem;
    @FXML
    private MenuItem logoutMenuItem;


    @FXML
    public void setMainController(MainController mainController){
        this.mainController = mainController;
        updateLoginButton();
    }

    @FXML
    public void setImage(Image image) {
        posterImage.setImage(image);

    }
    @FXML
    public void setReserveSeat(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeatPages.fxml"));
            Parent root = fxmlLoader.load();

            SeatsController seatsController = fxmlLoader.getController();
            seatsController.setEngineerSeats(engineer,movie);
            seatsController.setHall(hall);
            seatsController.setMainController(mainController);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


    @FXML
    public void loginButton() {
        if (mainController.isLoggedIn()) {
            mainController.logoutAction();
        } else {
            mainController.loginPage();
        }
        updateLoginButton();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
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

    @FXML
    private void logoutAction() {
        mainController.logoutAction();
        updateLoginButton();
    }
}

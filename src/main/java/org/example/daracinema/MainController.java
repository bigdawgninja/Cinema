package org.example.daracinema;

import Builder.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class MainController {

    LinkedList<MovieEngineer> movieEngineerList = new LinkedList<>();
    MovieEngineer engineer1 = new MovieEngineer(new MarioKartBuilder());
    MovieEngineer engineer2 = new MovieEngineer(new LuigiMansionBuilder());
    MovieEngineer engineer3 = new MovieEngineer(new DjangoUnchainBuilder());
    MovieEngineer engineer4 = new MovieEngineer(new FightClub());


    Movie movie;

    @FXML
    private ImageView posterImageOne, posterImageTwo,posterImageThree,posterImageFour;
    @FXML
    private Label movieOneLabel,movieTwoLabel,movieThreeLabel,movieFourLabel;
    @FXML
    private MenuButton loginButton;
    @FXML
    private TextField ageInput;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private Button ageButton;

    private boolean loggedIn = false;
    private String loggedInUserName;


    @FXML
    public void initialize(){
        movieEngineerList.add(engineer1);
        movieEngineerList.add(engineer2);
        movieEngineerList.add(engineer3);
        movieEngineerList.add(engineer4);


        engineer1.createMovie();
        movieOneLabel.setText(engineer1.getMovie().getTitle());


        engineer2.createMovie();
        movieTwoLabel.setText(engineer2.getMovie().getTitle());

        engineer3.createMovie();
        movieThreeLabel.setText(engineer3.getMovie().getTitle());

        engineer4.createMovie();
        movieFourLabel.setText(engineer4.getMovie().getTitle());

        updateLoginUI();
    }



    @FXML
    public void loginButton() {
        loggedIn = !loggedIn;
        updateLoginUI();

    }

    @FXML
    public void logoutAction() {
        loggedIn = false;
        loggedInUserName = null;
        updateLoginUI();
    }

    private void updateLoginUI() {
        if (loggedIn) {
            loginButton.setText(loggedInUserName);
            logoutMenuItem.setVisible(true);
        } else {
            loginButton.setText("User");
            logoutMenuItem.setVisible(false);
        }
    }
    public void updateLoginStatus(boolean status, String Username){
        loggedIn = status;
        loggedInUserName = Username;
        updateLoginUI();
    }

    @FXML
    public void movieButtonOne(){
        closeCurrentStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoviePage.fxml"));
            Parent root = fxmlLoader.load();

            MoviePageController controller = fxmlLoader.getController();
            controller.setImage(posterImageOne.getImage());
            controller.setEngineer(engineer1);
            controller.setMainController(this);



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void movieButtonTwo(){
        closeCurrentStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoviePage.fxml"));
            Parent root = fxmlLoader.load();

            MoviePageController controller = fxmlLoader.getController();
            controller.setImage(posterImageTwo.getImage());
            controller.setEngineer(engineer2);
            controller.setMainController(this);



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void movieButtonThree(){
        closeCurrentStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoviePage.fxml"));
            Parent root = fxmlLoader.load();

            MoviePageController controller = fxmlLoader.getController();
            controller.setImage(posterImageThree.getImage());
            controller.setEngineer(engineer3);
            controller.setMainController(this);



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void movieButtonFour(){
        closeCurrentStage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoviePage.fxml"));
            Parent root = fxmlLoader.load();

            MoviePageController controller = fxmlLoader.getController();
            controller.setImage(posterImageFour.getImage());
            controller.setEngineer(engineer4);
            controller.setMainController(this);



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void loginPage(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = fxmlLoader.load();

            LoginController loginController = fxmlLoader.getController();
            loginController.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            closeCurrentStage();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLoggedInUser() {
        return loggedInUserName;
    }

    public boolean isLoggedIn() {
        if(loggedIn){
            return true;
        }
        return false;

    }
    private void closeCurrentStage() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void ageRecommendation() {
        String ageWanted = ageInput.getText();

        try {
            int userAge = Integer.parseInt(ageWanted);
            boolean canWatchAnyMovie = false;

            posterImageOne.setVisible(true);
            movieOneLabel.setText(engineer1.getMovie().getTitle());
            posterImageTwo.setVisible(true);
            movieTwoLabel.setText(engineer2.getMovie().getTitle());
            posterImageThree.setVisible(true);
            movieThreeLabel.setText(engineer3.getMovie().getTitle());
            posterImageFour.setVisible(true);
            movieFourLabel.setText(engineer4.getMovie().getTitle());

            for (MovieEngineer engineer : movieEngineerList) {
                Movie movie = engineer.getMovie();
                String movieAgeString = movie.getAgeRecommendation();
                int movieAge = Integer.parseInt(movieAgeString);


                if (userAge >= movieAge) {
                    canWatchAnyMovie = true;
                    System.out.println("Can watch");

                } else {
                    switch (movie.getTitle()) {
                        case "Mario Kart the revenge":
                            posterImageOne.setVisible(false);
                            movieOneLabel.setText("");
                            break;
                        case "Luigi's Mansion: Ghostly Adventures":
                            posterImageTwo.setVisible(false);
                            movieTwoLabel.setText("");
                            break;
                        case "Django Unchained":
                            posterImageThree.setVisible(false);
                            movieThreeLabel.setText("");
                            break;
                        case "Fight Club":
                            posterImageFour.setVisible(false);
                            movieFourLabel.setText("");
                            break;
                    }
                }
            }

            if (!canWatchAnyMovie) {
                showAlert("Too Young", "You are too young to watch any of our movies");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid age input: " + ex.getMessage());
        }
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
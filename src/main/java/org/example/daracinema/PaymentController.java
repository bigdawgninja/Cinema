package org.example.daracinema;

import Builder.Movie;
import Builder.MovieEngineer;
import Database.LoyaltyPoints;
import Halls.Hall;
import Reservation.Reservations;
import Seats.Seat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentController {
    private int price;
    private Queue<Seat> selectedSeats;
    LoyaltyPoints loyaltyPoints = new LoyaltyPoints();
    private MovieEngineer movieEngineer;
    private Movie movie;
    Stack<Reservations> reservationStack = new Stack<>();

    @FXML
    private Label selectedSeatsLabel,priceLabel, movieLabel;
    @FXML
    private MenuButton loginButton;
    @FXML
    private MenuItem loginMenuItem;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private TextField  cvcTextArea,cardNumberTextArea,expTextArea,nameTextField ;
    @FXML
    private Button checkoutButton,mainMenuButton;

     private MainController mainController;
    private Hall hall;


    @FXML
    public void initialize(){
        updateSelectedSeatsLabel();
    }

    public void setSelectedSeats(Queue<Seat> selectedSeats) {
        this.selectedSeats = selectedSeats;
        updateSelectedSeatsLabel();
    }
    @FXML
    private void updateSelectedSeatsLabel() {
        StringBuilder seatsText = new StringBuilder();
        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            seatsText.append("Selected Seats: ");
            for (Seat seat : selectedSeats) {
                seatsText.append(seat.getSeatId()).append("\n");
            }
        } else {
            seatsText.append("No seats selected.");
        }
        selectedSeatsLabel.setText(seatsText.toString());
    }

    @FXML
    private void updatePriceLabel(){
        String username = mainController.getLoggedInUser();
        if (!Objects.equals(username, "User")){
            int loyaltyPoint = loyaltyPoints.getLoyaltyPoints(username);

            if (loyaltyPoint >= 100){
                price = 7 -1;
                priceLabel.setText("Price "+ String.valueOf(price));


            }else{
                price = 7;
                priceLabel.setText("Price " + String.valueOf(price));
            }
        }
        else{
            priceLabel.setText("Price: 7$");
        }
    }
    @FXML
    public void setEngineer(MovieEngineer engineer, Movie movie){
        this.movieEngineer = engineer;
        this.movie = movie;

        movieLabel.setText(movie.getTitle());
    }

    @FXML
    public void setPaymentMainController(MainController mainController){
        this.mainController = mainController;
        updateLoginButton();
        updatePriceLabel();
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

    public void setHall(Hall hall){
        this.hall = hall;
    }

    @FXML
    public void setCheckoutButton(){
        if (checkCard()){
            if (checkEXP()){
                if (setCvcTextArea()){
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptPage.fxml"));
                        Parent root = fxmlLoader.load();

                        ReceiptController receiptController = fxmlLoader.getController();
                        receiptController.setLabels(nameTextField.getText(), movieEngineer, movie,selectedSeatsLabel.getText(),"9h10", price);
                        receiptController.setMainController(mainController);
                        receiptController.setHall(hall);

                        loyaltyPoints.updateLoyaltyPoint(mainController.getLoggedInUser(), 50);
                        loyaltyPoints.updateLoyaltyPointMinus(mainController.getLoggedInUser(), 25);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    showAlert("Payment failed", "Invalid CVC numbers");
                }
            }else {
             showAlert("Payment failed", "Invalid exp date");
            }
        }else{
            showAlert("Payment failed", "Invalid card numbers");
        }

    }

    @FXML
    private boolean setCvcTextArea(){
        String cvc = cvcTextArea.getText();
        Pattern pattern = Pattern.compile("^\\d{3}$");
        Matcher matcher = pattern.matcher(cvc);

        if (matcher.matches()){
            System.out.println("CVC valid ");
            return true;
        }
        else {
            System.out.println("Invalid");
            return false;
        }
    }

    @FXML
    private boolean checkCard(){
        String cardNumber = cardNumberTextArea.getText();

        Pattern pattern = Pattern.compile("^\\d{16}$");
        Matcher matcher = pattern.matcher(cardNumber);

        if (matcher.matches()){
            System.out.println("card valid");
            return true;
        }
        else{
            System.out.println("Invalid");
            return false;
        }
    }

    @FXML
    private boolean checkEXP(){
        String expNumber = expTextArea.getText();
        String regex = "(0[1-9]|1[0-2])/(\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expNumber);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        if (matcher.matches()){
            String[] parts = expNumber.split("/");

            int inputMonth = Integer.parseInt(parts[0]);
            int inputYear = Integer.parseInt(parts[1]) + 2000;

            if (inputYear > currentYear || (inputYear == currentYear && inputMonth >= currentMonth)){
                System.out.println("Expiration date is valid.");
                return true;
            }else {
                System.out.println("Expiration date is in the past.");
                return false;
            }
        }else {
            System.out.println("wrong exp");
            return false;
        }

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

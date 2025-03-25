package org.example.daracinema;

import Database.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
     Singleton databaseSingleton = Singleton.getInstance();
     Connection connection = databaseSingleton.getConnection();
    private MainController mainController;
    private String logInUserName;


    @FXML
    private Button signUpButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField userNameInput,passwordInput;

    @FXML
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    public void setLoginButton(){
        if(connection != null){
            String username = userNameInput.getText();
            String password = passwordInput.getText();

            if (checkedValidation(username, password)) {
                System.out.println("Login successful");
                logInUserName = username;
                mainController.updateLoginStatus(true, logInUserName);

                openMainPage();

            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        }

    }

    @FXML
    private Boolean checkedValidation(String username, String password){
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    public void setSignUpButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpPage.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // messageBox to show if the login is incorrect

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openMainPage() {
        try {
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("Main-Page.fxml"));
            Parent root = fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.updateLoginStatus(true, logInUserName);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) loginButton.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

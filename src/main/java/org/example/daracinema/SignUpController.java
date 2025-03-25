package org.example.daracinema;

import Database.DatabaseController;
import Database.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpController {

    private DatabaseController databaseController = new DatabaseController();

    @FXML
    public void initialize() throws SQLException {
        Singleton databaseSingleton = Singleton.getInstance();
        Connection connection = databaseSingleton.getConnection();

    }

    @FXML
    private Button mainMenuButton,confirmButton;
    @FXML
    private TextField nameInput,usernameInput;
    @FXML
    private PasswordField passwordInput;

    @FXML
    public void setConfirmButton(){
        String name = nameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        databaseController.insertUser(name, username, password);

        nameInput.clear();
        usernameInput.clear();
        passwordInput.clear();
    }



    @FXML
    public void mainMenuButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main-Page.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class adminLogimPageController {

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageviewbig;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField passwordTF;

    @FXML
    private JFXButton homeBtn;

    private Connection connection;

    @FXML
    void initialize() {
        // Load and set the initial image
        Image image = new Image("admin.png");
        Image image2 = new Image("img6.jpg");
        imageviewbig.setImage(image);
        imageview2.setImage(image2);
    }

    @FXML
    void onLoginBtnHandler(ActionEvent event) {
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();

        // Check credentials
        if (validateCredentials(username, password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome " + username);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hmsDashboard.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println("Error loading FXML file: " + e.getMessage());
            }
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password");
        }
    }

    @FXML
    void onhomeBtnhandler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hmsloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) homeBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Establish database connection and query to validate credentials
        String url = "jdbc:mysql://localhost:3306/hospitalms";
        String dbUsername = "root";
        String dbPassword = "root";
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
    
        try {
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String userType = resultSet.getString("type");
                if ("admin".equals(userType)) {
                    return true; // If user is admin, return true
                }
            }
            return false; // If user is not found or not admin, return false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of any error
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

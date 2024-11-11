import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HMSLoginPageController {

    @FXML
    private ImageView logImage;

    @FXML
    private Button loginBtn;

    @FXML
    private Button nextBtn;

    
    @FXML
    private Button patientBtn;

    
    @FXML
    private Button doctorBtn;

    
    @FXML
    private Button adminBtn;

    @FXML
    private Button previousBtn;

    
    int currentIndex = 0;
    String images[] = {"img1.png", "img2.jpeg", "img3.jpg", "img4.jpg", "img5.png"};
    ScheduledExecutorService executorService;

   

    @FXML
    void initialize() {
        // Load and set the initial image
        Image image = new Image(images[currentIndex]);
        logImage.setImage(image);

        // Schedule image change task
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::changeImage, 0, 3, TimeUnit.SECONDS); // Change image every 5 seconds
    }

    private void changeImage() {
        Platform.runLater(() -> {
            currentIndex = (currentIndex + 1) % images.length;
            Image image = new Image(images[currentIndex]);
            logImage.setImage(image);
        });
    }

    @FXML
    void onNextBtn(ActionEvent event) {
        currentIndex = (currentIndex + 1) % images.length;
        Image image = new Image(images[currentIndex]);
        logImage.setImage(image);
        executorService.shutdown();
    }

    @FXML
    void onPreviousBtn(ActionEvent event) {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        Image image = new Image(images[currentIndex]);
        logImage.setImage(image);
        executorService.shutdown();
    }

    @FXML
    void onAdminBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) adminBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    // @FXML
    // void onStopBtn(ActionEvent event) {
    //     executorService.shutdown();
    // }
    // @FXML
    // void onAdminBtn(ActionEvent event) {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("adminloginpage.fxml"));
    //         Parent root = loader.load();
    //         Scene scene = new Scene(root);
    //         Stage stage = (Stage) adminBtn.getScene().getWindow();
    //         stage.setScene(scene);
    //         stage.show();
    //     } catch (IOException e) {
    //         System.err.println("Error loading FXML file: " + e.getMessage());
    //     }
    // }

    @FXML
    void onDoctorBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) doctorBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }

    }


    @FXML
    void onPatientBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patientloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) patientBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }


    }

    }


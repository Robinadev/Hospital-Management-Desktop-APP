import java.io.IOException;
import java.sql.Connection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class patientAppointFormController  
{
    private DB db=new DB();
    private Connection con=db.getConnection();

    @FXML
    private TextField addressTF;

    @FXML
    private TextField appDateTF;

    @FXML
    private JFXButton appointBtn;

    @FXML
    private TextField appointId;

    @FXML
    private JFXButton backbtn;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTf;

    @FXML
    private TextField phoneNumTF;

    @FXML
    void onAppointBtnHandler(ActionEvent event) {
        try {
            con.createStatement()
                    .executeUpdate("insert into appoints (aid, fname, lname, phonenumber,email,address,adate) values(" +
                         "'" + Integer.parseInt(appointId.getText()) + "', " +
                            "'" + firstNameTF.getText() + "', " +
                            "'" + lastNameTf.getText() + "', " +
                            "'" + phoneNumTF.getText() + "', " +
                            "'" + emailTF.getText() + "', " +
                            "'" + addressTF.getText() + "', " +
                            "'" + appDateTF.getText() +  "');");
                            showAlert(AlertType.INFORMATION,"Successfully Appointed!!","Thank you for using our Hospital management system for appointment!!");
                       appointId.clear();
                       firstNameTF.clear();
                       lastNameTf.clear();
                       phoneNumTF.clear();
                       emailTF.clear();
                       addressTF.clear();
                       appDateTF.clear();
            }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    @FXML
    void onbcakbtnhandler(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patientloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
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

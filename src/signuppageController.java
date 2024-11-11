import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class signuppageController {
           private DB db=new DB();
           private Connection con=db.getConnection();

    @FXML
    private TextField firstnameTF;

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageviewbig;

    @FXML
    private TextField lastnameTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField phonenumberTF;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    void onRegisterBtnHandler(ActionEvent event) {
         try {
            con.createStatement()
                    .executeUpdate("insert into users (username, password, type) values(" +
                         "'" + usernameTF.getText() + "', " +
                            "'" + passwordTF.getText() + "', " +
                            "'" + "patient"+"');");
            // stafftable.getItems().clear();
            // getAllStaffs();
            // addstaffidtf.clear();
            // addfnametf.clear();
            // addlastnametf.clear();
            // addphonetf.clear();
            // addpostiontf.clear();
            // addemailtf.clear();
            // addstaffidtf.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patientloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }


    }

}

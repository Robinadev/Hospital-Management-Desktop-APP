import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class registerPageController {

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
    void initialize() {
        // Load and set the initial image
        Image image = new Image("img7.jpg");
        Image image2 = new Image("img9.jpg");
        imageviewbig.setImage(image);
        imageview2.setImage(image2);
    }

    @FXML
    void onRegisterBtnHandler(ActionEvent event)
     {
        

    }

}

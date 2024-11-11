import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class logimPageController {

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageviewbig;

    @FXML
    private JFXTextField passwordTF;

    @FXML
    private JFXTextField usernameTF;

     @FXML
    void initialize() {
        // Load and set the initial image
        Image image = new Image("img7.jpg");
        Image image2 = new Image("img6.jpg");
        imageviewbig.setImage(image);
        imageview2.setImage(image2);
    }

}

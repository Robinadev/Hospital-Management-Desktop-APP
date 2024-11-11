import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class doctorAppointemenTableController {
 ObservableList<appoint> appoints=FXCollections.observableArrayList();
      private DB db=new DB();
      private Connection con=db.getConnection();
      private ResultSet resultSet;  
   
    @FXML
    private TableColumn<appoint, String> addressTF;

    @FXML
    private TableColumn<appoint, Integer> appIdTF;

    @FXML
    private TableColumn<appoint, String> appointDateTF;

    @FXML
    private JFXButton backbtn;

    @FXML
    private TableView<appoint> docterAppointemetTable;

    @FXML
    private TableColumn<appoint, String> emailTF;

    @FXML
    private TableColumn<appoint, String> firstNameTF;

    @FXML
    private TableColumn<appoint, String> lastNameTF;

    @FXML
    private TableColumn<appoint, Integer> phoneNumTF;

    @FXML
    void onbackbtnhandler(ActionEvent event)
     {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("doctorloginpage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }

    }
    public void initialize() {
        try {

            appIdTF.setCellValueFactory(new PropertyValueFactory<appoint, Integer>("aid"));
            firstNameTF.setCellValueFactory(new PropertyValueFactory<appoint, String>("fname"));
            lastNameTF.setCellValueFactory(new PropertyValueFactory<appoint, String>("lname"));
            phoneNumTF.setCellValueFactory(new PropertyValueFactory<appoint, Integer>("phonenumber"));
            emailTF.setCellValueFactory(new PropertyValueFactory<appoint, String>("email"));
            addressTF.setCellValueFactory(new PropertyValueFactory<appoint, String>("address"));
            appointDateTF.setCellValueFactory(new PropertyValueFactory<appoint, String>("adate"));
            
            docterAppointemetTable.setItems(appoints);
            getAllappoint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getAllappoint() {
        try {
            resultSet = con.createStatement().executeQuery("select * from appoints;");
            while (resultSet.next()) {
                  int appid=resultSet.getInt("aid");
                     String firstname=resultSet.getString("fname");
                        String lastname=resultSet.getString("lname");
                          int phonenumber=resultSet.getInt("phonenumber");
                     String email=resultSet.getString("email");
                        String address=resultSet.getString("address");
                        String adate=resultSet.getString("adate");
                        appoints.add(new appoint(appid, firstname, lastname,phonenumber,email, address,adate));
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }


}

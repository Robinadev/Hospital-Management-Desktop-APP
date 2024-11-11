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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class adminAppointmentController{
      ObservableList<appoint> appoints=FXCollections.observableArrayList();
      private DB db=new DB();
      private Connection con=db.getConnection();
      private ResultSet resultSet;      
    @FXML
    private TextField AppIdDeleteTF;

    @FXML
    private TableView<appoint> AppTable;

    @FXML
    private TableColumn<appoint, String> addresscol;

    @FXML
    private TableColumn<appoint, String> appDatecol;

    @FXML
    private TableColumn<appoint, Integer> appIdCol;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TableColumn<appoint, String> emailCol;

    @FXML
    private TableColumn<appoint,String> firstNamecol;

    @FXML
    private TableColumn<appoint, String> lastnamecol;

    @FXML
    private TableColumn<appoint, Integer> phonenumcol;

    @FXML
    void onBackBtnHandler(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hmsDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }


    }

    @FXML
    void onDeleteBtnHandler(ActionEvent event) 
    {
        String  appointtodelete = AppIdDeleteTF.getText();
        if (!appointtodelete.isEmpty()) {
            try {
                con.createStatement()
                        .executeUpdate("DELETE FROM appoints WHERE aid = '" + appointtodelete + "'");
                appoints.clear();
                getAllappoint();
                AppIdDeleteTF.clear();
                showAlert(AlertType.INFORMATION, "This Appointment Entry has Succesfully deleted!!!", "Entry deleted!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
           

    
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void initialize() {
        try {

            appIdCol.setCellValueFactory(new PropertyValueFactory<appoint, Integer>("aid"));
            firstNamecol.setCellValueFactory(new PropertyValueFactory<appoint, String>("fname"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<appoint, String>("lname"));
            phonenumcol.setCellValueFactory(new PropertyValueFactory<appoint, Integer>("phonenumber"));
            emailCol.setCellValueFactory(new PropertyValueFactory<appoint, String>("email"));
            addresscol.setCellValueFactory(new PropertyValueFactory<appoint, String>("address"));
            appDatecol.setCellValueFactory(new PropertyValueFactory<appoint, String>("adate"));
            
            AppTable.setItems(appoints);
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



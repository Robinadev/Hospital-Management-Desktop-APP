import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class patientInformationController {

    ObservableList<patient> patients=FXCollections.observableArrayList();
      private DB db=new DB();
      private Connection con=db.getConnection();
      private ResultSet resultSet;
    @FXML
    private TextField FirstNameTF;

    @FXML
    private Button addBtn;

    @FXML
    private TextField addressTF;

    @FXML
    private TableColumn<patient, String> addresscol;

    @FXML
    private TableColumn<patient, Integer> agecol;

    @FXML
    private TextField agetf;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField deleteIdTF;

    @FXML
    private Button editBtn;

    @FXML
    private TextField editedIdTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TableColumn<patient, String> emailcol;

    @FXML
    private TableColumn<patient, String> fnamecol;

    @FXML
    private TableColumn<patient, String> gencol;

    @FXML
    private TextField genderTF;

    @FXML
    private TableColumn<patient, Integer> insucol;

    @FXML
    private TextField insuranceProviderTF;

    @FXML
    private TextField insuranceTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TableColumn<patient, String> lnamecol;

    @FXML
    private TextField patientIdTF;

    @FXML
    private TableView<patient> patienttable;

    @FXML
    private TextField phoneNoTF;

    @FXML
    private TableColumn<patient, Integer> phonecol;

    @FXML
    private TableColumn<patient, Integer> pidcol;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTF;

    @FXML
void onAddBtn(ActionEvent event)
 {
    try 
    {
        String insertQuery = "INSERT INTO patient (patientid, fname, lname, age, gender, address, phonenumber, email, insuranceno) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
        preparedStatement.setInt(1, Integer.parseInt(patientIdTF.getText()));
        preparedStatement.setString(2, FirstNameTF.getText());
        preparedStatement.setString(3, lastNameTF.getText());
        preparedStatement.setInt(4, Integer.parseInt(agetf.getText()));
        preparedStatement.setString(5, genderTF.getText());
        preparedStatement.setString(6, addressTF.getText());
        preparedStatement.setInt(7, Integer.parseInt(phoneNoTF.getText()));
        preparedStatement.setString(8, emailTF.getText());
        preparedStatement.setInt(9, Integer.parseInt(insuranceTF.getText()));
        
        preparedStatement.executeUpdate();
        
        
    

            patienttable.getItems().clear();
            getAllpatient();
            patientIdTF.clear();
            FirstNameTF.clear();
            lastNameTF.clear();
            agetf.clear();
            emailTF.clear();
            genderTF.clear();
            addressTF.clear();
            phoneNoTF.clear();
            insuranceTF.clear();
            insuranceProviderTF.clear();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }


    }


    @FXML
    void onDeleteBtn(ActionEvent event) throws InterruptedException {
        String  patienttodelete = deleteIdTF.getText();
        if (!patienttodelete.isEmpty()) {
            try {
                con.createStatement()
                        .executeUpdate("DELETE FROM patient WHERE patientid = '" + patienttodelete + "'");
                patients.clear();
                getAllpatient();
                deleteIdTF.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(2000);
        returntohome();

    }

    @FXML
    void onEditBtn(ActionEvent event) 
    {
        int patientid = Integer.parseInt(editedIdTF.getText());
    String firstname = FirstNameTF.getText();
    String lastname = lastNameTF.getText();
    int age = Integer.parseInt(agetf.getText());
    String gender = genderTF.getText();
    String address=addressTF.getText();
    int phonenumber = Integer.parseInt(phoneNoTF.getText());
    int insurance = Integer.parseInt(insuranceTF.getText());

    String email = emailTF.getText();

    if (patientid != 0 && age != 0 && !firstname.isEmpty() && !lastname.isEmpty() && !gender.isEmpty() && !address.isEmpty() && insurance != 0 && phonenumber != 0 && !email.isEmpty()) {
        try {
            // Update the room's details in the database
            String updateQuery = "UPDATE patient SET fname=?, lname=? ,age=? , gender=?, address=?, phonenumber=?,email=?, insuranceno=? WHERE patientid=?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, address);
            preparedStatement.setInt(6, phonenumber);
            preparedStatement.setString(7, email);
            preparedStatement.setInt(8, insurance);
            preparedStatement.setInt(9, patientid);



            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // If update successful, refresh the room table
                patienttable.getItems().clear();
                getAllpatient();
                patientIdTF.clear();
            FirstNameTF.clear();
            lastNameTF.clear();
            agetf.clear();
            emailTF.clear();
            genderTF.clear();
            addressTF.clear();
            phoneNoTF.clear();
            insuranceTF.clear();
            insuranceProviderTF.clear();
            editedIdTF.clear();

                // Clear input fields
               
            } else {
                // Handle case where no room with the provided room number was found
                // You can show an alert or message indicating that no room was updated
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    } else {
        // Handle case where any of the required fields are empty
        // You can show an alert or message indicating that all fields are required
    }
        

    }

   @FXML
void onSearchBtn(ActionEvent event) {
    List<patient> patients = getpatientbyfirstname(searchTF.getText() + "%");

    if (patients.size() > 0) {
        patienttable.getItems().setAll(patients);
        selectFirstEntry();
        searchTF.clear();
    } else {
        showAlert(AlertType.INFORMATION,
        "First Name Not Found",
        "There are no entries with the specified last name");
            
    }

    

}

     public void initialize() {
        try {

            pidcol.setCellValueFactory(new PropertyValueFactory<patient, Integer>("patientid"));
            fnamecol.setCellValueFactory(new PropertyValueFactory<patient, String>("fname"));
            lnamecol.setCellValueFactory(new PropertyValueFactory<patient, String>("lname"));
            agecol.setCellValueFactory(new PropertyValueFactory<patient, Integer>("age"));
            phonecol.setCellValueFactory(new PropertyValueFactory<patient, Integer>("phonenumber"));
            emailcol.setCellValueFactory(new PropertyValueFactory<patient, String>("email"));
            insucol.setCellValueFactory(new PropertyValueFactory<patient, Integer>("insuranceno"));
            addresscol.setCellValueFactory(new PropertyValueFactory<patient, String>("address"));
            gencol.setCellValueFactory(new PropertyValueFactory<patient, String>("gender"));
            patienttable.setItems(patients);
            getAllpatient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getAllpatient()
     {
        try {
            resultSet = con.createStatement().executeQuery("select * from patient;");
            while (resultSet.next()) {
                  int patientid=resultSet.getInt("patientid");
                 String firstname=resultSet.getString("fname");
                        String lastname=resultSet.getString("lname");
                        int age=resultSet.getInt("age");
                        int phonenumber=resultSet.getInt("phonenumber");
                        String gender=resultSet.getString("gender");
                        String address=resultSet.getString("address");
                        String email=resultSet.getString("email");
                        int insurance=resultSet.getInt("insuranceno");
                        patients.add(new patient(firstname, lastname, patientid, age,gender, address,phonenumber,email,insurance));
                     }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}



 public List<patient> getpatientbyfirstname(String fname)
 {
    List<patient> sepatient = new ArrayList<>();
    try {
        String query = "SELECT * FROM patient WHERE fname LIKE ? ORDER BY fname, lname";
        PreparedStatement preparedStatement =con.prepareStatement(query);
        preparedStatement.setString(1, "%" + fname + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            patient pat  = new patient(
                
                resultSet.getString("fname"),
                resultSet.getString("lname"),
                resultSet.getInt("patientid"),
                resultSet.getInt("age"),
                resultSet.getString("gender"),
                resultSet.getString("address"),
                resultSet.getInt("phonenumber"),
                resultSet.getString("email"),
                resultSet.getInt("insuranceno"));
            sepatient.add(pat);
        }
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    
    return sepatient;
}

public void returntohome(){
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hmsDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) deleteBtn.getScene().getWindow();
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
    public void selectFirstEntry() {
        patienttable.getSelectionModel().selectFirst();
    }
}


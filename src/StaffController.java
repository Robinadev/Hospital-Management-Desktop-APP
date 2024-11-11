import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StaffController {
    ObservableList<Staff> staff=FXCollections.observableArrayList();
      private DB db=new DB();
      private Connection con=db.getConnection();
      private ResultSet resultSet;


   
    @FXML
    private JFXButton addBtn;

    @FXML
    private TextField adddepartidtf;

    @FXML
    private TextField addemailtf;

    @FXML
    private TextField addfnametf;

    @FXML
    private TextField addlastnametf;

    @FXML
    private TextField addphonetf;

    @FXML
    private TextField addpostiontf;

    @FXML
    private TextField addstaffidtf;

    @FXML
    private JFXButton backbtn;

    @FXML
    private TextField deletestaffidtf;

    
    @FXML
    private TableColumn<Staff, Integer> staffidcol;

    @FXML
    private TableColumn<Staff, String> emailcol;

    @FXML
    private TableColumn<Staff, String> firstnamecol;

    @FXML
    private TableColumn<Staff, String> lastnamecol;

    @FXML
    private TableColumn<Staff, String> phonenumbercol;

    @FXML
    private TableColumn<Staff, String> positioncol;

    @FXML
    private JFXButton removeBtn;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private TextField searchnametf;


    @FXML
    private TableView<Staff> stafftable;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private TextField updatefnametf;

    @FXML
    private TextField updatelnametf;

    @FXML
    private TextField updatephonetf;
    @FXML
    void OnUpdateBtnhandler(ActionEvent event) {
        String oldStaffId = addstaffidtf.getText();
        String newFirstname = addfnametf.getText();
        String newLastname = addlastnametf.getText();
        String position = addpostiontf.getText();
        String phoneNumber = addphonetf.getText();
        String email = addemailtf.getText();
    
        if (!oldStaffId.isEmpty() && !newFirstname.isEmpty() && !newLastname.isEmpty() && !position.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
            try {
                // Update the staff member's details in the database
                String updateQuery = "UPDATE staff SET fname=?, lname=?, position=?, phonenumber=?, email=? WHERE staffid=?";
                PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                preparedStatement.setString(1, newFirstname);
                preparedStatement.setString(2, newLastname);
                preparedStatement.setString(3, position);
                preparedStatement.setString(4, phoneNumber);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, oldStaffId);
    
                int rowsAffected = preparedStatement.executeUpdate();
    
                if (rowsAffected > 0) {
                    // If update successful, refresh the staff table
                    stafftable.getItems().clear();
                    getAllStaffs();
                    
                    // Clear input fields
                    addstaffidtf.clear();
                    addfnametf.clear();
                    addlastnametf.clear();
                    addphonetf.clear();
                    addpostiontf.clear();
                    addemailtf.clear();
                } else {
                    // Handle case where no staff member with the provided staff ID was found
                    // You can show an alert or message indicating that no staff member was updated
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
    
   //@FXML
// void OnUpdateBtnhandler(ActionEvent event) {
//        String oldstaffId = addstaffidtf.getText();
//         String newFirstname = addfnametf.getText();
//         String newlastname=addlastnametf.getText();
//         String position=addpostiontf.getText();
//         String phonenumber=addphonetf.getText();
//         String email=addemailtf.getText();
//         if (!oldstaffId.isEmpty() && !newFirstname.isEmpty() && !newFirstname.isEmpty() && !newlastname.isEmpty() && !position.isEmpty() && !phonenumber.isEmpty() && !email.isEmpty()) {
//             try {
//                 con.createStatement()
//                         .executeUpdate("UPDATE medical SET fname = '" + newFirstname + "SET ' WHERE firstname = '"
//                                 + oldFirstname + "'");
//                 user.clear();
//                 getAllUsers();
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

    // try {
    //     // Get the selected staff member from the table
    //     Staff selectedStaff = stafftable.getSelectionModel().getSelectedItem();
        
    //     if (selectedStaff != null) {
    //         // Update the staff member's details in the database
    //         String updateQuery = "UPDATE staff SET fname=?, lname=?, position=?, phonenumber=?, email=? WHERE staffid=?";
    //         PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
    //         preparedStatement.setString(1, addfnametf.getText());
    //         preparedStatement.setString(2, addlastnametf.getText());
    //         preparedStatement.setString(3, addpostiontf.getText());
    //         preparedStatement.setString(4, addphonetf.getText());
    //         preparedStatement.setString(5, addemailtf.getText());
    //         preparedStatement.setInt(6, addstaffidtf.getText()); // Assuming you have a method to get the staff ID

    //         preparedStatement.executeUpdate();
            
    //         // Refresh the staff table
    //         stafftable.getItems().clear();
    //         getAllStaffs();
            
    //         // Clear input fields
    //         addstaffidtf.clear();
    //         addfnametf.clear();
//           


   

    @FXML
    void onaddBtnhandler(ActionEvent event) {
        try {
            con.createStatement()
                    .executeUpdate("insert into staff (staffid, fname, lname, position,phonenumber,email) values(" +
                         "'" + Integer.parseInt(addstaffidtf.getText()) + "', " +
                            "'" + addfnametf.getText() + "', " +
                            "'" + addlastnametf.getText() + "', " +
                            "'" + addpostiontf.getText() + "', " +
                            "'" + addphonetf.getText() + "', " +
                            "'" + addemailtf.getText() +"');");
            stafftable.getItems().clear();
            getAllStaffs();
            addstaffidtf.clear();
            addfnametf.clear();
            addlastnametf.clear();
            addphonetf.clear();
            addpostiontf.clear();
            addemailtf.clear();
            addstaffidtf.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onbackhandler(ActionEvent event) {

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hmsDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    @FXML
    void onremoveBtnhandler(ActionEvent event) {
        String staffToDelete = deletestaffidtf.getText();
        if (!staffToDelete.isEmpty()) {
            try {
                con.createStatement()
                        .executeUpdate("DELETE FROM staff WHERE staffid = '" + staffToDelete + "'");
                staff.clear();
                getAllStaffs();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onsearchBtnhandler(ActionEvent event) 
    {
        List<Staff> staffs = getstaffbyfirstname(searchnametf.getText() + "%");

        if (staffs.size() > 0) {
            stafftable.getItems().setAll(staffs);
            selectFirstEntry();
            searchnametf.clear();
        } else {
            showAlert(AlertType.INFORMATION,
            "First Name Not Found",
            "There are no entries with the specified last name");
                
        }
    

    }
    public void initialize() {
        try {

            staffidcol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("staffid"));
            firstnamecol.setCellValueFactory(new PropertyValueFactory<Staff, String>("fname"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<Staff, String>("lname"));
            positioncol.setCellValueFactory(new PropertyValueFactory<Staff, String>("position"));
            phonenumbercol.setCellValueFactory(new PropertyValueFactory<Staff, String>("phonenumber"));
            emailcol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
            stafftable.setItems(staff);
            getAllStaffs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private void getAllStaffs() {
        try {
            resultSet = con.createStatement().executeQuery("select * from staff;");
            while (resultSet.next()) {
                int staffid=resultSet.getInt("staffid");

                        String firstname=resultSet.getString("fname");
                        String lastname=resultSet.getString("lname");
                        String position=resultSet.getString("position");
                        String phonenumber=resultSet.getString("phonenumber");
                        String email=resultSet.getString("email");
                        staff.add(new Staff(staffid, firstname, lastname, position,phonenumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
public List<Staff> getstaffbyfirstname(String fname)
 {
    List<Staff> searchstaff = new ArrayList<>();
    try {
        String query = "SELECT * FROM staff WHERE fname LIKE ? ORDER BY fname, lname";
        PreparedStatement preparedStatement =con.prepareStatement(query);
        preparedStatement.setString(1, "%" + fname + "%");
         resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Staff staff  = new Staff(
                resultSet.getInt("staffid"),
                resultSet.getString("fname"),
                resultSet.getString("lname"),
                resultSet.getString("position"),
                resultSet.getString("phonenumber"),
                resultSet.getString("email"));
            searchstaff.add(staff);
        }
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    
    return searchstaff;
}
private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void selectFirstEntry() {
        stafftable.getSelectionModel().selectFirst();
    }
        }



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

public class roomController {

      ObservableList<Room> room=FXCollections.observableArrayList();
      private DB db=new DB();
      private Connection con=db.getConnection();
      private ResultSet resultSet;


    @FXML
    private JFXButton addBtn;

    @FXML
    private TextField addcapacitytf;

    @FXML
    private TextField adddepartidtf;

    @FXML
    private TextField addroomnotf;

    @FXML
    private TextField addstatustf;


    @FXML
    private JFXButton backBtn;

    @FXML
    private TableColumn<Room, Integer> capacitycol;

    @FXML
    private TextField deleteroomnotf;

    @FXML
    private JFXButton editBtn;

    @FXML
    private TextField editcapacitytf;

    @FXML
    private TextField editroomnotf;

    @FXML
    private TextField editstatustf;

    @FXML
    private JFXButton removeBtn;

    @FXML
    private TableColumn<Room, Integer> rommnocol;

    @FXML
    private TableView<Room> roomtable;

    @FXML
    private TextField searchroomno;

    @FXML
    private JFXButton serachBtn;

    @FXML
    private TableColumn<Room, String> statuscol;

    @FXML
    void onaddroomBtnhandler(ActionEvent event) {

             try {
            con.createStatement()
                    .executeUpdate("insert into room (roomnumber, capacity, status) values(" +
                         "'" + Integer.parseInt(addroomnotf.getText()) + "', " +
                            "'" + Integer.parseInt(addcapacitytf.getText()) + "', " +
                            "'" + addstatustf.getText() 
                             +"');");
            roomtable.getItems().clear();
            getAllRooms();
            addroomnotf.clear();
            addcapacitytf.clear();
            addstatustf.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      public void initialize() {
        try {

            
            rommnocol.setCellValueFactory(new PropertyValueFactory<Room,Integer>("roomnumber"));
           // deptidrcol.setCellValueFactory(new PropertyValueFactory<Room,>("department_id"));
            capacitycol.setCellValueFactory(new PropertyValueFactory<Room,Integer>("capacity"));
            statuscol.setCellValueFactory(new PropertyValueFactory<Room,String>("status"));
            roomtable.setItems(room);
            getAllRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onbackbtnhandler(ActionEvent event) 
    {

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

    private void getAllRooms() {
        try {
            resultSet = con.createStatement().executeQuery("select * from room;");
            while (resultSet.next()) {
                
                         int room_number=resultSet.getInt("roomnumber");
                       // int department_id=resultSet.getInt("department_id");
                        int capacity=resultSet.getInt("capacity");
                        String status=resultSet.getString("status");
                        room.add(new Room(room_number,capacity,status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

@FXML
void oneditBtnhandler(ActionEvent event) {
    int roomNumber = Integer.parseInt(editroomnotf.getText());
    int newCapacity = Integer.parseInt(editcapacitytf.getText());
    String newStatus = editstatustf.getText();

    if (roomNumber != 0 && newCapacity != 0 && !newStatus.isEmpty()) {
        try {
            // Update the room's details in the database
            String updateQuery = "UPDATE room SET capacity=?, status=? WHERE roomnumber=?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setInt(1, newCapacity);
            preparedStatement.setString(2, newStatus);
            preparedStatement.setInt(3, roomNumber);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // If update successful, refresh the room table
                roomtable.getItems().clear();
                getAllRooms();
                
                // Clear input fields
                editroomnotf.clear();
                editcapacitytf.clear();
                editstatustf.clear();
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
    void onremovebtnhandler(ActionEvent event) {
        String roomtodelete = deleteroomnotf.getText();
        if (!roomtodelete.isEmpty()) {
            try {
                con.createStatement()
                        .executeUpdate("DELETE FROM room WHERE roomnumber = '" + roomtodelete + "'");
                room.clear();
                getAllRooms();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onsearchBtnhandler(ActionEvent event) 
    {
        List<Room> room2 = getroombyroomnumber(Integer.parseInt(searchroomno.getText() ) );

        if (room2.size() > 0) {
            roomtable.getItems().setAll(room2);
            selectFirstEntry();
            searchroomno.clear();
        } else {
            showAlert(AlertType.INFORMATION,
            "Room Number Not Found",
            "There are no entries with the specified room number");
                
        }

    }
public List<Room> getroombyroomnumber(int room)
 {
    List<Room> searchroom = new ArrayList<>();
    try {
        String query = "SELECT * FROM room WHERE roomnumber LIKE ? ";
        PreparedStatement preparedStatement =con.prepareStatement(query);
        preparedStatement.setInt(1,  + room );
         resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Room room1  = new Room(
                resultSet.getInt("roomnumber"),
                resultSet.getInt("capacity"),
                resultSet.getString("status"));
               
            searchroom.add(room1);
        }
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    
    return searchroom;
}
private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void selectFirstEntry() {
        roomtable.getSelectionModel().selectFirst();
    }

        }


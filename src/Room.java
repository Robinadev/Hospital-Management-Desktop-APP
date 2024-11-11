public class Room {
   private int roomnumber;
   private int capacity;
   private String status;
public Room(int roomnumber, int capacity, String status) {
    this.roomnumber = roomnumber;
    this.capacity = capacity;
    this.status = status;
}
public int getRoomnumber() {
    return roomnumber;
}
public void setRoomnumber(int roomnumber) {
    this.roomnumber = roomnumber;
}
public int getCapacity() {
    return capacity;
}
public void setCapacity(int capacity) {
    this.capacity = capacity;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}

}

    

    
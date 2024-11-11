public class Staff {
    private int staffid;
    private String fname;
    private String lname;
    private String position;
    private String phonenumber;
    private String email;
    public Staff(int staffid, String fname, String lname, String position, String phonenumber, String email) {
        this.staffid = staffid;
        this.fname = fname;
        this.lname = lname;
        this.position = position;
        this.phonenumber = phonenumber;
        this.email = email;
    }
    public int getStaffid() {
        return staffid;
    }
    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}

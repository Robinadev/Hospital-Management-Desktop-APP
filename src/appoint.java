public class appoint {
    private int aid;
    private String fname;
    private String lname;
    private int phonenumber;
    private String email;
    private String address;
    private String adate;
    public appoint(int aid, String fname, String lname, int phonenumber, String email, String address,
            String adate) {
        this.aid = aid;
        this.fname = fname;
        this.lname = lname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.adate = adate;
    }
    public int getAid() {
        return aid;
    }
    public void setAid(int aid) {
        this.aid = aid;
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
    public int getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAdate() {
        return adate;
    }
    public void setAdate(String adate) {
        this.adate = adate;
    }

    
}

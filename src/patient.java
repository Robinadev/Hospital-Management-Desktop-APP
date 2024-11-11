public class patient 
{
   private String fname;
    private String lname;
    private int patientid;
    private int age;
    private String gender;
    private String address;
    private int phonenumber;
    private String email;
    private int insuranceno;
    public patient(String fname, String lname, int patientid, int age, String gender, String address, int phonenumber,
            String email, int insuranceno) {
        this.fname = fname;
        this.lname = lname;
        this.patientid = patientid;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.insuranceno = insuranceno;
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
    public int getPatientid() {
        return patientid;
    }
    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public int getInsuranceno() {
        return insuranceno;
    }
    public void setInsuranceno(int insuranceno) {
        this.insuranceno = insuranceno;
    }

    
}

package org.apache.struts.employee.model;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String email;
    private String role;
    private String city;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setDob(String dob){
        this.dob = dob;
    }
    public String getDob(){
        return dob;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getCity(){
        return city;
    }
        
}
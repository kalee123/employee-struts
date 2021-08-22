package org.apache.struts.employee.dao;

import org.apache.struts.employee.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import javax.sql.DataSource;  
import javax.naming.*;
import java.util.*;

public class EmployeeDAO {
	// inserst new employee to table
	public static void insertEmployee(Employee emp,Address address){
		
        PreparedStatement prepStmt1 = null;
        PreparedStatement prepStmt2 = null;
        Connection con = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();

            String empSQL = "Insert INTO employees VALUES ( ? , ? , ? , ? , ? , ? , ? )";
            String addressSQL = "Insert INTO address VALUES ( ? , ? , ? , ? )";

            prepStmt1 = con.prepareStatement(empSQL); 
            prepStmt2 = con.prepareStatement(addressSQL);
            
            prepStmt1.setInt(1, emp.getId());
            prepStmt1.setString(2, emp.getFirstName());
            prepStmt1.setString(3, emp.getLastName());
            prepStmt1.setDate(4, Date.valueOf(emp.getDob()));
            prepStmt1.setString(5, emp.getGender()); 
            prepStmt1.setString(6, emp.getEmail());
            prepStmt1.setInt(7, 2);
            prepStmt1.executeUpdate();

            prepStmt2.setInt(1, emp.getId());
            prepStmt2.setInt(2, address.getDoorNo());
            prepStmt2.setString(3, address.getStreet());
            prepStmt2.setString(4, address.getCity()); 
            prepStmt2.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (prepStmt1 != null) {
                try {
                	prepStmt1.close();
                } catch (SQLException e) {}
            }
            if (prepStmt2 != null) {
                try {
                	prepStmt2.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
    }
	
	// Update employe details
    public static void updateEmployee(Employee emp,Address address){
    	PreparedStatement prepStmt1 = null;
        PreparedStatement prepStmt2 = null;
        Connection con = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();

            String empSQL = "UPDATE employees SET first_name=?,last_name=?,dob=?,gender=?,email=?,role_id=? WHERE emp_id="+emp.getId();
            String addressSQL = "UPDATE address SET  door_no=?,street=?,city=? WHERE emp_id="+emp.getId();
    
            prepStmt1 = con.prepareStatement(empSQL);
            prepStmt2 = con.prepareStatement(addressSQL);
            
            prepStmt1.setString(1, emp.getFirstName());
            prepStmt1.setString(2, emp.getLastName());
            prepStmt1.setDate(3, Date.valueOf(emp.getDob()));
            prepStmt1.setString(4, emp.getGender()); 
            prepStmt1.setString(5, emp.getEmail());
            prepStmt1.setInt(6, 2);
            prepStmt1.executeUpdate();
    
            prepStmt2.setInt(1, address.getDoorNo());
            prepStmt2.setString(2, address.getStreet());
            prepStmt2.setString(3, address.getCity()); 
            prepStmt2.executeUpdate();
    
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (prepStmt1 != null) {
                try {
                	prepStmt1.close();
                } catch (SQLException e) {}
            }
            if (prepStmt2 != null) {
                try {
                	prepStmt2.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }        
    
    }
	
    // Search employee based on param
    public static ArrayList<Employee> search(String param){
    	ArrayList<Employee> list = new ArrayList<Employee>();
    	Connection con = null;
    	PreparedStatement prepStmt = null;
    	ResultSet rs = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();

            String SQL = "SELECT employees.emp_id,first_name,last_name,email,role_name,address.city from employees INNER JOIN role on employees.role_id=role.role_id INNER JOIN address on employees.emp_id=address.emp_id WHERE cast(employees.emp_id as CHAR) LIKE '%"+param+"%' OR first_name LIKE '%"+param+"%' OR last_name LIKE '%"+param+"%' OR email LIKE '%"+param+"%' OR role_name LIKE '%"+param+"%' OR city LIKE '%"+param+"%'";
            prepStmt = con.prepareStatement(SQL);
           
            rs = prepStmt.executeQuery();
            
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(rs.getString("emp_id")));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setRole(rs.getString("role_name"));
                emp.setCity(rs.getString("city"));
                list.add(emp);
            }

            rs.close();
            prepStmt.close();
            con.close();
            
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {}
            }
            if (prepStmt != null) {
                try {
                	prepStmt.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return list;
    }
    
    // Delete rows 
    public static ArrayList<Employee> delete(int id){
    	ArrayList<Employee> list = new ArrayList<Employee>();
    	PreparedStatement prepStmt1 = null;
        PreparedStatement prepStmt2 = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();

            String deleteFK = "DELETE FROM address USING employees WHERE employees.emp_id = address.emp_id and employees.emp_id="+id;
            String deletePK = "DELETE FROM employees WHERE emp_id="+id;
            
            prepStmt1 = con.prepareStatement(deleteFK);
            prepStmt2 = con.prepareStatement(deletePK);
            
            prepStmt1.executeUpdate();
            prepStmt2.executeUpdate();

            String fetch="SELECT employees.emp_id,first_name,last_name,email,role_name,city FROM employees INNER JOIN role on employees.role_id=role.role_id JOIN address ON employees.emp_id = address.emp_id ";
            
            PreparedStatement prepStmt = con.prepareStatement(fetch);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(rs.getString("emp_id")));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setRole(rs.getString("role_name"));
                emp.setCity(rs.getString("city"));
                list.add(emp);
            }

        }catch(Exception e){
            System.out.println(e);
        }finally {
        	if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {}
            }
            if (prepStmt1 != null) {
                try {
                	prepStmt1.close();
                } catch (SQLException e) {}
            }
            if (prepStmt2 != null) {
                try {
                	prepStmt2.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return list;
    }
    
    public static EmployeeDetail getEmployeeDetail(int id){
    	EmployeeDetail empDetail = new EmployeeDetail();
    	Connection con = null;
    	PreparedStatement prepStmt = null;
    	ResultSet rs = null;
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();

            String SQL = "SELECT * FROM employees JOIN role ON employees.role_id=role.role_id JOIN address ON employees.emp_id=address.emp_id WHERE employees.emp_id="+id;
            prepStmt = con.prepareStatement(SQL);
           
            rs = prepStmt.executeQuery();

            rs.next();
            Employee emp = new Employee();
            emp.setId(Integer.parseInt(rs.getString("emp_id")));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setEmail(rs.getString("email"));
            emp.setRole(rs.getString("role_name"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            emp.setDob(dateFormat.format(rs.getDate("dob")));
            emp.setGender(rs.getString("gender"));

            Address address=new Address();
            address.setDoorNo(emp.getId());
            address.setDoorNo(rs.getInt("door_no"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            
            empDetail.setEmployee(emp);
            empDetail.setAddress(address);

            rs.close();
            prepStmt.close();
            con.close();

        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {}
            }
            if (prepStmt != null) {
                try {
                	prepStmt.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return empDetail;
    }
    
    public static int getEmployeeId(){
    	int id = 0;
    	Connection con = null;
    	PreparedStatement prepStmt = null;
    	ResultSet rs = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/datasource");
            con = ds.getConnection();
            prepStmt = con.prepareStatement("SELECT MAX(emp_id) FROM employees ");
            rs = prepStmt.executeQuery();
            rs.next();
            id = rs.getInt(1)+1;
            rs.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {}
            }
            if (prepStmt != null) {
                try {
                	prepStmt.close();
                } catch (SQLException e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        return id;
    }
}
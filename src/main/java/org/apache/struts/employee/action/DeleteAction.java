package org.apache.struts.employee.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.employee.dao.EmployeeDAO;
import org.apache.struts.employee.model.Employee;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;


public class DeleteAction extends ActionSupport{
	    
	private static final long serialVersionUID = 1L;
	private int id;
	private ArrayList<Employee> empList;	
	
	public String execute() throws Exception {
		EmployeeDAO dao = new EmployeeDAO();
		empList = dao.delete(id);
		System.out.println(empList);
        return SUCCESS;
    }
	
	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}
	
	public ArrayList<Employee> getEmpList(){
		return empList;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

}
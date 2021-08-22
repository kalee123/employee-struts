package org.apache.struts.employee.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.employee.dao.EmployeeDAO;
import org.apache.struts.employee.model.Employee;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;


public class SearchAction extends ActionSupport{
	    
	private static final long serialVersionUID = 1L;
	private String param;
	private ArrayList<Employee> empList;	
	
	public String execute() throws Exception {
		EmployeeDAO dao = new EmployeeDAO();
		empList = dao.search(param);
		System.out.println(empList);
        return SUCCESS;
    }
	
	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}
	
	public ArrayList<Employee> getEmpList(){
		return empList;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	public String getParam() {
		return param;
	}

}
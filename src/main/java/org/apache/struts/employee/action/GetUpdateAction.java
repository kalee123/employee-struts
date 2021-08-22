package org.apache.struts.employee.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.employee.dao.EmployeeDAO;
import org.apache.struts.employee.model.*;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;


public class GetUpdateAction extends ActionSupport{
	    
	private static final long serialVersionUID = 1L;
	private int id;
	private EmployeeDetail empDetail;
	public String execute() throws Exception {
		EmployeeDAO dao = new EmployeeDAO();
		if(id>0) {
			empDetail = dao.getEmployeeDetail(id);
			System.out.println(empDetail);
		}	
		else {
			id = dao.getEmployeeId();
			System.out.println(id);
		}		
        return SUCCESS;
    }
	
	public void setEmpDetail(EmployeeDetail empDetail) {
		this.empDetail = empDetail;
	}
	
	public EmployeeDetail getEmpDetail(){
		return empDetail;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
}
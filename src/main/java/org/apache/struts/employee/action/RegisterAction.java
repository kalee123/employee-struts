package org.apache.struts.employee.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.employee.model.Employee;
import org.apache.struts.employee.model.Address;
import org.apache.struts.employee.dao.EmployeeDAO;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Employee empBean;
	private Address addressBean;

    public String execute() throws Exception {
    	EmployeeDAO dao = new EmployeeDAO();
    	dao.insertEmployee(empBean, addressBean);
    	
        return SUCCESS;
    }
    
    public Employee getEmpBean() {
        return empBean;
    }
    
    public void setEmpBean(Employee emp) {
        empBean = emp;
    }
    
    public Address getAddressBean() {
        return addressBean;
    }
    
    public void setAddressBean(Address address) {
        addressBean = address;
    }
    
}
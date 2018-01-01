/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Employee;
import edu.gju.alumni.alumniapp.services.EmployeeService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@Named(value = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    private List<Employee> employees;
    private Employee employee;
    @Inject
    private EmployeeService empService;

    @PostConstruct
    @PostActivate
    public void populateEmployees() {
//        this.employee = new Employee();
        try {
            employees = empService.getAllEmployees();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            id = Integer.parseInt(params.get("id"));
            employee = empService.getEmployeeById(id);
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.WARNING, null, employee.getFirstName());
            setEmployee(employee);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.employee;
    }

    public void deleteEmployee() {
        String employeeId = employee.getId();
        try {
            empService.deleteEmployee(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeService getEmpService() {
        return empService;
    }

    public void setEmpService(EmployeeService empService) {
        this.empService = empService;
    }

}

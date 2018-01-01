/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Employee;
import edu.gju.alumni.alumniapp.models.UserGroup;
import edu.gju.alumni.alumniapp.services.EmployeeService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@RequestScoped
@Named(value = "addEditEmployee")
public class AddEditEmployeeBean implements Serializable{

    private Employee employee;
    private Employee editedEmployee;
    private UserGroup empDepaetment;
    int maxEmployeeId;
    private String groupId;
    private String groupName;
    private Map<Integer, String> groups;
    @Inject
    private EmployeeService empService;
    @Inject
    EmployeeBean empBean;

    @PostConstruct
    @PostActivate
    public void init() {
        employee = new Employee();
        editedEmployee = new Employee();
        empDepaetment = new UserGroup();
        groups = new HashMap<>();
        try {
            if (empBean.getEmployee() != null) {
                employee = empBean.getEmployee();
                maxEmployeeId = Integer.parseInt(employee.getId());
                editedEmployee.setId(employee.getId());
                editedEmployee.setFirstName(employee.getFirstName());
                editedEmployee.setLastName(employee.getLastName());
                editedEmployee.setUserName(employee.getUserName());
                editedEmployee.setPassword(employee.getPassword());
                empDepaetment.setUserId(employee.getId());
                editedEmployee.setDepartment(employee.getDepartment());
            } else {
                maxEmployeeId = empService.maxEmployeeId();
                employee.setId(Integer.toString(maxEmployeeId));
                empDepaetment.setUserId(Integer.toString(maxEmployeeId));
                employee.setUserName(Integer.toString(maxEmployeeId));
            }
            groups = empService.getGroupsMap();

        } catch (SQLException ex) {
            Logger.getLogger(AddEditEmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertEmployee() {
        try {
            empDepaetment.setGroupdId(groupId);
            employee.setDepartment(empDepaetment);
            empService.addEmployee(employee);
        } catch (SQLException ex) {
            Logger.getLogger(AddEditEmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployee() {
        try {
            empDepaetment.setGroupdId(groupId);
            empDepaetment.setGroupName(groupName);
            editedEmployee.setDepartment(empDepaetment);
            empService.editEMployee(editedEmployee);

        } catch (SQLException ex) {
            Logger.getLogger(AddEditEmployeeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getMaxEmployeeId() {
        return maxEmployeeId;
    }

    public void setMaxEmployeeId(int maxEmployeeId) {
        this.maxEmployeeId = maxEmployeeId;
    }

    public Map<Integer, String> getGroups() {
        return groups;
    }

    public void setGroups(Map<Integer, String> groups) {
        this.groups = groups;
    }

    public EmployeeService getEmpService() {
        return empService;
    }

    public void setEmpService(EmployeeService empService) {
        this.empService = empService;
    }

    public UserGroup getEmpDepaetment() {
        return empDepaetment;
    }

    public void setEmpDepaetment(UserGroup empDepaetment) {
        this.empDepaetment = empDepaetment;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Employee getEditedEmployee() {
        return editedEmployee;
    }

    public void setEditedEmployee(Employee editedEmployee) {
        this.editedEmployee = editedEmployee;
    }

}

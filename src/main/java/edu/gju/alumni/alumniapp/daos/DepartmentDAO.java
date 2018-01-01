/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.models.Department;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface DepartmentDAO extends ConnectionDAO{
    
    public List<Department> getAllDepartments(int schoolId) throws SQLException;
    
}

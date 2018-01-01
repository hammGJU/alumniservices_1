/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.daos.annotations.DeptDAO;
import edu.gju.alumni.alumniapp.models.Department;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(DepartmentDAO.class)
@Stateless(name = "deptDAOImpl")
@DeptDAO
public class DepartmentDAOImpl extends ConnectionDAOImpl implements DepartmentDAO, Serializable {

    private Connection connection;

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            this.connection = super.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DepartmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Department> getAllDepartments(int shcoolId) throws SQLException {
        List<Department> departments = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_DEPARTMENTS.toString());
        ps.setInt(1, shcoolId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Department dep = PopulateModels.populateDepartment(rs);

            departments.add(dep);
        }
        rs.close();
        ps.close();
        return departments;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import edu.gju.alumni.alumniapp.models.Student;
import java.sql.SQLException;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface AlumniEditDAO extends ConnectionDAO {

    public Student getAlumniInformation(String studentId) throws SQLException;

    public Map<String, String> getJobStatusMap() throws SQLException;

    public int editAlumniInfo(Student student);

}

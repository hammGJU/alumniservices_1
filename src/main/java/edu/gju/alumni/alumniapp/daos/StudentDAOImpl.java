/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.daos.annotations.StdDAO;
import edu.gju.alumni.alumniapp.beans.UserSessionBean;
import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
@Local(StudentDAO.class)
@Stateless(name = "studentDAOImpl")
@StdDAO
public class StudentDAOImpl extends ConnectionDAOImpl implements StudentDAO, Serializable {

    private Connection connection;
//    @Resource(lookup = "jdbc/Alumni_GJU")
//    private DataSource dataSource;
//    @Inject
    private UserSessionBean sessionBean;

    public StudentDAOImpl() throws Exception {

    }

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            this.connection = super.getConnection();
//        try {
//            this.connection = dataSource.getConnection();
////        if (sessionBean.getConnection() != null) {
////            this.connection = sessionBean.getConnection();
////        } else {
////            try {
////                this.connection = dataSource.getConnection();
////            } catch (SQLException ex) {
////                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
////            }
////
////        }
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (Exception ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_STUDENTS.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Student s = PopulateModels.populateStudent(rs);
            allStudents.add(s);
        }

        rs.close();
        ps.close();
        return allStudents;
    }

    @Override
    public List<Email> getStudentEmail(String studentId) throws SQLException {
        List<Email> emails = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_STUDENT_EMAIL.toString());
        ps.setString(1, studentId);
        ResultSet rs = ps.executeQuery();
        emails = PopulateModels.populateEmail(rs);
        return emails;

    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_STUDENT_BY_ID.toString());
        ps.setString(1, Integer.toString(id));
        ResultSet rs = ps.executeQuery();
        Student student = new Student();
        while (rs.next()) {
            student = PopulateModels.populateStudent(rs);
        }
        rs.close();
        ps.close();
        return student;

    }

    @Override
    public int addStudent(Student student) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.INSERT_STUDENT.toString());
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getLastName());
        ps.setDate(3, new Date(student.getDateOfBirth().getTime()));
        ps.setString(4, student.getNationality());
        ps.setString(5, student.getSchool().getId());
        ps.setString(6, student.getDepartment().getId());
        ps.setInt(7, student.getDegree().getId());
        ps.setDouble(8, student.getGpa());
        ps.setString(9, student.getGender().getId());
        ps.setString(10, student.getStatus().getId());
        ps.setDouble(11, student.getYearsExperience());
        ps.setString(12, student.getFacebookLink());
        ps.setString(13, student.getLinkedInLink());
        ps.setString(14, student.getGradYear().getId());
        ps.setString(15, student.getGradSemester().getId());

        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    @Override
    public int editStudent(Student student) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.EDIT_STUDENT.toString());
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getLastName());
        ps.setDate(3, (Date) student.getDateOfBirth());
        ps.setString(4, student.getNationality());
        ps.setString(5, student.getSchool().getId());
        ps.setString(6, student.getDepartment().getId());
        ps.setInt(7, student.getDegree().getId());
        ps.setDouble(8, student.getGpa());
        ps.setString(9, student.getGender().getId());
        ps.setString(10, student.getStatus().getId());
        ps.setDouble(11, student.getYearsExperience());
        ps.setString(12, student.getFacebookLink());
        ps.setString(13, student.getLinkedInLink());
        ps.setString(14, student.getGradYear().getId());
        ps.setString(15, student.getGradSemester().getId());
        ps.setString(16, student.getId());

        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    @Override
    public int deleteStudent(int studentId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.DELETE_STUDENT.toString());
        ps.setString(1, Integer.toString(studentId));
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

//    public DataSource getDataSource() {
//        return dataSource;
//    }
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    public UserSessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(UserSessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

}

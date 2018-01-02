/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@ViewScoped
@Named(value = "manyStudentsBean")
public class ContactManyStudents {

    List<Student> listOfStudents;
    private List<Email> emails;
    @Inject
    private StudentService studentService;
    @Inject
    private StudentBean studentBean;

    public ContactManyStudents() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        listOfStudents = studentBean.getListOfStudents();
        studentBean.setListOfStudents(null);

    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void sendEmail() {

    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

}

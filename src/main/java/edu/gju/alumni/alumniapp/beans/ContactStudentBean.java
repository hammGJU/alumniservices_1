/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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
@Named(value = "viewStudent")
public class ContactStudentBean implements Serializable {

    private Student student;
    private Map<String, String> emails;
    @Inject
    private StudentService studentService;
    @Inject
    private StudentBean studentBean;

    public ContactStudentBean() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        student = studentBean.getSelectedStudent();
        studentBean.setSelectedStudent(null);
        emails = new HashMap<>();
        for (int i = 0; i < student.getEmails().size(); i++) {
            emails.put(student.getId(), student.getEmails().get(i).toString());
        }

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void sendEmail() {

    }

    public Map<String, String> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, String> emails) {
        this.emails = emails;
    }

}

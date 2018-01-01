/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import java.io.Serializable;
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

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void sendEmail() {

    }

}

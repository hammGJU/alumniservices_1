/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import edu.gju.alumni.alumniapp.utils.SendEmail;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author hesham
 */
@ViewScoped
@Named(value = "viewStudent")
public class ContactStudentBean implements Serializable {

    private Student student;
    List<Student> listOfStudents;
    private List<Email> emails;
    @Inject
    private StudentService studentService;
    @Inject
    private StudentBean studentBean;

    private final String emailUserName = "ce743project.hamm@gmail.com";
    private final String emailPassowrd = "ham@ce743";
    private String toEmail;
    private String message;
    private String subject;

    public ContactStudentBean() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        student = studentBean.getSelectedStudent();
        listOfStudents = studentBean.getListOfStudents();
        studentBean.setSelectedStudent(null);
        studentBean.setListOfStudents(null);
        try {
            if (student != null) {
                emails = studentService.getStudentEmails(student.getId());
                int size = emails.size();
                if (size == 2) {
                    student.setEmail1(emails.get(0).getEmail());
                    student.setEmail2(emails.get(1).getEmail());
                } else if (size == 1) {
                    student.setEmail1(emails.get(0).getEmail());
                    student.setEmail2("NOT");
                } else {
                    student.setEmail1("NOT");
                    student.setEmail2("NOt");
                }
            } else {
                for (Student s : listOfStudents) {
                    emails = studentService.getStudentEmails(s.getId());
                    int size = emails.size();
                    if (size == 2) {
                        s.setEmail1(emails.get(0).getEmail());
                        s.setEmail2(emails.get(1).getEmail());
                    } else if (size == 1) {
                        s.setEmail1(emails.get(0).getEmail());
                        s.setEmail2("NOT");
                    } else {
                        s.setEmail1("NOT");
                        s.setEmail2("NOt");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContactStudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void sendEmail() {
        List<String> toEmails = new ArrayList<>();
        SendEmail sendEmail = new SendEmail();
        for (Student s : listOfStudents) {
            String email1 = s.getEmail1();
            String email2 = s.getEmail2();
            if (email1 != null && email2 == null) {
                toEmails.add(email1);
            }
            if (email1 == null && email2 != null) {
                toEmails.add(email2);
            }
            if (email1 != null && email2 != null) {
                toEmails.add(email2);
                toEmails.add(email1);
            }
        }
        try {
            for (int i = 0; i < toEmails.size(); i++) {

                sendEmail.sendEmail(emailUserName, emailPassowrd, emailUserName, toEmails.get(i), subject, message);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(ContactStudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}

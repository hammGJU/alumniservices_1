/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

/**
 *
 * @author hesham
 */
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {

    private Student student;

    private boolean skip;

    @Inject
    private StudentService studentService;

    @PostConstruct
    public void init() {
        student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void save() {
        int result = -1;
        try {
            result = studentService.addStudent(student);
        } catch (SQLException ex) {
            Logger.getLogger(UserWizard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result > 0) {
            FacesMessage msg = new FacesMessage("Successful", "Welcome :" + student.getFirstName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}

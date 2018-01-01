/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author hesham
 */
public class StudentJob implements Serializable {

    private String studentId;
    private String companyName;
    private String studentPosition;
    private String conntry;
    private String city;
    private Date startDate;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStudentPosition() {
        return studentPosition;
    }

    public void setStudentPosition(String studentPosition) {
        this.studentPosition = studentPosition;
    }

    public String getConntry() {
        return conntry;
    }

    public void setConntry(String conntry) {
        this.conntry = conntry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.studentId);
        hash = 89 * hash + Objects.hashCode(this.companyName);
        hash = 89 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentJob other = (StudentJob) obj;
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentJob{" + "studentId=" + studentId + ", companyName=" + companyName + ", studentPosition=" + studentPosition + ", conntry=" + conntry + ", city=" + city + ", startDate=" + startDate + '}';
    }

}

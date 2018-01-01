/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author hesham
 */
public class GraduationSemester implements Serializable {

    private String id;
    private String semesterName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.semesterName);
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
        final GraduationSemester other = (GraduationSemester) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.semesterName, other.semesterName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraduationSemester{" + "id=" + id + ", semesterName=" + semesterName + '}';
    }

}

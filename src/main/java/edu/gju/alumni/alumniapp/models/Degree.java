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
public class Degree implements Serializable {

    private int id;
    private String degreeName;

    public Degree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.degreeName);
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
        final Degree other = (Degree) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.degreeName, other.degreeName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Degree{" + "id=" + id + ", degreeName=" + degreeName + '}';
    }

}

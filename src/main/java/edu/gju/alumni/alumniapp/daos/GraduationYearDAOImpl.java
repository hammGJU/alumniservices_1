/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.daos.annotations.GradYearDAO;
import java.io.Serializable;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(GraduationYearDAO.class)
@Stateless(name="gradYearDAO")
@GradYearDAO
public class GraduationYearDAOImpl extends ConnectionDAOImpl implements GraduationYearDAO, Serializable{
    
}

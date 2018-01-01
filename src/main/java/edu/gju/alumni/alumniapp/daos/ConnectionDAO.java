/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import java.sql.Connection;
import javax.ejb.Local;
import javax.enterprise.inject.Default;
import javax.naming.NamingException;

/**
 *
 * @author hesham
 */
@Local
@Default
public interface ConnectionDAO {

    public Connection getConnection() throws Exception;

    public void closeConnection() throws Exception;

}

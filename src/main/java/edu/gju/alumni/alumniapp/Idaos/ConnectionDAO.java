/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Local;
import javax.enterprise.inject.Default;

/**
 *
 * @author hesham
 */
@Local
@Default
public interface ConnectionDAO {

    public Connection getConnection() throws SQLException;

    public void closeConnection() throws SQLException;

    public String login(String userName, String userPassword) throws SQLException;

    public void logout() throws SQLException;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.daos.annotations.ConnDAO;
import java.io.Serializable;
import java.sql.Connection;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
@Stateless(name = "connectionDAOImpl")
@ConnDAO
public class ConnectionDAOImpl implements ConnectionDAO, Serializable {

    @Resource(lookup = "jdbc/Alumni_GJU")
    private DataSource dataSource;
    private Connection connection;

    public ConnectionDAOImpl() {

    }

    @Override
    @Produces
    public Connection getConnection() throws Exception {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = this.dataSource.getConnection();
            return this.connection;
        } else {
            return this.connection;
        }

    }

    @Override
    @Remove
    public void closeConnection() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}

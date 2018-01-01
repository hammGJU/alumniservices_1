/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.daos.annotations.ConnDAO;
import edu.gju.alumni.alumniapp.daos.ConnectionDAO;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@Stateful
public class ConnectionService {

    private Connection connection;

    @Inject
    @ConnDAO
    private ConnectionDAO connectionDao;

    public ConnectionService() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        this.sessionBean = (UserSessionBean) facesContext.getELContext().getELResolver().getValue(
//                facesContext.getELContext(), null, "userBean");
    }

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            connection = connectionDao.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            connection = connectionDao.getConnection();
        }

        return connection;
    }

    @Remove
    public void closeConnection() throws Exception {
//        if (sessionBean != null) {
        if (connection != null) {
            connection.close();
        }
//        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.services.ConnectionService;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserSessionBean implements Serializable {

    private String userName;
    private String userPassword;
    private Connection connection;
    private String selectedItemId;
    private int menuIndex = 0;

    @Inject
    private ConnectionService connService;

    public UserSessionBean() {
    }

    public void login() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
        boolean success = false;
        this.connection = connService.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from employee");
        String user;
        String password;
        while (rs.next()) {
            user = rs.getString("EMPLOYEE_FIRST_NAME");
            password = rs.getString("EMPLOYEE_LAST_NAME");
            if (this.userName.equals(user) && this.userPassword.equals(password)) {
                success = true;
                break;
            } else {
                success = false;
            }
        }
        if (success) {
            if (facesContext != null) {

                navHandler.handleNavigation(facesContext, null, "/registrar/registrar_first_page?faces-redirect=true");
            }
        }

    }

    public void logout() throws Exception {
        if (connection != null) {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            connection.close();
            connection = null;
        }
        setUserName(null);
        setUserPassword(null);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();

    }

    public ConnectionService getConnService() {
        return connService;
    }

    public void setConnService(ConnectionService connService) {
        this.connService = connService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public int getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

}

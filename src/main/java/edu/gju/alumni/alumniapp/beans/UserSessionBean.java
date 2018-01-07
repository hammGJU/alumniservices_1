/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.services.ConnectionService;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
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

    public void login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
        boolean success = false;
        String uGroup = null;
        try {
            uGroup = connService.login(this.userName, this.userPassword);
        } catch (SQLException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (uGroup != null) {
            success = true;
        }

        if (success) {
            if (facesContext != null) {
                if (uGroup.equals(AlumniServEnum.ADMIN.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/admin/admin_first_page?faces-redirect=true");
                } else if (uGroup.equals(AlumniServEnum.ALUMNI.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/alumni/alumni_first_page?faces-redirect=true");
                } else if (uGroup.equals(AlumniServEnum.REGISTRAR.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/registrar/registrar_first_page?faces-redirect=true");
                } else if (uGroup.equals(AlumniServEnum.DSA.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/dsa/dsa_employee_first_page?faces-redirect=true");
                } else {
                    navHandler.handleNavigation(facesContext, null, "/accountant/accountant_first_page?faces-redirect=true");
                }
            }
        }

    }

    @PreDestroy
    public void logout() {
        try {
            connService.logout();
        } catch (SQLException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
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

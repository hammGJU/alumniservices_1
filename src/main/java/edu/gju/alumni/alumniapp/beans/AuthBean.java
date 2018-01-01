/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.services.ConnectionService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hesham
 */
@ManagedBean(name = "authBean")
@ViewScoped
public class AuthBean implements Serializable {

    private static final long serialVersionUID = 1L;
//    private static final Logger logger = LoggerFactory.getLogger(AuthBean.class);

    @EJB
    private ConnectionService service;

    @ManagedProperty("#{user}")
    private UserSessionBean userBean;

    private String userName;
    private String password;
    private String originalURL;

    public ConnectionService getService() {
        return service;
    }

    public void setService(ConnectionService service) {
        this.service = service;
    }

    public UserSessionBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserSessionBean userBean) {
        this.userBean = userBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    @PostConstruct
    public void init() {
//        logger.debug("called");

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath();
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }

//        logger.debug("originalURL: {}", originalURL);
    }

    public void login() throws IOException, ServletException, Exception {
//        logger.debug("called");
//        logger.debug("originalURL: {}", originalURL);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(userName, password);
        } catch (ServletException e) {
//            JsfUtils.addErrorMessage(e, "authentication failed");
            return;
        }

        Connection conn = service.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM USERS x WHERE x.user_name = " + userName);
        String user = null;
        String password;
        while (rs.next()) {
            user = rs.getString("USER_NAME");
            password = rs.getString("USER_PASSWORD");
        }
        if (user != null) {
//            JsfUtils.addErrorMessage("authorization failed");
            externalContext.redirect(originalURL);
        }

//        userBean.setPerson(new Object());
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath());
    }

    // getters/setters
}

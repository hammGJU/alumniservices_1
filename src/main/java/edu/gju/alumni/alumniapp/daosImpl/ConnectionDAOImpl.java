/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.ConnectionDAO;
import edu.gju.alumni.alumniapp.daos.annotations.ConnDAO;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateless;
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
//    @Produces
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = this.dataSource.getConnection();
//            this.connection.setAutoCommit(false);
            return this.connection;
        } else {
            return this.connection;
        }

    }

    @Override
    @Remove
    public void closeConnection() throws SQLException {

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

    @Override
    public String login(String userName, String userPassowrd) throws SQLException {
        String uGroup = null;
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_USERS.toString());
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        String ug = null;
        String un;
        String up;
        while (rs.next()) {
            ug = rs.getString(AlumniServEnum.GROUP_NAME.toString());
            un = rs.getString(AlumniServEnum.USER_NAME.toString());
            up = rs.getString(AlumniServEnum.PASSWORD.toString());
            if (userName.equals(un) && userPassowrd.equals(up)) {
                switch (ug) {
                    case "Admin":
                        uGroup = AlumniServEnum.ADMIN.toString();
                        break;
                    case "Alumni":
                        uGroup = AlumniServEnum.ALUMNI.toString();
                        break;
                    case "DSA":
                        uGroup = AlumniServEnum.DSA.toString();
                        break;
                    case "Registrar":
                        uGroup = AlumniServEnum.REGISTRAR.toString();
                        break;
                    case "Accountant":
                        uGroup = AlumniServEnum.ACCOUNTANT.toString();
                    default:
                        uGroup = null;
                }
            }
            break;
        }
        return uGroup;
    }

    @Override
    public void logout() throws SQLException {
        if (connection != null) {

            closeConnection();
            connection = null;
        }
    }

}

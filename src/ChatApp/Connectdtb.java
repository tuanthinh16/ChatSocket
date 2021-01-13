/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

import com.mysql.jdbc.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class Connectdtb {

    private Connection conn;
    private ResultSet rs;
    private Statement st;

    public static Connection getConnectdtb() {
        final String database = "account-chat";
        final String url = "jdbc:mysql://localhost:8180/" + database;
        final String user = "root";
        final String pass = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connectdtb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connectdtb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi rồi");
        }

        return null;
    }

}

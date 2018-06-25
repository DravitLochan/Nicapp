/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dravit
 */
public class DBConnect {
    
    private final String url = "jdbc:postgresql://localhost:5432/dravit";
    private final String user = "dravit";
    private final String password = "123456";
    
    private DBConnect() {
    }
    
    public static DBConnect getInstance() {
        return DBConnectHolder.INSTANCE;
    }
    
    private static class DBConnectHolder {

        private static final DBConnect INSTANCE = new DBConnect();
    }
    
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the PostgreSQL server successfully.");
        return conn;
    }
    
    public static void main(String[] args){
        try {
            getInstance().connect();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

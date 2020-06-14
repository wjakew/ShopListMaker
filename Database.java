/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
 */
package shoplistmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *Object for maintaining database connection
 * @author jakub
 */
public class Database {
    String version = "v1.0.0";
    
    boolean logged_as_user = false;
    boolean connected = false;
    
    String user_login = null;
    int user_id = -1;
    
    Connection con = null;
    ResultSet rs = null;
    
    // main constructor
    Database (){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/programnote_database?" +
                                   "user=root&password=password");
            System.out.println("Connected: "+con.toString());
            connected = true;
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode()); 
        }
    }
    /**
     * Database.close()
     * @throws SQLException
     * Function for closing the connection
     */
    void close() throws SQLException{
        log_out();
        con.close();
    }
    /**
     * Database_Connection.get_query()
     * @param query
     * @return ResultSet
     * @throws SQLException
     * Function returns ResultSet from database
     */
    ResultSet get_query(String query) throws SQLException{
        try{
            System.out.println("Trying to get query: "+query);
            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();
        }catch (SQLException ex){
            System.out.println("Getting ResultSet return fail");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }
    
    /**
     * Database.log_in(String username, String password)
     * @param username
     * @param password
     * @return boolean
     * @throws SQLException
     * Function for logging into database
     */
    boolean log_in(String username, String password) throws SQLException{
        
        String query = " SELECT * FROM USER_INFO where user_login = ? and user_password = ?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,password);
        
        ResultSet rs = ps.executeQuery();
        
        if ( rs.next() ){
            logged_as_user = true;
            user_login = rs.getString("user_login");
            user_id = rs.getInt("user_id");
            return true;
        }
        else{
            return false;
        }   
    }
    /**
     * Database.log_out()
     * Function for logging out from database
     */
    void log_out(){
        logged_as_user = false;
        user_id = -1;
        user_login = null;
    }
}

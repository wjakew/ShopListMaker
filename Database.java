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
import java.util.Date;

/**
 *Object for maintaining database connection
 * @author jakub
 */
public class Database {
    String version = "v1.0.2";
    
    boolean logged_as_user = false;
    boolean connected = false;
    
    String user_login = null;
    int user_id = -1;
    
    Connection con = null;
    ResultSet rs = null;
    
    // main constructor
    Database () throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
            String url="jdbc:mysql://localhost:3306/shoplistmaker_database";
            String username="root";
            String password="password";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connected: "+con.toString());
            connected = true;
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode()); 
        }
    }
    
    void optional_connection(String url,String username,String password) throws 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,username,password);
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
    /**
     * Database.get_dictionary_id()
     * @return int
     * @throws SQLException
     * Returns id of the user dictionary, -1 if not exist
     */
    int get_dictionary_id() throws SQLException{
        String query = "SELECT * FROM DICTIONARY_USER where user_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        
        if ( rs.next() ){
            return rs.getInt("dictionary_id");
        }
        return -1;
    }
    
    /**
     * Database.get_category_id(String key)
     * @param key
     * @return Integer
     * @throws SQLException
     * Function for getting category id
     */
    int get_category_id(String key) throws SQLException{
        String query = "SELECT category_id from CATEGORY where category_name = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,key);
        ResultSet rs = ps.executeQuery();
        
        if( rs.next() ){
            return rs.getInt("category_id");
        }
        return -1;
    }
    /**
     * Database.add_to_main_storage(String key,Sting value)
     * @param key
     * @param value
     * @return boolean
     * @throws SQLException
     * Function for adding to main storage on the database with checking on duplicates
     */
    boolean add_to_main_storage(String key,String value) throws SQLException{
        int category_id = get_category_id(key);
        String query = "INSERT INTO MAIN_STORAGE (user_id, category_id,main_name) \n" +
                       "SELECT ?,?,?\n" +
                       "WHERE NOT EXISTS (Select `main_name` From `MAIN_STORAGE` WHERE `main_name` = ?) LIMIT 1;";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,Integer.toString(user_id));
        ps.setString(2,Integer.toString(category_id));
        ps.setString(3,value);
        ps.setString(4,value);
        System.out.println("QUERY: "+ps.toString());
        try{
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    boolean add_shoplist(Shoplifter cart) throws SQLException{
        // first we have to sync dictionaries
        offload_dictionary(cart.dict);
        // in that moment we have synced all new stuff with database
        ArrayList<String> items_id = new ArrayList<>();
        for ( String value : cart.combine_collections()){
            int index = item_id(value);
            
            if ( index != -1){
               items_id.add(Integer.toString(index));
            }
        }
        
        // we have all ids in items_id collection
        if ( !items_id.isEmpty() ){
            String elements = items_id.toString();
            elements = elements.substring(1, elements.length()-2);
            System.out.println(elements);  
        }
        return false;
    }
    /**
     * Database.item_id(String value)
     * @param value
     * @return Integer
     * @throws SQLException 
     * Returns database id of an item. If item didn\t exists returns -1
     */
    int item_id(String value) throws SQLException{
        String query = "SELECT element_id FROM ELEMENT where element_name = ? and user_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, value);
        ps.setInt(2,user_id);
        
        ResultSet rs = ps.executeQuery();
        
        if ( rs.next() ){
            return rs.getInt("element_id");
        }
        return -1;
    }

    /**
     * Database.add_element(String key,String value,int dictionary_id,String note)
     * @param key
     * @param value
     * @param dictionary_id
     * @param note
     * @return boolean
     * @throws SQLException 
     * Function adds elements to the database
     */
    boolean add_element(String key,String value,int dictionary_id,String note) throws SQLException{
        
        int category_id = get_category_id(key);
        
        String query = "INSERT INTO ELEMENT (category_id,user_id,dictionary_id,element_name,element_note,element_valid)\n" +
                       "SELECT ?,?,?,?,?,?\n" +
                       "WHERE NOT EXISTS (Select `element_name` From `ELEMENT` WHERE `element_name` =?) LIMIT 1;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, Integer.toString(category_id));
        ps.setString(2, Integer.toString(user_id));
        ps.setString(3, Integer.toString(dictionary_id));
        ps.setString(4, value);
        ps.setString(5, note);
        ps.setString(6, Integer.toString(1));
        ps.setString(7, value);
        try{
            ps.execute();
            add_to_main_storage(key,value);
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    /**
     * Database.get_amount_of_keys()
     * @return Integer
     * @throws SQLException
     * Returns number of values
     */
    int get_amount_of_values() throws SQLException{
        String query = "SELECT COUNT(*)\n" +
                       "FROM ELEMENT where user_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,user_id);
        ResultSet rs = ps.executeQuery();
        
        if ( rs.next() ){
            return rs.getInt("COUNT(*)");
        }
        return 0;
    }
    /**
     * Database.update_dictionary_date()
     * @throws SQLException 
     * Updates date of the dictionary update
     */
    void update_dictionary_date() throws SQLException{
        Date ac = new Date();
        String query = "UPDATE DICTIONARY_USER SET dictionary_date = ? where user_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,ac.toString());
        ps.setInt(2,user_id);
    }
    /**
     * Database.get_dictionary_date()
     * @return String
     * Returns date of the dictionary update
     */
    String get_dictionary_date() throws SQLException{
        String query = "SELECT dictionary_date from DICTIONARY_USER where user_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,user_id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()){
            return rs.getString("dictionary_date");
        }
        return null;
    }
    
    /**
     * Database.offload_dictionary(DictReader to_offload)
     * @param to_offload
     * @throws SQLException 
     * Function for adding dictionary
     */
    void offload_dictionary(DictReader to_offload) throws SQLException{
        if ( get_dictionary_id() == -1 ){
            System.out.println("There is no user dictionary. Adding one...");
            Date actual_date = new Date();
            String query = "INSERT INTO DICTIONARY_USER\n"
                    + "(user_id,dictionary_date)\n"
                    + "VALUES\n"
                    + "(?,?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setString(2,actual_date.toString());
            ps.execute();
            System.out.println("QUERY: "+ps.toString());
        }
        int dictionary_id = get_dictionary_id();
        update_dictionary_date();
        for(String klucz:  to_offload.klucze){
            int index = to_offload.klucze.indexOf(klucz);
            klucz = klucz.substring(1);
            for (String wartosc : to_offload.wartosci.get(index)){
                add_element(klucz,wartosc,dictionary_id,"");
            }
        }
    }
    
    /**
     * Database.load_dictionary()
     * @throws SQLException 
     * @return DictReader
     * Loading dictionary from database
     */
    DictReader load_dictionary() throws SQLException{
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<ArrayList<String>> values = new ArrayList<>();
        
        String query = "SELECT * from CATEGORY;";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while ( rs.next() ){    // looping on categories
            int index = rs.getInt("category_id");
            String category_name = "%"+rs.getString("category_name");
            keys.add(category_name);
            String element_query = "SELECT * FROM ELEMENT WHERE category_id = ? and user_id = ?";
            PreparedStatement ps_element = con.prepareStatement(element_query);
            
            ps_element.setInt(1, index);
            ps_element.setInt(2,user_id);
            
            ResultSet rs_element = ps_element.executeQuery();   // setting query for each of the categories 
            ArrayList<String> rotate_value = new ArrayList<>();
            
            while ( rs_element.next() ){ // looping on element database
                rotate_value.add(rs_element.getString("element_name"));
            }
            values.add(rotate_value);
            }
        return new DictReader(keys,values);
        }
}

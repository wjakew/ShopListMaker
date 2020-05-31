/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

/**
 *
 * @author jakubwawak
 */
public class InfoHandler {
    
    String version = "v.1.3";
    Shoplifter act_cart;
    int lg = 0;
    String dict_path = "";
    int mode = 0;
    
    Config to_pass;
    
    InfoHandler(){
        act_cart=null;
        to_pass = null;
    }
    
    void dic(String src){
        dict_path = src;
        lg = 1;
    }
    
    void add_config(Config actual){
        to_pass = actual;
        dict_path = actual.get_dict_path();
    }
    
    Config get_config(){
        return to_pass;
        
    }
    
}

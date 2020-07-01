/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jakubwawak
 */
public class ShopListMaker {

    /**
     * @param args the command line arguments
     */
    static InfoHandler act;
    
    static Database actual;
    
    static int test = 0;
    
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        actual = new Database();
                
        if (actual.connected){
            JOptionPane.showMessageDialog(null, "Database connected");
            }
        else{
            JOptionPane.showMessageDialog(null, "Unable to connect to the database");
            // database connector window
            
            new optional_database_window(null,true,act);
            actual = act.actual;
            
            if (actual.connected){
                JOptionPane.showMessageDialog(null,"Succesfully connected to the optional database");
            }
            else{
                JOptionPane.showMessageDialog(null, "Still unable to connect to the database");
            }
        }
        
        if(test == 1){//warunek dla testowania kodu

        }
        else{
        if(!new File("config_file.txt").exists()) { 
                
                JFileChooser myFileChooser = new JFileChooser();
                myFileChooser.setDialogTitle("No dictionary file. - Choose it:");

                if(myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    String path = myFileChooser.getSelectedFile().getAbsolutePath();

                    if(path.contains("dict")&&path.endsWith(".txt")){
                        //act.lg=1;
                        //act.dict_path=path;
                        
                        act = new InfoHandler();
                        act.add_database(actual);
                        Config act_config = new Config(act);

                        act.lg=1;
                        
                        String imie = JOptionPane.showInputDialog("Hi! What's your name?:");
                        act_config.make(imie, path);
                        act.add_config(act_config);
                        JOptionPane.showMessageDialog(null,"Restart the app");
                        new main_window(act);
                    }
                    else{
                        JOptionPane.showMessageDialog(myFileChooser, "Error while loading dict file");
                        System.exit(0);
                    }       
                }
                else{
                    System.exit(0);
                }
            }
        else{
                act = new InfoHandler();
                File f = new File("dict.txt");

                Config act_config = new Config(act);

                act.add_config(act_config);
                act.lg=1;
                
                act.dict_path = act_config.get_dict_path();
                act.add_database(actual);
                File test = new File(act_config.get_dict_path());
                if(test.exists()){
                    System.out.println(act.get_config().get_dict_path());
                    new main_window(act);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error while loading dict file");
                    JFileChooser myFileChooser = new JFileChooser();
                    myFileChooser.setDialogTitle("Choose dictionary directory:");

                    if(myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        String path = myFileChooser.getSelectedFile().getAbsolutePath();

                        if(path.contains("dict")&&path.endsWith(".txt")){
                            try {
                                act.to_pass.change_path(path);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Changed - restart the program");
                }

                } 
        }
    }
    
}

/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
    
    static int test = 0;
    
    public static void main(String[] args) throws FileNotFoundException {
        
        if(test == 1){//warunek dla testowania kodu

        }
        else{
        if(!new File("config_file.txt").exists()) { 
                JFileChooser myFileChooser = new JFileChooser();
                myFileChooser.setDialogTitle("Brak Pliku Słownikowego - Wybierz go");

                if(myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    String path = myFileChooser.getSelectedFile().getAbsolutePath();

                    if(path.contains("dict")&&path.endsWith(".txt")){
                        //act.lg=1;
                        //act.dict_path=path;
                        
                        act = new InfoHandler();

                        Config act_config = new Config(act);

                        act.lg=1;
                        
                        String imie = JOptionPane.showInputDialog("Poznajmy sie! Podaj swoje imie:");
                        act_config.make(imie, path);
                        act.add_config(act_config);
                        JOptionPane.showMessageDialog(null,"Zalecamy restart aplikacji");
                        new main_window(act);
                    }
                    else{
                        JOptionPane.showMessageDialog(myFileChooser, "Niepoparawny plik slownikowy");
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
                File test = new File(act_config.get_dict_path());
                if(test.exists()){
                    System.out.println(act.get_config().get_dict_path());
                    new main_window(act);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Brak pliku slownikowego.");
                    JFileChooser myFileChooser = new JFileChooser();
                    myFileChooser.setDialogTitle("Podaj lokalizacje pliku");

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
                    JOptionPane.showMessageDialog(null,"Zmieniono - uruchom ponownie");
                }

                } 
        }
    }
    
}

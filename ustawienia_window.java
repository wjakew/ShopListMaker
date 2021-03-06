/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static shoplistmaker.ShopListMaker.act;
import static shoplistmaker.ShopListMaker.actual;

/**
 *
 * @author User
 */
public class ustawienia_window extends javax.swing.JDialog {

    /**
     * Creates new form ustawienia_window
     */
    InfoHandler program_info;
    public ustawienia_window(java.awt.Frame parent, boolean modal,InfoHandler act_inf) {
        super(parent, modal);
        program_info = act_inf;
        
        initComponents();
        jLabel4.setText(program_info.version);
        setLocationRelativeTo(null);
        imie_label.setText(act_inf.to_pass.get_imie());
        
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        imie_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mod_imie_button = new javax.swing.JButton();
        mod_sciezka_button = new javax.swing.JButton();
        wyslij_slownik_button = new javax.swing.JButton();
        podglad_button = new javax.swing.JButton();
        merge_dictionaries_button = new javax.swing.JButton();
        button_showdictionary = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SLM Settings");

        jLabel1.setText("Your Name");

        imie_label.setText("jLabel2");

        jLabel2.setText("Path of dictionary file");

        mod_imie_button.setText("Edit");
        mod_imie_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_imie_buttonActionPerformed(evt);
            }
        });

        mod_sciezka_button.setText("Edit");
        mod_sciezka_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_sciezka_buttonActionPerformed(evt);
            }
        });

        wyslij_slownik_button.setText("Send your dictionary");
        wyslij_slownik_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyslij_slownik_buttonActionPerformed(evt);
            }
        });

        podglad_button.setText("Show path");
        podglad_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                podglad_buttonActionPerformed(evt);
            }
        });

        merge_dictionaries_button.setText("Merge dictionaries");
        merge_dictionaries_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                merge_dictionaries_buttonActionPerformed(evt);
            }
        });

        button_showdictionary.setText("Show dictionary");
        button_showdictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_showdictionaryActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(94, 94, 94)
                            .addComponent(imie_label)
                            .addGap(43, 43, 43)
                            .addComponent(mod_imie_button, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(podglad_button)
                            .addGap(18, 18, 18)
                            .addComponent(mod_sciezka_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(wyslij_slownik_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_showdictionary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(merge_dictionaries_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(imie_label)
                    .addComponent(mod_imie_button))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(podglad_button)
                    .addComponent(mod_sciezka_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wyslij_slownik_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_showdictionary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(merge_dictionaries_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mod_imie_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_imie_buttonActionPerformed
        String nowe_imie = JOptionPane.showInputDialog("Type your name:");
        try {
            program_info.to_pass.change_imie(nowe_imie);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        imie_label.setText(program_info.to_pass.get_imie());
    }//GEN-LAST:event_mod_imie_buttonActionPerformed

    private void mod_sciezka_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_sciezka_buttonActionPerformed
        JFileChooser myFileChooser = new JFileChooser();
        myFileChooser.setDialogTitle("You changed the dictionary path");

        if(myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            String path = myFileChooser.getSelectedFile().getAbsolutePath();

            if(path.contains("dict")&&path.endsWith(".txt")){
                try {
                    program_info.to_pass.change_path(path);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JOptionPane.showMessageDialog(this,program_info.to_pass.get_dict_path());
    }//GEN-LAST:event_mod_sciezka_buttonActionPerformed

    private void podglad_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_podglad_buttonActionPerformed
        JOptionPane.showMessageDialog(this,program_info.to_pass.get_dict_path());
    }//GEN-LAST:event_podglad_buttonActionPerformed

    private void wyslij_slownik_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyslij_slownik_buttonActionPerformed
        DictReader act = new DictReader("",program_info);
        String email = JOptionPane.showInputDialog("Type e-mail");
        if ( email.contains("@") ){
            try {
                MailSender ms = new MailSender(act,email);
                ms.run();
                JOptionPane.showMessageDialog(this, "Dictionary was sent");
            } catch (MessagingException ex) {
                Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_wyslij_slownik_buttonActionPerformed

    private void button_showdictionaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_showdictionaryActionPerformed
        DictReader d = new DictReader("",program_info);
        new showdictionary_window(null,true,d);
    }//GEN-LAST:event_button_showdictionaryActionPerformed

    private void merge_dictionaries_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_merge_dictionaries_buttonActionPerformed
        JFileChooser myFileChooser = new JFileChooser();
                myFileChooser.setDialogTitle("Choose dictionary file to merge");

                if(myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    String path = myFileChooser.getSelectedFile().getAbsolutePath();

                    if(path.contains("dict")&&path.endsWith(".txt")){
                        //act.lg=1;
                        //act.dict_path=path;
                        
                        DictReader to_merge = new DictReader(path,program_info);
                        
                        program_info.dictionary.merge(to_merge);
                        try {
                            program_info.dictionary.save_dict();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ustawienia_window.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"There isn't dictionary file!");
                    }       
                }
                else{
                    JOptionPane.showMessageDialog(this, "Merging procedure stopped");
                }
    }//GEN-LAST:event_merge_dictionaries_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_showdictionary;
    private javax.swing.JLabel imie_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton merge_dictionaries_button;
    private javax.swing.JButton mod_imie_button;
    private javax.swing.JButton mod_sciezka_button;
    private javax.swing.JButton podglad_button;
    private javax.swing.JButton wyslij_slownik_button;
    // End of variables declaration//GEN-END:variables
}

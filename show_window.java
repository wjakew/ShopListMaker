/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jakubwawak
 */
public class show_window extends javax.swing.JFrame {

    /**
     * Creates new form show_window
     */
    Shoplifter actual;
    InfoHandler act;
    
    DefaultListModel<String> lista_wio = new DefaultListModel<>();
    DefaultListModel<String> lista_napoje = new DefaultListModel<>();
    DefaultListModel<String> lista_przyprawy = new DefaultListModel<>();
    DefaultListModel<String> lista_pieczywo = new DefaultListModel<>();
    DefaultListModel<String> lista_przekaski = new DefaultListModel<>();
    DefaultListModel<String> lista_chemia = new DefaultListModel<>();
    DefaultListModel<String> lista_zdrz = new DefaultListModel<>();
    DefaultListModel<String> lista_nabial = new DefaultListModel<>();
    
    public show_window(java.awt.Frame parent, boolean modal,InfoHandler of) {
        actual = of.act_cart;
        act = of;
        refresh_lists();
        initComponents();
        if(!actual.brak_kategorii.isEmpty()){
            JOptionPane.showMessageDialog(null,"Uwaga! Niektórych składników koszyka nie udało się skategoryzować. Dokonaj korekcji.");
            korekcja_button.setEnabled(true);
            uwaga.setText("Pamietaj o dokonaniu korekcji!");
        }
        else{
            korekcja_button.setEnabled(false);
            uwaga.setText("");
        }
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }
    
    
    void refresh_lists(){
        lista_wio.clear();
        lista_napoje.clear();
        lista_przyprawy.clear();
        lista_pieczywo.clear();
        lista_przekaski.clear();
        lista_chemia.clear();
        lista_zdrz.clear();
        lista_nabial.clear();
        
        for(String wio: actual.warzywaiowoce){
            lista_wio.addElement(wio);
        }
        for (String napoje: actual.napoje){
            lista_napoje.addElement(napoje);
        }
        for (String przyprawy: actual.przyprawy){
            lista_przyprawy.addElement(przyprawy);
        }
        for(String pieczywo: actual.pieczywo){
            lista_pieczywo.addElement(pieczywo);
        }
        for(String przekaski: actual.przekaski){
            lista_przekaski.addElement(przekaski);
        }
        for(String chemia: actual.chemia){
            lista_chemia.addElement(chemia);
        }
        for(String zdrz: actual.zdrowezywienie){
            lista_zdrz.addElement(zdrz);
        }
        for(String nabial: actual.nabial){
            lista_nabial.addElement(nabial);
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        warzywa_i_owoce_list = new javax.swing.JList<>();
        napoje_list = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        przyprawy_list = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pieczywo_list = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        przekaski_list = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        chemia_list = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        zdrowe_zywienie_list = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        generacja_button = new javax.swing.JButton();
        cofnij_button = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        nabial_list = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        korekcja_button = new javax.swing.JToggleButton();
        uwaga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Warzywa i Owoce");

        warzywa_i_owoce_list.setModel(lista_wio);
        jScrollPane2.setViewportView(warzywa_i_owoce_list);

        napoje_list.setModel(lista_napoje);

        jLabel2.setText("Napoje");

        przyprawy_list.setModel(lista_przyprawy);
        jScrollPane3.setViewportView(przyprawy_list);

        jLabel3.setText("Przyprawy");

        pieczywo_list.setModel(lista_pieczywo);
        jScrollPane4.setViewportView(pieczywo_list);

        jLabel4.setText("Pieczywo");

        przekaski_list.setModel(lista_przekaski);
        jScrollPane5.setViewportView(przekaski_list);

        jLabel5.setText("Przekąski");

        chemia_list.setModel(lista_chemia);
        jScrollPane6.setViewportView(chemia_list);

        jLabel6.setText("Chemia");

        zdrowe_zywienie_list.setModel(lista_zdrz);
        jScrollPane7.setViewportView(zdrowe_zywienie_list);

        jLabel7.setText("Zdrowe Żywienie");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("Podsumowanie Twoich Zakupów");

        generacja_button.setText("Generuj Liste");
        generacja_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generacja_buttonActionPerformed(evt);
            }
        });

        cofnij_button.setText("Cofnij");
        cofnij_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cofnij_buttonActionPerformed(evt);
            }
        });

        nabial_list.setModel(lista_nabial);
        jScrollPane8.setViewportView(nabial_list);

        jLabel9.setText("Nabiał");

        korekcja_button.setText("Korekcja");
        korekcja_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                korekcja_buttonActionPerformed(evt);
            }
        });

        uwaga.setText("jLabel10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(154, 154, 154)
                        .addComponent(jLabel4)
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(napoje_list, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cofnij_button, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(57, 57, 57)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(generacja_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(488, 488, 488)
                                .addComponent(uwaga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(korekcja_button)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addGap(158, 158, 158)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel9)
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(korekcja_button)
                    .addComponent(uwaga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(napoje_list, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generacja_button)
                            .addComponent(cofnij_button)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generacja_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generacja_buttonActionPerformed
        new generacja_window(this,true,act);
    }//GEN-LAST:event_generacja_buttonActionPerformed

    private void cofnij_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cofnij_buttonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cofnij_buttonActionPerformed

    private void korekcja_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_korekcja_buttonActionPerformed
        new korekta_window(this,true,act);
        refresh_lists();
        uwaga.setText("Korekcja zostala dokonana.");
        korekcja_button.setEnabled(false);
    }//GEN-LAST:event_korekcja_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> chemia_list;
    private javax.swing.JButton cofnij_button;
    private javax.swing.JButton generacja_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToggleButton korekcja_button;
    private javax.swing.JList<String> nabial_list;
    private javax.swing.JList<String> napoje_list;
    private javax.swing.JList<String> pieczywo_list;
    private javax.swing.JList<String> przekaski_list;
    private javax.swing.JList<String> przyprawy_list;
    private javax.swing.JLabel uwaga;
    private javax.swing.JList<String> warzywa_i_owoce_list;
    private javax.swing.JList<String> zdrowe_zywienie_list;
    // End of variables declaration//GEN-END:variables
}

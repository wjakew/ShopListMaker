/*
JAKUB WAWAK
ALL RIGHTS RESERVED.
kubawawak@gmail.com
 */
package shoplistmaker;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author jakubwawak
 */
public class generacja_window extends javax.swing.JDialog {

    /**
     * Creates new form generacja_window
     */
    InfoHandler info;
    
    public generacja_window(java.awt.Frame parent, boolean modal,InfoHandler of) {
        super(parent, modal);
        info = of;
        
        initComponents();
        
        panel_tekstowy.setText(info.act_cart.make_list());
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    String get_list(){
        String ret = panel_tekstowy.getText();
        ret = ret +"\n"+"Milych Zakupow zycza tworcy aplikacji ShopListMaker\n";
        return ret;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel_tekstowy = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        zamknij = new javax.swing.JButton();
        copy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(panel_tekstowy);

        jLabel1.setText("Tak prezentuje się Twoja lista zakupów:");

        zamknij.setText("Zamknij");
        zamknij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zamknijActionPerformed(evt);
            }
        });

        copy.setText("Kopiuj do schowka");
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(copy)
                        .addGap(18, 18, 18)
                        .addComponent(zamknij, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copy)
                    .addComponent(zamknij))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed
        StringSelection stringSelection = new StringSelection(info.act_cart.make_list());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(this, "Lista zostala skopiowana do schowka.");
        System.exit(0);
    }//GEN-LAST:event_copyActionPerformed

    private void zamknijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zamknijActionPerformed
        JOptionPane.showMessageDialog(this, "Dziekujemy za skorzystanie z SLM");
        System.exit(0);
    }//GEN-LAST:event_zamknijActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane panel_tekstowy;
    private javax.swing.JButton zamknij;
    // End of variables declaration//GEN-END:variables
}

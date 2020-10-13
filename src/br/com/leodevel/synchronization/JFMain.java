package br.com.leodevel.synchronization;

import br.com.leodevel.synchronization.models.DataSynchronize;
import br.com.leodevel.synchronization.models.User;
import br.com.leodevel.synchronization.services.DataSynchronizeService;
import br.com.leodevel.synchronization.services.UserService;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

public class JFMain extends javax.swing.JFrame {

    private TrayIcon trayIcon;
    private SystemTray tray = SystemTray.getSystemTray();

    private final List<DataSynchronize> listDataSynchronize;
    private final List<JPDataSynchronize> listPanelsDataSynchronize;

    public JFMain() {
        initComponents();
       
        listDataSynchronize = new ArrayList<>();
        listPanelsDataSynchronize = new ArrayList<>();
        jpDataSynchronize.setLayout(new MigLayout("wrap 1,gap 18 18"));

        loadDataSynchronize();

        try {
            Image icon = Toolkit.getDefaultToolkit().
                    createImage(getClass().
                            getResource("/br/com/leodevel/synchronization/images/favicon.png")).
                    getScaledInstance(18, 18, 0);
            trayIcon = new TrayIcon(icon);
            tray.add(trayIcon);
            trayIcon.setToolTip("Monitorar Sincronização entre Tabelas");
        } catch (AWTException e) {
            System.exit(0);
        }

        addMouseListener();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpDataSynchronize = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitorar sincronizações");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sincronizações"));

        jScrollPane1.setBorder(null);

        jpDataSynchronize.setPreferredSize(new java.awt.Dimension(500, 296));

        javax.swing.GroupLayout jpDataSynchronizeLayout = new javax.swing.GroupLayout(jpDataSynchronize);
        jpDataSynchronize.setLayout(jpDataSynchronizeLayout);
        jpDataSynchronizeLayout.setHorizontalGroup(
            jpDataSynchronizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );
        jpDataSynchronizeLayout.setVerticalGroup(
            jpDataSynchronizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jpDataSynchronize);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jMenu1.setText("Configuração");

        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Nova sincronização");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(false);

    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        new JDList<>(
                this,
                true,
                UserService.getInstance(),
                User.class,
                JDUser.class).setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JDDataSynchronize jdNew = new JDDataSynchronize(
                this,
                true);

        jdNew.setVisible(true);

        if (jdNew.dataSynchronize != null) {
            addDataSynchronize(jdNew.dataSynchronize);
        }
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFMain().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpDataSynchronize;
    // End of variables declaration//GEN-END:variables

    private void addMouseListener() {
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isVisible()) {
                    login();
                }
            }
        });
    }
    
    private void login(){
        new JDLogin(this, true, this).setVisible(true);
    }
    
    private void loadDataSynchronize() {
        try {
            for (DataSynchronize dataSynchronize : DataSynchronizeService.getInstance().getAll()) {
                addDataSynchronize(dataSynchronize);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addDataSynchronize(DataSynchronize dataSynchronize) {

        listDataSynchronize.add(dataSynchronize);

        JPDataSynchronize jPAlarmeModulo = new JPDataSynchronize(this, dataSynchronize);

        listPanelsDataSynchronize.add(jPAlarmeModulo);

        jpDataSynchronize.add(jPAlarmeModulo);
        jpDataSynchronize.validate();
        jpDataSynchronize.repaint();

    }

    public void removeDataSynchronize(DataSynchronize dataSynchronize) {

        listDataSynchronize.removeIf(obj -> Objects.equals(obj.getId(), dataSynchronize.getId()));

        JPDataSynchronize jpSynchronize = listPanelsDataSynchronize.stream().filter(obj -> Objects.equals(obj.dataSynchronize.getId(), dataSynchronize.getId())).findFirst().orElse(null);

        jpDataSynchronize.remove(jpSynchronize);
        jpDataSynchronize.validate();
        jpDataSynchronize.repaint();

        listPanelsDataSynchronize.remove(jpSynchronize);

    }
    
}

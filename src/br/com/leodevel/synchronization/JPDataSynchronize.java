package br.com.leodevel.synchronization;

import br.com.leodevel.synchronization.models.DataSynchronize;
import br.com.leodevel.synchronization.models.LastReading;
import br.com.leodevel.synchronization.modules.DataSynchronizeModule;
import br.com.leodevel.synchronization.services.DataSynchronizeService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class JPDataSynchronize extends javax.swing.JPanel {

    private DataSynchronizeModule dataSynchronizeModule;
    public DataSynchronize dataSynchronize;
    private final JFMain jfMain;

    public JPDataSynchronize(JFMain jfMain, DataSynchronize dataSynchronize) {
        initComponents();
        
        this.jfMain = jfMain;
        this.dataSynchronize = dataSynchronize;
        
        setData();        
        
        if (dataSynchronize.getStartImmediately() != null && dataSynchronize.getStartImmediately()) {
            module();
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        btIniciar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLogs = new javax.swing.JButton();

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/leodevel/synchronization/images/cancel.png"))); // NOI18N

        btIniciar.setText("Iniciar");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btLogs.setText("Logs");
        btLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLogs)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btIniciar)
                        .addComponent(btEditar)
                        .addComponent(btExcluir)
                        .addComponent(btLogs))
                    .addComponent(lbIcon, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed

        int option = JOptionPane.showConfirmDialog(null, "Deseja realmente "
                + "excluir ?", "Escolha", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option != 0) {
            return;
        }

        try {

            DataSynchronizeService.getInstance().delete(dataSynchronize);

            File log = Paths.get("logs/data_synchronize_" + dataSynchronize.getId() + ".log").toFile();
            if (log.exists()) {
                log.delete();
            }
            
            File lastReading = Paths.get(LastReading.getFile(dataSynchronize.getId())).toFile();
            if (lastReading.exists()) {
                lastReading.delete();
            }

            jfMain.removeDataSynchronize(dataSynchronize);

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!",
                    "Êxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btExcluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed

        dataSynchronize = DataSynchronizeService.getInstance().getById(dataSynchronize.getId());

        JDDataSynchronize jdEdit = new JDDataSynchronize(
                jfMain,
                true,
                dataSynchronize);

        jdEdit.setVisible(true);

        this.dataSynchronize = jdEdit.dataSynchronize;

        setData();

    }//GEN-LAST:event_btEditarActionPerformed

    private void btLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogsActionPerformed

        File log = Paths.get("logs/data_synchronize_" + dataSynchronize.getId() + ".log").toFile();

        if (log.exists()) {

            try {
                Desktop.getDesktop().open(log);
            } catch (IOException ex) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo de log encontrado!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btLogsActionPerformed

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed

        module();

    }//GEN-LAST:event_btIniciarActionPerformed

    private void module() {

        if (btIniciar.getText().equals("Iniciar")) {

            try {

                dataSynchronizeModule = new DataSynchronizeModule(dataSynchronize);
                dataSynchronizeModule.start();

                btIniciar.setText("Parar");

                lbIcon.setIcon(new javax.swing.ImageIcon(getClass().
                        getResource("/br/com/leodevel/synchronization/images/start.png")));

                btEditar.setEnabled(false);
                btExcluir.setEnabled(false);

            } catch (Exception ex) {
            }

        } else {

            try {
                dataSynchronizeModule.close();
                dataSynchronizeModule.interrupt();
                dataSynchronizeModule.join();
            } catch (InterruptedException ex) {
            }

            dataSynchronizeModule = null;
            btIniciar.setText("Iniciar");

            lbIcon.setIcon(new javax.swing.ImageIcon(getClass()
                    .getResource("/br/com/leodevel/synchronization/images/cancel.png")));

            btEditar.setEnabled(true);
            btExcluir.setEnabled(true);

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btLogs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables

    private void setData() {
        lbName.setText(dataSynchronize.getName());
    }
    
}

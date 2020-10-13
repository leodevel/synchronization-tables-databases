package br.com.leodevel.synchronization;

import br.com.leodevel.synchronization.models.Column;
import br.com.leodevel.synchronization.models.DataMapping;
import br.com.leodevel.synchronization.models.DataSource;
import br.com.leodevel.synchronization.models.DataSynchronize;
import br.com.leodevel.synchronization.models.TableColumn;
import br.com.leodevel.synchronization.services.DataSourceService;
import br.com.leodevel.synchronization.services.DataSynchronizeService;
import br.com.leodevel.synchronization.shared.TableModelCustom;
import br.com.leodevel.synchronization.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JDDataSynchronize extends javax.swing.JDialog {

    private final int action;
    public DataSynchronize dataSynchronize;
    private final TableModelCustom<DataMapping> tableModel;
    private List<TableColumn> allColumnsSource;
    private List<TableColumn> allColumnsDestination;
    private static final List<Column> COLUMNS = Arrays.asList(
            new Column("Origem", "columnSource", String.class, true),
            new Column("Destino", "columnDestination", String.class, true),
            new Column("Tipo Origem", "columnSource.columnType", String.class, false),
            new Column("Tipo Destino", "columnDestination.columnType", String.class, false),
            new Column("Valor Padrão", "valueDefault", Object.class, true));

    public JDDataSynchronize(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.action = 1;

        tableModel = new TableModelCustom<>(COLUMNS);
        tbDataMapping.setModel(tableModel);

        setTitle("Novo");

    }

    public JDDataSynchronize(java.awt.Frame parent, boolean modal, DataSynchronize dataSynchronize) {
        super(parent, modal);
        initComponents();

        this.dataSynchronize = dataSynchronize;
        this.action = 2;

        tableModel = new TableModelCustom<>(COLUMNS);
        tbDataMapping.setModel(tableModel);

        setValuesFields();

        setTitle("Editar");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDataSourceHost = new javax.swing.JTextField();
        txtDataSourceUser = new javax.swing.JTextField();
        txtDataSourcePassword = new javax.swing.JPasswordField();
        cbDataSourceTable = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDataSourceDatabaseName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataDestinationHost = new javax.swing.JTextField();
        txtDataDestinationUser = new javax.swing.JTextField();
        txtDataDestinationPassword = new javax.swing.JPasswordField();
        cbDataDestinationTable = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDataDestinationDatabaseName = new javax.swing.JTextField();
        chkReplaceIfExist = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        txtInterval = new javax.swing.JSpinner();
        chkStartImmediately = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDataMapping = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPrimaryKeySource = new javax.swing.JTextField();
        txtPrimaryKeyDestination = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        chkReprocessAll = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sincronizar tabelas");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Origem"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Host:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Usuário:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Senha:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tabela:");

        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("<html><u>BUSCAR</u></html>");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Banco:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataSourceHost)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbDataSourceTable, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDataSourceUser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDataSourcePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                    .addComponent(txtDataSourceDatabaseName))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDataSourceHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDataSourceDatabaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDataSourceUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDataSourcePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDataSourceTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Destino"));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Host:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Usuário:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Senha:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tabela:");

        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("<html><u>BUSCAR</u></html>");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Banco:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDataDestinationHost, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbDataDestinationTable, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDataDestinationUser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataDestinationPassword)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDataDestinationDatabaseName))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDataDestinationHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtDataDestinationDatabaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDataDestinationUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDataDestinationPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDataDestinationTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chkReplaceIfExist.setText("Substituir se existir:");
        chkReplaceIfExist.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkReplaceIfExist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReplaceIfExistActionPerformed(evt);
            }
        });

        jLabel9.setText("Intervalo:");

        txtInterval.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        chkStartImmediately.setText("Iniciar automáticamente:");
        chkStartImmediately.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Mapeamento"));

        jScrollPane1.setBorder(null);

        tbDataMapping.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origem", "Destino", "Tipo", "Chave Primária", "Remover"
            }
        ));
        jScrollPane1.setViewportView(tbDataMapping);

        jButton1.setText("CARREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btRemover.setText("REMOVER");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        jLabel17.setText("Chave primária (origem):");

        jLabel18.setText("Chave primária (destino):");

        txtPrimaryKeySource.setEnabled(false);

        txtPrimaryKeyDestination.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrimaryKeySource, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrimaryKeyDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(btRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btRemover)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(txtPrimaryKeySource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrimaryKeyDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btSalvar.setText("SALVAR");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        jLabel12.setText("Nome:");

        jLabel15.setText("Reprocessar:");

        chkReprocessAll.setText("Tudo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(chkReplaceIfExist)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkStartImmediately)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkReprocessAll)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btSalvar)
                                .addGap(19, 19, 19)))))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkReplaceIfExist)
                            .addComponent(chkStartImmediately))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(chkReprocessAll)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSalvar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

    private void chkReplaceIfExistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReplaceIfExistActionPerformed

    }//GEN-LAST:event_chkReplaceIfExistActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked

        showTables(txtDataSourceHost, txtDataSourceDatabaseName,
                txtDataSourceUser, txtDataSourcePassword, cbDataSourceTable);

    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        showTables(txtDataDestinationHost, txtDataDestinationDatabaseName,
                txtDataDestinationUser, txtDataDestinationPassword, cbDataDestinationTable);

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            DataSource source = getDataSource(txtDataSourceHost, txtDataSourceDatabaseName, txtDataSourceUser,
                    txtDataSourcePassword, cbDataSourceTable);
            DataSource destination = getDataSource(txtDataDestinationHost, txtDataDestinationDatabaseName,
                    txtDataDestinationUser, txtDataDestinationPassword, cbDataDestinationTable);

            allColumnsSource = DataSourceService.getInstance().getTableColumnsByDataSource(source);
            allColumnsDestination = DataSourceService.getInstance().getTableColumnsByDataSource(destination);

            JComboBox<TableColumn> cbSource = new JComboBox<>();
            JComboBox<TableColumn> cbDestination = new JComboBox<>();

            cbSource.addItem(null);
            allColumnsSource.forEach(c -> cbSource.addItem(c));
            allColumnsDestination.forEach(c -> cbDestination.addItem(c));

            tbDataMapping.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbSource));
            tbDataMapping.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbDestination));

            List<DataMapping> dataMapping = new ArrayList<>();

            allColumnsSource.forEach(column -> {
                TableColumn tableColumnDestination = allColumnsDestination.stream()
                        .filter(tableColumn -> tableColumn.getColumnName()
                        .equalsIgnoreCase(column.getColumnName())).findFirst().orElse(null);
                DataMapping dm = new DataMapping();
                dm.setColumnSource(column);
                dm.setColumnDestination(tableColumnDestination == null ? null : tableColumnDestination);
                dataMapping.add(dm);
            });

            allColumnsDestination.stream().filter(column -> dataMapping.stream().noneMatch(dm
                    -> dm.getColumnDestination() != null && column.getColumnName()
                    .equalsIgnoreCase(dm.getColumnDestination().getColumnName()))).forEach(column -> {
                DataMapping dm = new DataMapping();
                dm.setColumnSource(null);
                dm.setColumnDestination(column);
                dataMapping.add(dm);
            });

            tableModel.clear();
            tableModel.addAll(dataMapping);

            txtPrimaryKeySource.setText(allColumnsSource.stream().filter(c -> c.getPrimaryKey()).findFirst().get().getColumnName());
            txtPrimaryKeyDestination.setText(allColumnsDestination.stream().filter(c -> c.getPrimaryKey()).findFirst().get().getColumnName());

        } catch (Exception ex) {
            Logger.getLogger(JDDataSynchronize.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        try {

            DataSynchronize ds = getObj();

            if (action == 1) {

                ds.setAllColumnsSource(allColumnsSource.stream().map(c -> c.copy()).collect(Collectors.toList()));
                ds.setAllColumnsDestination(allColumnsDestination.stream().map(c -> c.copy()).collect(Collectors.toList()));
                DataSynchronizeService.getInstance().insert(ds);
                this.dataSynchronize = ds;

                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);
            } else {

                ds.setId(this.dataSynchronize.getId());
                ds.setLastReading(this.dataSynchronize.getLastReading());
                ds.setAllColumnsSource(allColumnsSource != null ? allColumnsSource.stream().map(c -> c.copy()).collect(Collectors.toList())
                        : this.dataSynchronize.getAllColumnsSource().stream().map(c -> c.copy()).collect(Collectors.toList()));
                ds.setAllColumnsDestination(allColumnsDestination != null ? allColumnsDestination.stream().map(c -> c.copy()).collect(Collectors.toList())
                        : this.dataSynchronize.getAllColumnsDestination().stream().map(c -> c.copy()).collect(Collectors.toList()));
                DataSynchronizeService.getInstance().update(this.dataSynchronize, ds);

                this.dataSynchronize = ds;

                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);

            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btSalvarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed

        int row = tbDataMapping.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma coluna para remover",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.remove(tableModel.getRows().get(row));

    }//GEN-LAST:event_btRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRemover;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox<String> cbDataDestinationTable;
    private javax.swing.JComboBox<String> cbDataSourceTable;
    private javax.swing.JCheckBox chkReplaceIfExist;
    private javax.swing.JCheckBox chkReprocessAll;
    private javax.swing.JCheckBox chkStartImmediately;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbDataMapping;
    private javax.swing.JTextField txtDataDestinationDatabaseName;
    private javax.swing.JTextField txtDataDestinationHost;
    private javax.swing.JPasswordField txtDataDestinationPassword;
    private javax.swing.JTextField txtDataDestinationUser;
    private javax.swing.JTextField txtDataSourceDatabaseName;
    private javax.swing.JTextField txtDataSourceHost;
    private javax.swing.JPasswordField txtDataSourcePassword;
    private javax.swing.JTextField txtDataSourceUser;
    private javax.swing.JSpinner txtInterval;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrimaryKeyDestination;
    private javax.swing.JTextField txtPrimaryKeySource;
    // End of variables declaration//GEN-END:variables

    private void showTables(JTextField txtHost, JTextField txtDatabaseName, JTextField txtUser,
            JTextField txtPassword, JComboBox cbTables) {

        try {

            DataSource dataSource = getDataSource(txtHost, txtDatabaseName, txtUser, txtPassword, cbTables);
            List<String> tables = DataSourceService.getInstance().getTablesByDataSource(dataSource);

            cbTables.removeAllItems();
            tables.forEach(table -> {
                cbTables.addItem(table);
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível buscar as tabelas!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }

    private DataSource getDataSource(JTextField txtHost, JTextField txtDatabaseName, JTextField txtUser,
            JTextField txtPassword, JComboBox cbTables) throws Exception {

        String host = txtHost.getText();
        String database = txtDatabaseName.getText();
        String user = txtUser.getText();
        String password = txtPassword.getText();

        if (Utils.isNullOrEmpty(host, database, user, password)) {
            throw new Exception("Dados obrigatórios não informados");
        }

        DataSource dataSource = new DataSource();
        dataSource.setHost(host);
        dataSource.setDatabase(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setTable(cbTables.getSelectedIndex() < 0 ? null : cbTables.getSelectedItem().toString());

        return dataSource;
    }

    private DataSynchronize getObj() throws Exception {

        String name = txtName.getText().trim();

        boolean replaceIfExist = chkReplaceIfExist.isSelected();
        boolean startImmediately = chkStartImmediately.isSelected();
        boolean processAll = chkReprocessAll.isSelected();
        int interval = (int) txtInterval.getValue();

        if (Utils.isNullOrEmpty(name)) {
            throw new Exception("Nome não informado.");
        }

        DataSource source;
        DataSource destination;

        try {

            source = getDataSource(txtDataSourceHost, txtDataSourceDatabaseName, txtDataSourceUser,
                    txtDataSourcePassword, cbDataSourceTable);

            destination = getDataSource(txtDataDestinationHost, txtDataDestinationDatabaseName,
                    txtDataDestinationUser, txtDataDestinationPassword, cbDataDestinationTable);

        } catch (Exception ex) {
            throw new Exception("Parâmetros de conexão de origem e destino inválidos.");
        }

        List<DataMapping> dataMapping = tableModel.getRows();

        if (dataMapping == null || dataMapping.isEmpty()) {
            throw new Exception("Mapeamento não informado.");
        }

        for (DataMapping dm : dataMapping) {
            if (Utils.isNullOrEmpty(dm.getColumnDestination())) {
                throw new Exception("Mapeamento inválido. É obrigatório informar a coluna de destino.");
            }
            if (Utils.isNullOrEmpty(dm.getColumnSource()) && Utils.isNullOrEmpty(dm.getValueDefault())) {
                throw new Exception("Mapeamento inválido. Quando não informado a coluna de origem, um valor padrão "
                        + "deve ser utilizado.");
            }
            if (dm.getColumnSource() != null && dm.getColumnSource().getColumnType() != dm.getColumnDestination().getColumnType()) {
                throw new Exception("As colunas de origem e de destino devem ser do mesmo tipo.");
            }
        }

        DataSynchronize ds = new DataSynchronize();
        ds.setName(name);
        ds.setSource(source);
        ds.setDestination(destination);
        ds.setDataMapping(dataMapping);
        ds.setReplaceIfExist(replaceIfExist);
        ds.setInterval(interval);
        ds.setStartImmediately(startImmediately);
        ds.setProcessAll(processAll);

        return ds;

    }

    private void setValuesFields() {

        txtName.setText(dataSynchronize.getName());

        txtDataSourceHost.setText(dataSynchronize.getSource().getHost());
        txtDataSourceDatabaseName.setText(dataSynchronize.getSource().getDatabase());
        txtDataSourceUser.setText(dataSynchronize.getSource().getUser());
        txtDataSourcePassword.setText(dataSynchronize.getSource().getPassword());
        cbDataSourceTable.addItem(dataSynchronize.getSource().getTable());
        cbDataSourceTable.setSelectedItem(dataSynchronize.getSource().getTable());

        txtDataDestinationHost.setText(dataSynchronize.getDestination().getHost());
        txtDataDestinationDatabaseName.setText(dataSynchronize.getDestination().getDatabase());
        txtDataDestinationUser.setText(dataSynchronize.getDestination().getUser());
        txtDataDestinationPassword.setText(dataSynchronize.getDestination().getPassword());
        cbDataDestinationTable.addItem(dataSynchronize.getDestination().getTable());
        cbDataDestinationTable.setSelectedItem(dataSynchronize.getDestination().getTable());

        chkReplaceIfExist.setSelected(dataSynchronize.getReplaceIfExist() == null ? false : dataSynchronize.getReplaceIfExist());
        chkStartImmediately.setSelected(dataSynchronize.getStartImmediately() == null ? false : dataSynchronize.getStartImmediately());
        chkReprocessAll.setSelected(dataSynchronize.getProcessAll() == null ? false : dataSynchronize.getProcessAll());
        txtInterval.setValue(dataSynchronize.getInterval());

        JComboBox<TableColumn> cbSource = new JComboBox<>();
        JComboBox<TableColumn> cbDestination = new JComboBox<>();

        cbSource.addItem(null);
        dataSynchronize.getAllColumnsSource().forEach(c -> cbSource.addItem(c));
        dataSynchronize.getAllColumnsDestination().forEach(c -> cbDestination.addItem(c));

        tbDataMapping.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbSource));
        tbDataMapping.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbDestination));

        tableModel.clear();
        tableModel.addAll(dataSynchronize.getDataMapping());

        txtPrimaryKeySource.setText(dataSynchronize.getAllColumnsSource().stream().filter(c -> c.getPrimaryKey()).findFirst().get().getColumnName());
        txtPrimaryKeyDestination.setText(dataSynchronize.getAllColumnsDestination().stream().filter(c -> c.getPrimaryKey()).findFirst().get().getColumnName());

    }

}

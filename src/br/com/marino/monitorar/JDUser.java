package br.com.marino.monitorar;

import br.com.marino.monitorar.models.User;
import br.com.marino.monitorar.services.UserService;
import br.com.marino.monitorar.shared.AddEditListener;
import br.com.marino.monitorar.utils.Utils;
import javax.swing.JOptionPane;

public class JDUser extends javax.swing.JDialog {

    private final int action;
    private User user;
    private final AddEditListener addEditListener;

    public JDUser(java.awt.Frame parent, boolean modal, AddEditListener addEditListener) {
        super(parent, modal);

        initComponents();

        this.addEditListener = addEditListener;
        this.action = 1;

        setTitle("Novo usuário");

    }

    public JDUser(java.awt.Frame parent, boolean modal,
            AddEditListener addEditListener, User user) {

        super(parent, modal);
        initComponents();

        this.user = user;
        this.addEditListener = addEditListener;
        this.action = 2;

        setTitle("Alterando cadastro");

        setValuesFields();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btSave = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alarme");

        jLabel1.setText("Login:");

        btSave.setText("Salvar");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel10.setText("Nome:");

        jLabel2.setText("Senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                            .addComponent(txtLogin)))
                    .addComponent(btSave, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed

        try {

            User u = getObj();

            if (action == 1) {

                UserService.getInstance().insert(u);
                addEditListener.insert(u);

                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);
            } else {

                if (u.getPassword() == null || u.getPassword().isEmpty()) {
                    u.setPassword(user.getPassword());
                }

                UserService.getInstance().update(user, u);
                addEditListener.update(user, u);

                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!",
                        "Êxito", JOptionPane.INFORMATION_MESSAGE);

            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    private User getObj() throws Exception {

        String name = txtName.getText().trim();
        String login = txtLogin.getText().trim();
        String password = txtPassword.getText().trim();

        if (action == 1) {
            if (Utils.isNullOrEmpty(name, login, password)) {
                throw new Exception("Todos os campos são de preenchimento obrigatório!");
            }
        }else{
            if (Utils.isNullOrEmpty(name, login)) {
                throw new Exception("Os campos nome e login são de preenchimento obrigatório!");
            }
        }

        User u = new User();
        u.setName(name);
        u.setLogin(login);
        u.setPassword((password == null || password.isEmpty()) ? null : Utils.toMD5(password));

        return u;

    }

    private void setValuesFields() {

        txtName.setText(user.getName());
        txtLogin.setText(user.getLogin());
        txtPassword.setText("");
        
        if (action == 2){
            txtLogin.setEnabled(false);
        }

    }

}

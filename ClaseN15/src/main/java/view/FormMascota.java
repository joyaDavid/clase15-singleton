package view;

import dao.MascotaDAO;
import dto.MascotaDTO;
import exception.DatoInvalidoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.DatabaseMetaData;
import java.util.List;
import model.Mascota;

public class FormMascota extends JFrame {
   
    private JTextField txtNombre, txtEspecie, txtEdad;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private MascotaDAO dao = new MascotaDAO();
    private int indiceSeleccionado = -1;
    
    public FormMascota() {
        initComponents();
        initTable();
        setTitle("Gestión de Mascotas");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Especie:"));
        txtEspecie = new JTextField();
        panelFormulario.add(txtEspecie);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnActualizar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Especie", "Edad"}, 0);
        tablaMascotas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaMascotas);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnSalir = new JButton("Salir");
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnGuardar.addActionListener(this::guardarMascota);
        btnActualizar.addActionListener(this::actualizarMascota);
        btnEliminar.addActionListener(this::eliminarMascota);
        btnSalir.addActionListener(e -> salir());
        tablaMascotas.getSelectionModel().addListSelectionListener(e -> seleccionarMascota());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        listarMascotas();
    }

    private void guardarMascota(ActionEvent e) {
        try {
            MascotaDTO mascota = obtenerDatosFormulario();
            dao.guardar(mascota);
            JOptionPane.showMessageDialog(this, "✅ Felicidades has guardado a la mascota");
            limpiarCampos();
            listarMascotas();
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void actualizarMascota(ActionEvent e) {
        try {
            if (indiceSeleccionado < 0) {
                throw new Exception("X ERROR celecciona la mascota en la Tabla");
            }
            MascotaDTO mascota = obtenerDatosFormulario();
            dao.actualizar(indiceSeleccionado, mascota);
            JOptionPane.showMessageDialog(this, "✏️Has actualizado a la Mascota");
            limpiarCampos();
            listarMascotas();
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void eliminarMascota(ActionEvent e) {
        try {
            if (indiceSeleccionado < 0) {
                throw new Exception("ERROR Selecciona una mascota para eliminar");
            }
            dao.eliminar(indiceSeleccionado);
            JOptionPane.showMessageDialog(this, "🗑 Has Eliminado la Mascota ");
            limpiarCampos();
            listarMascotas();
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void seleccionarMascota() {
        indiceSeleccionado = tablaMascotas.getSelectedRow();
        if (indiceSeleccionado >= 0) {
            txtNombre.setText((String) modeloTabla.getValueAt(indiceSeleccionado, 0));
            txtEspecie.setText((String) modeloTabla.getValueAt(indiceSeleccionado, 1));
            txtEdad.setText(String.valueOf(modeloTabla.getValueAt(indiceSeleccionado, 2)));
        }
    }

    private void listarMascotas() {
        modeloTabla.setRowCount(0);
        List<MascotaDTO> lista = dao.listar();
        for (MascotaDTO m : lista) {
            modeloTabla.addRow(new Object[]{m.getNombre(), m.getEspecie(), m.getEdad()});
        }
        indiceSeleccionado = -1;
    }

    private MascotaDTO obtenerDatosFormulario() throws DatoInvalidoException {
        String nombre = txtNombre.getText().trim();
        String especie = txtEspecie.getText().trim();
        String edadStr = txtEdad.getText().trim();

        if (nombre.isEmpty() || especie.isEmpty() || edadStr.isEmpty()) {
            throw new DatoInvalidoException("Todos los campos son obligatorios");
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("La edad debe ser un número válido");
        }

        if (edad < 0) {
            throw new DatoInvalidoException("La edad no puede ser negativa");
        }

        return new MascotaDTO(nombre, especie, edad);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEspecie.setText("");
        txtEdad.setText("");
        tablaMascotas.clearSelection();
        indiceSeleccionado = -1;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegister = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel1.setText("Nombre de tu Mascota :");

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel2.setText("Especie de  Mascota :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel3.setText("Edad de la Mascota  :");

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblRegister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Especie", "Edad"
            }
        ));
        tblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegisterMouseClicked1(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegister);

        jLabel4.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("BIENVENIDO");

        jLabel5.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("REGISTRA TU MASCOTA");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnEliminar))
                        .addGap(9, 9, 9)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       if(tblRegister.getSelectedRowCount() != 1){
          return; 
       }
       String nombre = txtNombre.getText();
       String especie = txtEspecie.getText();
       String edad = txtEdad.getText();
       int fila =tblRegister.getSelectedRowCount();
       Mascota m = new Mascota(nombre, especie, edad);
       modeloTabla.setValueAt(m.getNombre(), fila, 0);
       modeloTabla.setValueAt(m.getEspecie(), fila, 1);
       modeloTabla.setValueAt(m.getEdad(), fila, 2);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         String nombre = txtNombre.getText();
       String especie = txtEspecie.getText();
       String edad = txtEdad.getText();
       
       Mascota m = new Mascota (nombre, especie, edad);
       modeloTabla.addRow(m.toArray());
       txtNombre.setText("");
       txtEspecie.setText("");
       txtEdad.setText("");                   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      if(tblRegister.getSelectedRow() != 1){
          return;
      }
      int fila = tblRegister.getSelectedRow();
      modeloTabla.removeRow(fila);
    }//GEN-LAST:event_btnEliminarActionPerformed
  
    private void salir() { //GEN-FIRST:event_btnEliminarActionPerformed
    int respuesta = JOptionPane.showConfirmDialog(
        this,
        "¿Estás seguro que deseas salir?",
        "Confirmar salida",
        JOptionPane.YES_NO_OPTION
    );

    if (respuesta == JOptionPane.YES_OPTION) {
        System.exit(0); 
      }
    }//GEN-LAST:event_btnSalirActionPerformed
    
    private void tblRegisterMouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegisterMouseClicked1
        if (evt.getClickCount() == 2){
            int fila = tblRegister.getSelectedRow();
            String nombre = (String) tablaMascotas.getValueAt(fila, 0);
            String especie = (String) tablaMascotas.getValueAt(fila, 1);
            String edad = (String) tablaMascotas.getValueAt(fila, 2);
            txtNombre.setText(nombre);
            txtEspecie.setText(especie);
            txtEdad.setText(edad);

        }
    }//GEN-LAST:event_tblRegisterMouseClicked1

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new FormMascota().setVisible(true);
        }
      });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblRegister;
    private javax.swing.JButton btnSalir;
    // End of variables declaration//GEN-END:variables

    private void initTable() {
        modeloTabla = new DefaultTableModel(DatabaseMetaData.columnNoNulls, 0){
        @Override        
        public boolean isCellEditable(int row, int column){
            return false;
        }
        };
        tblRegister.setModel(modeloTabla);
    }
}
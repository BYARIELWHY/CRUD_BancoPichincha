package com.banco.crud_bancopichincha;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class VentanaClientes extends JFrame {
    private final ClienteDAO dao = new ClienteDAO();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCedula = new JTextField();
    private final JTextField txtCorreo = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Cedula", "Correo", "Telefono"}, 0
    ) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    public VentanaClientes() {
        setTitle("Banco Pichincha - Gestion de Clientes");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Cedula:"));
        formulario.add(txtCedula);
        formulario.add(new JLabel("Correo:"));
        formulario.add(txtCorreo);
        formulario.add(new JLabel("Telefono:"));
        formulario.add(txtTelefono);
        JButton btnGuardar = new JButton("Registrar");
        JButton btnListar = new JButton("Listar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JPanel botones = new JPanel(new FlowLayout());
        botones.add(btnGuardar);
        botones.add(btnListar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        JPanel superior = new JPanel(new BorderLayout(10, 10));
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botones, BorderLayout.SOUTH);
        superior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        btnGuardar.addActionListener(e -> guardar());
        btnListar.addActionListener(e -> listar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtCedula.setText(modelo.getValueAt(fila, 2).toString());
                    txtCorreo.setText(modelo.getValueAt(fila, 3).toString());
                    txtTelefono.setText(modelo.getValueAt(fila, 4).toString());
                }
            }
        });
        addWindowListener(new WindowAdapterImpl());
        listar();
    }
    private void guardar() {
        if (!validarCampos()) {
            return;
        }
        try {
            Cliente cliente = new Cliente(
                    txtNombre.getText().trim(),
                    txtCedula.getText().trim(),
                    txtCorreo.getText().trim(),
                    txtTelefono.getText().trim()
            );
            dao.guardar(cliente);
            JOptionPane.showMessageDialog(
                    this, "Cliente registrado correctamente."
            );
            limpiar();
            listar();
        } catch (HeadlessException e) {
            mostrarError(e);
        }
    }
    private void listar() {
        try {
            modelo.setRowCount(0);
            List<Cliente> clientes = dao.listar();
            for (Cliente c : clientes) {
                modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getCedula(),
                    c.getCorreo(),
                    c.getTelefono()
                });
            }
        } catch (Exception e) {
            mostrarError(e);
        }
    }
    private void actualizar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(
                    this, "Selecciona un cliente de la tabla."
            );
            return;
        }
        if (!validarCampos()) {
            return;
        }
        try {
            int id = Integer.parseInt(
                    modelo.getValueAt(fila, 0).toString()
            );
            Cliente cliente = dao.buscarPorId(id);
            if (cliente != null) {
                cliente.setNombre(txtNombre.getText().trim());
                cliente.setCedula(txtCedula.getText().trim());
                cliente.setCorreo(txtCorreo.getText().trim());
                cliente.setTelefono(txtTelefono.getText().trim());
                dao.actualizar(cliente);
                JOptionPane.showMessageDialog(
                        this, "Cliente actualizado correctamente."
                );
                limpiar();
                listar();
            }
        } catch (HeadlessException | NumberFormatException e) {
            mostrarError(e);
        }
    }
    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(
                    this, "Selecciona un cliente de la tabla."
            );
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar este cliente?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(
                    modelo.getValueAt(fila, 0).toString()
            );
            dao.eliminar(id);
            JOptionPane.showMessageDialog(
                    this, "Cliente eliminado correctamente."
            );
            limpiar();
            listar();
        } catch (HeadlessException | NumberFormatException e) {
            mostrarError(e);
        }
    }
    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()
                || txtCedula.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Todos los campos son obligatorios."
            );
            return false;
        }
        return true;
    }
    private void limpiar() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        tabla.clearSelection();
    }
    private void mostrarError(Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Ocurrio un error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    private class WindowAdapterImpl extends WindowAdapter {
        public WindowAdapterImpl() {
        }
        @Override
        public void windowClosing(WindowEvent e) {
            HibernateUtil.shutdown();
            dispose();
        }
    }
}

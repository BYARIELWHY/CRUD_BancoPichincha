package com.banco.crud_bancopichincha;
import javax.swing.SwingUtilities;
public class CRUD_BancoPichincha {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaClientes ventana = new VentanaClientes();
            ventana.setVisible(true);
        });
    }
}



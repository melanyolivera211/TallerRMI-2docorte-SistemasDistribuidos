package com.ejemplo.conversion.rmi;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.ejemplo.conversion.rmi.vistas.VentanaPrincipal;

/**
 * Punto de entrada para la aplicación cliente LipeRMI.
 * Configura el aspecto visual (Look and Feel) y despliega la ventana principal centrada.
 */
public class Principal {

    public static void main(String[] args) {
        // Establecer apariencia del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });
    }
}

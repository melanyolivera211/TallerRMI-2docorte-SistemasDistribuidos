package com.ejemplo.conversion.rmi;

import com.ejemplo.conversion.rmi.net.Servidor;

/**
 * Clase principal para ejecutar la aplicación servidora LipeRMI.
 */
public class Principal {

    public static void main(String[] args) {
        Servidor servicio = new Servidor();
        try {
            servicio.iniciar();
            System.out.println("Esperando solicitudes de clientes distribuidos...\n(Presione Ctrl+C para finalizar)");
        } catch (Exception ex) {
            System.err.println("Error crítico al iniciar el servidor LipeRMI: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }
}

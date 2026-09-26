package com.ejemplo.conversion.rmi.net;

import java.io.IOException;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;
import com.ejemplo.conversion.rmi.lib.IRemotaConversion;

/**
 * Gestor del servidor LipeRMI.
 * Configura el puerto de escucha (9007), registra la implementación del servicio
 * y gestiona el ciclo de vida del servidor (iniciar y detener).
 */
public class Servidor {

    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoConversionImplem calculoConversion;

    /**
     * Constructor del servidor. Inicializa el manejador de llamadas,
     * la instancia del servidor LipeRMI y la implementación del cálculo.
     */
    public Servidor() {
        this.invocador = new CallHandler();
        this.servidor = new Server();
        this.calculoConversion = new CalculoConversionImplem();
    }

    /**
     * Inicia el servidor LipeRMI registrando el servicio remoto y
     * enlazando el puerto de red 9007.
     *
     * @throws Exception Si ocurre un error de LipeRMI o de entrada/salida de red.
     */
    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaConversion.class, calculoConversion);
            servidor.bind(puerto, invocador);
            System.out.println("=================================================");
            System.out.println("   SERVIDOR LipeRMI INICIADO EXITOSAMENTE        ");
            System.out.println("   Puerto de escucha: " + puerto);
            System.out.println("   Servicio registrado: " + IRemotaConversion.class.getName());
            System.out.println("=================================================");
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar métodos remotos (LipeRMI)", ex);
        } catch (IOException ex) {
            throw new Exception("Error de I/O al enlazar el puerto " + puerto, ex);
        }
    }

    /**
     * Detiene el servidor y libera los recursos de red.
     */
    public void detener() {
        if (servidor != null) {
            servidor.close();
            System.out.println("[Servidor LipeRMI] Servidor cerrado y puerto liberado.");
        }
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}

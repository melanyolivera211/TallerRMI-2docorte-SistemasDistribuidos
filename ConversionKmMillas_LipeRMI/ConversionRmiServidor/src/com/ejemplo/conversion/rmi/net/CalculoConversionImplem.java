package com.ejemplo.conversion.rmi.net;

import com.ejemplo.conversion.rmi.lib.DatosConversion;
import com.ejemplo.conversion.rmi.lib.IRemotaConversion;

/**
 * Implementación de la interfaz IRemotaConversion para LipeRMI.
 * Proporciona la lógica de conversión de kilómetros a millas en el servidor.
 */
public class CalculoConversionImplem implements IRemotaConversion {

    public CalculoConversionImplem() {
    }

    /**
     * Valida los kilómetros (deben ser >= 0) y realiza el cálculo de millas.
     * Factor de conversión: 1 kilómetro = 0.621371 millas.
     *
     * @param datos Objeto con la información de entrada (kilómetros).
     * @return El mismo objeto DatosConversion con el atributo millas actualizado.
     */
    @Override
    public DatosConversion convertir(DatosConversion datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Error: El objeto DatosConversion no puede ser nulo.");
        }

        float km = datos.getKilometros();
        if (km < 0) {
            throw new IllegalArgumentException("Error: La distancia en kilómetros debe ser mayor o igual a 0 (recibido: " + km + ").");
        }

        float millas = km * 0.621371f;
        datos.setMillas(millas);

        System.out.println("[Servidor LipeRMI] Petición procesada con éxito: " + km + " km -> " + millas + " millas");
        return datos;
    }
}

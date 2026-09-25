package com.ejemplo.conversion.rmi.lib;

/**
 * Interfaz de servicio remoto para LipeRMI.
 * Define el contrato de comunicación para la conversión de kilómetros a millas.
 */
public interface IRemotaConversion {

    /**
     * Realiza la conversión de kilómetros a millas.
     *
     * @param datos Objeto con los kilómetros a convertir.
     * @return Objeto DatosConversion con el valor de millas calculado.
     */
    DatosConversion convertir(DatosConversion datos);
}

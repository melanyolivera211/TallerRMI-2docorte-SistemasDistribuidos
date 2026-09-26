package com.ejemplo.conversion.rmi.lib;

import java.io.Serializable;

/**
 * Objeto de transferencia de datos (DTO) para transportar la información
 * de conversión de unidades (kilómetros y millas) entre cliente y servidor.
 * Implementa Serializable para permitir su transmisión por la red mediante RMI.
 */
public class DatosConversion implements Serializable {

    private static final long serialVersionUID = 1L;

    private float kilometros;
    private float millas;

    /**
     * Constructor por defecto.
     */
    public DatosConversion() {
        this.kilometros = 0.0f;
        this.millas = 0.0f;
    }

    /**
     * Constructor que recibe el valor en kilómetros a convertir.
     *
     * @param kilometros Distancia en kilómetros
     */
    public DatosConversion(float kilometros) {
        this.kilometros = kilometros;
        this.millas = 0.0f;
    }

    public float getKilometros() {
        return kilometros;
    }

    public void setKilometros(float kilometros) {
        this.kilometros = kilometros;
    }

    public float getMillas() {
        return millas;
    }

    public void setMillas(float millas) {
        this.millas = millas;
    }

    @Override
    public String toString() {
        return "DatosConversion{" +
                "kilometros=" + kilometros +
                ", millas=" + millas +
                '}';
    }
}

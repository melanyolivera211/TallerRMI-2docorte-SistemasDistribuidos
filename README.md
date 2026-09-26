# Taller de Sistemas Distribuidos: Conversión de Kilómetros a Millas (LipeRMI)

Aplicación cliente-servidor distribuida en **Java** para la conversión de distancias (kilómetros a millas) utilizando la librería **LipeRMI** (`lipermi-1.0.1.jar`).

---

## 📁 1. Estructura del Proyecto

```
Taller-RMI/
├── ConversionRmiLib/                      # 1. Librería común (DTO e Interfaz)
│   └── src/com/ejemplo/conversion/rmi/lib/
│       ├── DatosConversion.java           # Objeto serializable (km, millas)
│       └── IRemotaConversion.java         # Interfaz remota
│
├── ConversionRmiServidor/                 # 2. Servidor LipeRMI
│   └── src/com/ejemplo/conversion/rmi/
│       ├── Principal.java                 # Punto de entrada del servidor
│       └── net/
│           ├── CalculoConversionImplem.java # Lógica (millas = km * 0.621371)
│           └── Servidor.java              # CallHandler y Server (Puerto 9007)
│
├── ConversionRmiCliente/                  # 3. Cliente LipeRMI (GUI Swing)
│   └── src/com/ejemplo/conversion/rmi/
│       ├── Principal.java                 # Lanzador de la interfaz gráfica
│       └── vistas/
│           └── VentanaPrincipal.java      # JFrame con pestañas CONEXIÓN y CONVERSIÓN
│
├── lib/                                   # Dependencias externas
│   ├── lipermi-1.0.1.jar                  # Librería LipeRMI
│   └── ConversionRmiLib.jar               # JAR de la librería común
│
├── scripts/                               # Scripts por lotes para ejecución rápida
│   ├── compilar_todo.bat                  # Compila los 3 componentes
│   ├── ejecutar_servidor.bat              # Inicia el proceso del Servidor
│   └── ejecutar_cliente.bat               # Inicia la interfaz gráfica del Cliente
│
└── README.md                              # Documentación del proyecto
```

---

## 🚀 2. Compilación y Ejecución

Dentro de la carpeta `scripts/` se encuentran los accesos directos:

1. **Compilar todo**:
   Ejecutar `scripts\compilar_todo.bat` para compilar la librería común, generar `ConversionRmiLib.jar` en `lib/` y compilar el servidor y el cliente.

2. **Iniciar el Servidor**:
   Ejecutar `scripts\ejecutar_servidor.bat` para iniciar el servidor LipeRMI en el puerto `9007`.

3. **Iniciar el Cliente**:
   Ejecutar `scripts\ejecutar_cliente.bat` para abrir la interfaz gráfica Swing:
   * En la pestaña **CONEXION**: Conectar a `localhost` en el puerto `9007`.
   * En la pestaña **CONVERSION**: Ingresar el valor en **KILÓMETROS** (ej. `10`) y hacer clic en **CONVERTIR** para obtener `6.21371 millas`.

---

## 🧪 3. Casos de Prueba

Fórmula: $\text{millas} = \text{kilometros} \times 0.621371$

* **10 km**: $10 \times 0.621371 = 6.21371 \text{ millas}$.
* **0 km**: $0.00000 \text{ millas}$.
* **Valores negativos**: Muestra mensaje de advertencia *"La distancia en kilómetros debe ser mayor o igual a 0."*
* **Texto inválido**: Muestra mensaje de advertencia *"Debe ingresar un número válido."*

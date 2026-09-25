# Taller de Sistemas Distribuidos: Conversión de Kilómetros a Millas

Este repositorio contiene dos implementaciones cliente-servidor completamente independientes para la conversión distribuida de distancias (Kilómetros a Millas):

1. **`ConversionKmMillas_LipeRMI/`**: Implementación basada en la librería de terceros **LipeRMI** (`lipermi-1.0.1.jar`).
2. **`ConversionKmMillas_RMI_Estandar/`**: Implementación nativa de Java SE mediante **Java RMI Estándar** (`UnicastRemoteObject`, `Naming`, `rmiregistry`), sin librerías externas.

Ambas carpetas son **autónomas** y pueden copiarse o moverse por separado a cualquier otro equipo o ruta sin romper dependencias.

---

## 📁 1. Estructura de Carpetas Independientes

```
Taller-RMI/
├── ConversionKmMillas_LipeRMI/            # CARPETA 1: Versión con LipeRMI
│   ├── ConversionRmiLib/                  # Librería común (DTO e interfaz remota)
│   │   └── src/com/ejemplo/conversion/rmi/lib/
│   │       ├── DatosConversion.java       # DTO Serializable (km, millas)
│   │       └── IRemotaConversion.java     # Interfaz remota para LipeRMI
│   ├── ConversionRmiServidor/             # Servidor LipeRMI
│   │   └── src/com/ejemplo/conversion/rmi/
│   │       ├── Principal.java             # Lanzador del servidor LipeRMI
│   │       └── net/
│   │           ├── CalculoConversionImplem.java # Lógica (millas = km * 0.621371)
│   │           └── Servidor.java          # CallHandler y Server (Puerto 9007)
│   ├── ConversionRmiCliente/              # Cliente LipeRMI (GUI Swing)
│   │   └── src/com/ejemplo/conversion/rmi/
│   │       ├── Principal.java             # Lanzador del cliente
│   │       └── vistas/
│   │           └── VentanaPrincipal.java  # GUI con pestañas CONEXIÓN y CONVERSIÓN
│   ├── lib/                               # Librerías requeridas por LipeRMI
│   │   ├── lipermi-1.0.1.jar              # Dependencia LipeRMI (Maven Central)
│   │   └── ConversionRmiLib.jar           # JAR compilado de ConversionRmiLib
│   └── scripts/                           # Scripts por lotes para LipeRMI
│       ├── compilar_todo.bat              # Compila únicamente los 3 proyectos LipeRMI
│       ├── ejecutar_servidor.bat          # Inicia el Servidor LipeRMI
│       └── ejecutar_cliente.bat           # Abre la interfaz gráfica del Cliente
│
├── ConversionKmMillas_RMI_Estandar/       # CARPETA 2: Versión con RMI Estándar (Sin LipeRMI)
│   ├── ConversionRmiEstandarServidor/     # Servidor RMI Nativo
│   │   └── src/com/ejemplo/conversion/rmi/
│   │       ├── lib/
│   │       │   └── DatosConversion.java   # DTO Serializable
│   │       └── estandar/
│   │           ├── IRemotaConversion.java # Interfaz extends java.rmi.Remote
│   │           ├── CalculoConversionImplem.java # Extiende UnicastRemoteObject
│   │           └── ServidorPrincipal.java # LocateRegistry.createRegistry(9007) y rebind
│   ├── ConversionRmiEstandarCliente/      # Cliente RMI Nativo (GUI Swing)
│   │   └── src/com/ejemplo/conversion/rmi/
│   │       ├── lib/
│   │       │   └── DatosConversion.java   # DTO Serializable
│   │       └── estandar/
│   │           ├── IRemotaConversion.java # Interfaz remota para stub
│   │           ├── VentanaPrincipal.java  # GUI con Naming.lookup
│   │           └── Principal.java         # Lanzador del cliente estándar
│   └── scripts/                           # Scripts por lotes para RMI Estándar
│       ├── compilar_todo.bat              # Compila únicamente los proyectos RMI estándar
│       ├── ejecutar_servidor.bat          # Inicia el Servidor RMI Estándar
│       └── ejecutar_cliente.bat           # Abre la interfaz gráfica del Cliente
│
└── README.md                              # Documentación general del taller
```

---

## 🚀 2. Ejecución de la Versión con LipeRMI

Entrar a la carpeta `ConversionKmMillas_LipeRMI\scripts\`:

1. **Compilar (una sola vez)**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_LipeRMI\scripts\compilar_todo.bat
     ```
   * Compila `ConversionRmiLib`, empaqueta `ConversionRmiLib.jar`, y compila `ConversionRmiServidor` y `ConversionRmiCliente`.

2. **Iniciar el Servidor**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_LipeRMI\scripts\ejecutar_servidor.bat
     ```
   * Iniciará el servidor LipeRMI en el puerto `9007`.

3. **Iniciar el Cliente**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_LipeRMI\scripts\ejecutar_cliente.bat
     ```
   * En la pestaña **CONEXION**: Clic en **Conectar** (IP: `localhost`, Puerto: `9007`).
   * En la pestaña **CONVERSION**: Ingresar los kilómetros (ej. `10`) y presionar **CONVERTIR**.

---

## ⚡ 3. Ejecución de la Versión RMI Estándar (Sin LipeRMI)

Entrar a la carpeta `ConversionKmMillas_RMI_Estandar\scripts\`:

1. **Compilar (una sola vez)**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_RMI_Estandar\scripts\compilar_todo.bat
     ```
   * Compila servidor y cliente estándar con Java puro (sin requerir ningún `.jar` externo).

2. **Iniciar el Servidor**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_RMI_Estandar\scripts\ejecutar_servidor.bat
     ```
   * Creará el `rmiregistry` en el puerto `9007` y registrará el servicio `rmi://localhost:9007/Conversion`.

3. **Iniciar el Cliente**:
   * Hacer doble clic en:
     ```bat
     ConversionKmMillas_RMI_Estandar\scripts\ejecutar_cliente.bat
     ```
   * En la pestaña **CONEXION**: Clic en **Conectar**.
   * En la pestaña **CONVERSION**: Ingresar los kilómetros (ej. `10`) y presionar **CONVERTIR**.

---

## 🧪 4. Pruebas y Validación Funcional

Fórmula implementada: $\text{millas} = \text{kilometros} \times 0.621371$

| Caso de Prueba | Entrada (km) | Salida Esperada (millas) | Resultado |
| :--- | :--- | :--- | :--- |
| **Prueba Canónica** | `10` | `6.21371 millas` | Correcto |
| **Cero** | `0` | `0.00000 millas` | Correcto |
| **Maratón (decimal)** | `42.195` | `26.21875 millas` | Correcto |
| **Valor negativo** | `-10` | Error de validación | Muestra diálogo: "La distancia debe ser >= 0" |
| **Texto inválido** | `abc` | Error de formato | Muestra diálogo: "Debe ingresar un número válido" |

---

## 📊 5. Comparativa Técnica: LipeRMI vs. Java RMI Estándar

| Criterio | LipeRMI (`ConversionKmMillas_LipeRMI`) | RMI Estándar (`ConversionKmMillas_RMI_Estandar`) |
| :--- | :--- | :--- |
| **Dependencia Externa** | Requiere librería `lipermi-1.0.1.jar`. | 100% nativo de Java SE (`java.rmi.*`). Sin librerías. |
| **Interfaz Remota** | POJO regular (no requiere herencia). | Obligatorio `extends java.rmi.Remote`. |
| **Excepciones** | Métodos limpios sin `RemoteException`. | Obligatorio declarar `throws RemoteException`. |
| **Clase Servidora** | POJO normal registrado en `CallHandler`. | Hereda de `UnicastRemoteObject`. |
| **Localización** | `client.getGlobal(IRemotaConversion.class)`. | `Naming.lookup("rmi://ip:puerto/Conversion")`. |
| **Registro** | Interno mediante `CallHandler` en un solo puerto TCP. | Servicio de nombres `rmiregistry` (`LocateRegistry`). |
| **Sockets** | Socket TCP persistente multiplexado. | Conexiones RMI clásicas (a menudo con puertos efímeros). |
| **Compatibilidad** | Alta en Android y dispositivos embebidos. | No soportado nativamente en Android moderno. |

---

## 🔬 6. Tecnologías RPC Modernas

* **gRPC**: Desarrollado por Google sobre HTTP/2 y Protocol Buffers (`.proto`). Soporta streaming bidireccional y contratos fuertemente tipados entre múltiples lenguajes (Java, Go, Python, C++, C#).
* **Apache Thrift**: Desarrollado por Apache/Facebook para comunicación binaria RPC eficiente y multiplataforma.
* **JSON-RPC / REST**: Protocolos basados en HTTP y texto JSON legibles por humanos, universales para navegadores y aplicaciones web.

---

## 📸 7. Capturas de Pantalla Sugeridas para el Informe

1. `01_LipeRMI_Estructura.png`: Árbol del proyecto `ConversionKmMillas_LipeRMI`.
2. `02_LipeRMI_Compilacion.png`: Consola de `compilar_todo.bat` en LipeRMI exitosa.
3. `03_LipeRMI_Servidor.png`: Consola de `ejecutar_servidor.bat` en puerto 9007.
4. `04_LipeRMI_Cliente_10km.png`: Ventana gráfica mostrando conversión de 10 km a 6.21371 millas.
5. `05_RMI_Estandar_Estructura.png`: Árbol del proyecto `ConversionKmMillas_RMI_Estandar`.
6. `06_RMI_Estandar_Compilacion.png`: Consola de `compilar_todo.bat` estándar exitosa.
7. `07_RMI_Estandar_Servidor.png`: Consola de `ejecutar_servidor.bat` estándar.
8. `08_RMI_Estandar_Cliente_10km.png`: Ventana gráfica estándar con cálculo exitoso.
9. `09_Validacion_Error.png`: Diálogo de error ante números negativos o texto.

package com.ejemplo.conversion.rmi.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.ejemplo.conversion.rmi.lib.DatosConversion;
import com.ejemplo.conversion.rmi.lib.IRemotaConversion;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

/**
 * Interfaz Gráfica de Usuario (GUI) para el cliente LipeRMI.
 * Proporciona pestañas para la conexión de red y la ejecución del cálculo distribuido.
 */
public class VentanaPrincipal extends JFrame {

    // Componentes de Conexión de Red LipeRMI
    private CallHandler invocadorRemoto;
    private Client cliente;
    private IRemotaConversion conversionRemoto;

    // Componentes visuales Swing
    private JTabbedPane pestanas;
    private JPanel panelConexion;
    private JPanel panelConversion;

    // Controles pestaña CONEXIÓN
    private JLabel lblTituloConexion;
    private JLabel lblIp;
    private JTextField campoIpServidor;
    private JLabel lblPuerto;
    private JTextField campoPuertoServidor;
    private JLabel lblEstadoEtiqueta;
    private JLabel txtEstado;
    private JButton btnIniciar;

    // Controles pestaña CONVERSIÓN
    private JLabel lblTituloConversion;
    private JLabel lblKilometros;
    private JTextField campoKilometros;
    private JButton btnConvertir;
    private JLabel lblMillas;
    private JTextField txtMillas;

    /**
     * Constructor que inicializa los componentes de la interfaz.
     */
    public VentanaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cliente de Conversión (LipeRMI)");
        setSize(500, 360);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        pestanas = new JTabbedPane();
        pestanas.setBounds(15, 15, 455, 290);

        // ==========================================
        // PESTAÑA 1: CONEXIÓN
        // ==========================================
        panelConexion = new JPanel();
        panelConexion.setLayout(null);

        lblTituloConexion = new JLabel("CLIENTE CONVERSIÓN (LipeRMI)", SwingConstants.CENTER);
        lblTituloConexion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTituloConexion.setBounds(20, 15, 410, 25);
        panelConexion.add(lblTituloConexion);

        lblIp = new JLabel("DIRECCIÓN IP:");
        lblIp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblIp.setBounds(40, 60, 130, 25);
        panelConexion.add(lblIp);

        campoIpServidor = new JTextField("localhost");
        campoIpServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        campoIpServidor.setBounds(180, 60, 200, 25);
        panelConexion.add(campoIpServidor);

        lblPuerto = new JLabel("PUERTO DE RED:");
        lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPuerto.setBounds(40, 100, 130, 25);
        panelConexion.add(lblPuerto);

        campoPuertoServidor = new JTextField("9007");
        campoPuertoServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        campoPuertoServidor.setBounds(180, 100, 200, 25);
        panelConexion.add(campoPuertoServidor);

        lblEstadoEtiqueta = new JLabel("ESTADO:");
        lblEstadoEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEstadoEtiqueta.setBounds(40, 140, 130, 25);
        panelConexion.add(lblEstadoEtiqueta);

        txtEstado = new JLabel("Desconectado");
        txtEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtEstado.setForeground(new Color(204, 0, 0));
        txtEstado.setBounds(180, 140, 200, 25);
        panelConexion.add(txtEstado);

        btnIniciar = new JButton("Conectar");
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.setBounds(140, 190, 160, 35);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        panelConexion.add(btnIniciar);

        pestanas.addTab("CONEXION", panelConexion);

        // ==========================================
        // PESTAÑA 2: CONVERSIÓN
        // ==========================================
        panelConversion = new JPanel();
        panelConversion.setLayout(null);

        lblTituloConversion = new JLabel("CONVERSIÓN DE KILÓMETROS A MILLAS", SwingConstants.CENTER);
        lblTituloConversion.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTituloConversion.setBounds(20, 15, 410, 25);
        panelConversion.add(lblTituloConversion);

        lblKilometros = new JLabel("KILÓMETROS:");
        lblKilometros.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblKilometros.setBounds(30, 65, 110, 25);
        panelConversion.add(lblKilometros);

        campoKilometros = new JTextField();
        campoKilometros.setFont(new Font("Tahoma", Font.PLAIN, 13));
        campoKilometros.setBounds(150, 65, 130, 25);
        panelConversion.add(campoKilometros);

        btnConvertir = new JButton("CONVERTIR");
        btnConvertir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConvertir.setForeground(new Color(0, 102, 204));
        btnConvertir.setBounds(300, 65, 120, 26);
        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnConvertirActionPerformed(evt);
            }
        });
        panelConversion.add(btnConvertir);

        lblMillas = new JLabel("MILLAS:");
        lblMillas.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMillas.setBounds(30, 120, 110, 25);
        panelConversion.add(lblMillas);

        txtMillas = new JTextField();
        txtMillas.setEditable(false);
        txtMillas.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtMillas.setForeground(new Color(153, 0, 0));
        txtMillas.setBackground(new Color(245, 245, 245));
        txtMillas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        txtMillas.setBounds(150, 120, 270, 28);
        panelConversion.add(txtMillas);

        pestanas.addTab("CONVERSION", panelConversion);

        add(pestanas);

        // Limpieza de conexión al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (cliente != null) {
                    try {
                        cliente.close();
                    } catch (Exception ex) {
                        // Ignorar errores al cerrar ventana
                    }
                }
            }
        });
    }

    /**
     * Gestiona el evento de conexión y desconexión con el servidor LipeRMI.
     */
    private void btnIniciarActionPerformed(ActionEvent evt) {
        try {
            if (btnIniciar.getText().equalsIgnoreCase("Conectar")) {
                int puerto = Integer.parseInt(campoPuertoServidor.getText().trim());
                String ipServidor = campoIpServidor.getText().trim();

                invocadorRemoto = new CallHandler();
                cliente = new Client(ipServidor, puerto, invocadorRemoto);
                conversionRemoto = (IRemotaConversion) cliente.getGlobal(IRemotaConversion.class);

                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(new Color(204, 0, 0));
                txtEstado.setText("Conectado");
                txtEstado.setForeground(new Color(0, 153, 51));

                JOptionPane.showMessageDialog(this,
                        "Conexión exitosa al servidor LipeRMI en " + ipServidor + ":" + puerto,
                        "Conectado", JOptionPane.INFORMATION_MESSAGE);
            } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
                if (cliente != null) {
                    cliente.close();
                }
                conversionRemoto = null;
                btnIniciar.setText("Conectar");
                btnIniciar.setForeground(new Color(0, 153, 51));
                txtEstado.setText("Desconectado");
                txtEstado.setForeground(new Color(204, 0, 0));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El puerto debe ser un número entero válido.",
                    "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "ERROR AL CONECTAR con el servidor LipeRMI:\n" + ex.getMessage(),
                    "Fallo de Red", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error inesperado al conectar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Gestiona el evento de conversión de kilómetros a millas invocando el servicio remoto.
     * La llamada se realiza en un hilo separado para mantener la UI responsiva.
     */
    private void btnConvertirActionPerformed(ActionEvent evt) {
        if (conversionRemoto == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe conectarse al servidor en la pestaña CONEXION antes de realizar la conversión.",
                    "Sin Conexión", JOptionPane.WARNING_MESSAGE);
            pestanas.setSelectedIndex(0);
            return;
        }

        String textoKm = campoKilometros.getText().trim();
        if (textoKm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor de distancia en kilómetros.",
                    "Campo Vacío", JOptionPane.WARNING_MESSAGE);
            campoKilometros.requestFocus();
            return;
        }

        final float km;
        try {
            km = Float.parseFloat(textoKm);
            if (km < 0) {
                JOptionPane.showMessageDialog(this,
                        "La distancia en kilómetros debe ser mayor o igual a 0.",
                        "Valor Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un número válido (ejemplo: 10 o 15.5).",
                    "Formato Inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ejecutar llamada remota en un hilo secundario para no congelar la GUI
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[Cliente LipeRMI] Enviando petición: " + km + " km");
                    DatosConversion datos = new DatosConversion(km);
                    final DatosConversion resultado = conversionRemoto.convertir(datos);
                    System.out.println("[Cliente LipeRMI] Respuesta recibida: " + resultado.getMillas() + " millas");

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            txtMillas.setText(String.format("%.5f millas", resultado.getMillas()));
                        }
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(VentanaPrincipal.this,
                                    "ERROR en la invocación remota: " + ex.getMessage(),
                                    "Error con el Cliente", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                    ex.printStackTrace();
                }
            }
        });
        hilo.start();
    }

    public JLabel getTxtEstado() {
        return txtEstado;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }
}

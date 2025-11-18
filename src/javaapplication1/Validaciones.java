package javaapplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Validaciones {

    private static JLabel qrLabel;
    private static JLabel infoLabel;

    public static JPanel crearPanelValidaciones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ========== TOP BAR CON CAMPANITA Y SALIR ==========
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.setBackground(new Color(245, 245, 245));
        topBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Panel derecho con campanita y salir
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightTopPanel.setBackground(new Color(245, 245, 245));

        // Icono campanita
        ImageIcon campanitaIcon = new ImageIcon(Validaciones.class.getResource("/imagenes/campanita.png"));
        JLabel campanitaLabel = new JLabel(campanitaIcon);

        // Botón Salir
        JButton salirButton = new JButton("Salir");
        salirButton.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 14));
        salirButton.setForeground(Color.WHITE);
        salirButton.setBackground(new Color(80, 156, 219));
        salirButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        salirButton.setFocusPainted(false);
        salirButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        rightTopPanel.add(campanitaLabel);
        rightTopPanel.add(salirButton);

        topBarPanel.add(rightTopPanel, BorderLayout.EAST);

        // ========== CONTENIDO PRINCIPAL DE VALIDACIONES ==========
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));

        // Header con título centrado
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JLabel titleLabel = new JLabel("Validaciones");
        titleLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 32));
        titleLabel.setForeground(new Color(51, 51, 51));

        headerPanel.add(titleLabel);

        // Panel central con todos los componentes
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 245, 245));

        // Campo "Ingresa Clave" - CENTRADO
        JPanel clavePanel = new JPanel();
        clavePanel.setBackground(new Color(245, 245, 245));
        clavePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel claveLabel = new JLabel("Ingresa Clave (4 dígitos)");
        claveLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 18));
        claveLabel.setForeground(new Color(51, 51, 51));

        JTextField claveField = new JTextField(20);
        claveField.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 16));
        claveField.setPreferredSize(new Dimension(250, 40));
        claveField.setMaximumSize(new Dimension(250, 40));

        clavePanel.add(claveLabel);
        clavePanel.add(Box.createHorizontalStrut(15));
        clavePanel.add(claveField);

        // Botón "Generar QR" - CENTRADO
        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonPanel.setBackground(new Color(245, 245, 245));

        JButton generarQRBtn = new JButton("Generar QR");
        generarQRBtn.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 18));
        generarQRBtn.setForeground(Color.WHITE);
        generarQRBtn.setBackground(new Color(80, 156, 219));
        generarQRBtn.setPreferredSize(new Dimension(180, 50));
        generarQRBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        generarQRBtn.setFocusPainted(false);
        generarQRBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Panel para información del evento
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        
        infoLabel = new JLabel("Ingresa una clave válida de 4 dígitos", SwingConstants.CENTER);
        infoLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        infoLabel.setForeground(new Color(150, 150, 150));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoPanel.add(infoLabel);

        // Panel para el QR
        JPanel qrPanel = new JPanel(new BorderLayout());
        qrPanel.setBackground(Color.WHITE);
        qrPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        qrPanel.setPreferredSize(new Dimension(300, 300));
        qrPanel.setMaximumSize(new Dimension(300, 300));

        // Label para mostrar el QR
        qrLabel = new JLabel("QR aparecerá aquí", SwingConstants.CENTER);
        qrLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        qrLabel.setForeground(new Color(150, 150, 150));
        qrLabel.setVerticalAlignment(SwingConstants.CENTER);
        qrPanel.add(qrLabel, BorderLayout.CENTER);

        // ✅ ACTION LISTENER MEJORADO CON VENTANA DE VALIDACIÓN INTERNA
        generarQRBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clave = claveField.getText().trim();
                
                if (clave.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, 
                        "Por favor ingresa una clave de 4 dígitos", 
                        "Clave vacía", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (!clave.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(panel, 
                        "La clave debe ser de exactamente 4 dígitos numéricos", 
                        "Clave inválida", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    // Obtener información del evento
                    String informacionEvento = obtenerInformacionEvento(clave);
                    
                    if (informacionEvento.startsWith("Clave no encontrada")) {
                        JOptionPane.showMessageDialog(panel, 
                            "Clave no válida. Usa una clave de evento existente.", 
                            "Clave inválida", 
                            JOptionPane.WARNING_MESSAGE);
                        infoLabel.setText("Clave no válida - Usa claves del listado de eventos");
                        infoLabel.setForeground(Color.RED);
                        qrLabel.setIcon(null);
                        qrLabel.setText("Clave inválida");
                        return;
                    }
                    
                    // Actualizar información del evento
                    infoLabel.setText(informacionEvento);
                    infoLabel.setForeground(new Color(0, 100, 0));
                    
                    // ✅ GENERAR DATOS PARA LA VALIDACIÓN
                    String datosValidacion = generarDatosValidacion(clave);
                    
                    // Generar el código QR con los datos de validación
                    BufferedImage qrImage = generarQRCode(datosValidacion, 250, 250);
                    
                    if (qrImage != null) {
                        ImageIcon qrIcon = new ImageIcon(qrImage);
                        qrLabel.setIcon(qrIcon);
                        qrLabel.setText("");
                        
                        // ✅ MOSTRAR BOTÓN PARA VER VALIDACIÓN
                        JButton verValidacionBtn = new JButton("Ver Ventana de Validación");
                        verValidacionBtn.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 14));
                        verValidacionBtn.setBackground(new Color(40, 167, 69));
                        verValidacionBtn.setForeground(Color.WHITE);
                        verValidacionBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                mostrarVentanaValidacion(clave);
                            }
                        });
                        
                        JOptionPane.showOptionDialog(panel, 
                            "QR generado exitosamente!\n\n" + informacionEvento + 
                            "\n\nPuedes ver la ventana de validación:", 
                            "QR Generado", 
                            JOptionPane.DEFAULT_OPTION, 
                            JOptionPane.INFORMATION_MESSAGE, 
                            null, 
                            new Object[]{verValidacionBtn}, 
                            verValidacionBtn);
                    } else {
                        qrLabel.setIcon(null);
                        qrLabel.setText("Error al generar QR");
                    }
                } catch (Exception ex) {
                    qrLabel.setIcon(null);
                    qrLabel.setText("Error al generar QR");
                    JOptionPane.showMessageDialog(panel, 
                        "Error al generar QR: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonPanel.add(generarQRBtn);

        // Centrar el panel del QR
        JPanel qrContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        qrContainerPanel.setBackground(new Color(245, 245, 245));
        qrContainerPanel.add(qrPanel);

        // Agregar componentes al panel central con espaciado
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(clavePanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(botonPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(qrContainerPanel);
        centerPanel.add(Box.createVerticalGlue());

        // Ensamblar el contentPanel
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);

        // ========== ENSAMBLAJE FINAL ==========
        panel.add(topBarPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    // ✅ MÉTODO PARA OBTENER INFORMACIÓN DEL EVENTO
    private static String obtenerInformacionEvento(String clave) {
        switch (clave) {
            case "4852":
                return "Evento: Semana de la ingeniería | Horas: 10 | Clave: 4852";
            case "7193":
                return "Evento: Ulibro | Horas: 15 | Clave: 7193";
            case "2367":
                return "Evento: Conservatorio de fsdgsff | Horas: 4 | Clave: 2367";
            case "9041":
                return "Evento: Taller de lectura | Horas: 2 | Clave: 9041";
            default:
                return "Clave no encontrada";
        }
    }

    // ✅ MÉTODO PARA GENERAR DATOS DE VALIDACIÓN
    private static String generarDatosValidacion(String clave) {
        return "CRONOS-UNAB-VALIDACION:" + clave + ":" + System.currentTimeMillis();
    }

    // ✅ MÉTODO PARA MOSTRAR VENTANA DE VALIDACIÓN
    private static void mostrarVentanaValidacion(String clave) {
        JDialog ventanaValidacion = new JDialog();
        ventanaValidacion.setTitle("Validación de Asistencia - CRONOS UNAB");
        ventanaValidacion.setSize(500, 600);
        ventanaValidacion.setLocationRelativeTo(null);
        ventanaValidacion.setModal(true);
        ventanaValidacion.setResizable(false);

        // Panel principal con gradiente
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(102, 126, 234), 0, getHeight(), new Color(118, 75, 162));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Panel de contenido (tarjeta blanca)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.setMaximumSize(new Dimension(400, 500));

        // Logo (simulado)
        JLabel logoLabel = new JLabel(" CRONOS-UNAB", SwingConstants.CENTER);
        logoLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 24));
        logoLabel.setForeground(new Color(51, 51, 51));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estado de validación
        JLabel statusLabel = new JLabel(" ASISTENCIA VALIDADA", SwingConstants.CENTER);
        statusLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 20));
        statusLabel.setForeground(new Color(40, 167, 69));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Información del evento
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(248, 249, 250));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Obtener detalles específicos del evento
        String[] detalles = obtenerDetallesEvento(clave);
        
        JLabel eventTitle = new JLabel(detalles[0], SwingConstants.CENTER);
        eventTitle.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 18));
        eventTitle.setForeground(new Color(51, 51, 51));
        eventTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel studentLabel = new JLabel("Estudiante: Edwin Gomez", SwingConstants.CENTER);
        studentLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        studentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel("ID: U00124073", SwingConstants.CENTER);
        idLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dateLabel = new JLabel("Fecha: " + detalles[2], SwingConstants.CENTER);
        dateLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel claveLabel = new JLabel("Clave: " + clave, SwingConstants.CENTER);
        claveLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        claveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(eventTitle);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(studentLabel);
        infoPanel.add(idLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(claveLabel);

        // Badge de horas
        JLabel hoursBadge = new JLabel("TIENES " + detalles[1] + " HORAS LIBRES CONSEGUIDAS", SwingConstants.CENTER);
        hoursBadge.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 16));
        hoursBadge.setForeground(Color.WHITE);
        hoursBadge.setBackground(new Color(23, 162, 184));
        hoursBadge.setOpaque(true);
        hoursBadge.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        hoursBadge.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mensaje final
        JLabel messageLabel = new JLabel("Esta validación ha sido registrada en el sistema CRONOS-UNAB", SwingConstants.CENTER);
        messageLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 12));
        messageLabel.setForeground(new Color(108, 117, 125));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón cerrar
        JButton closeButton = new JButton("Cerrar Validación");
        closeButton.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 14));
        closeButton.setBackground(new Color(80, 156, 219));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaValidacion.dispose();
            }
        });

        // Ensamblar contenido
        contentPanel.add(logoLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(statusLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(infoPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(hoursBadge);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(messageLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(closeButton);

        // Centrar el contenido
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(contentPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        ventanaValidacion.add(mainPanel);
        ventanaValidacion.setVisible(true);
    }

    // ✅ MÉTODO PARA OBTENER DETALLES ESPECÍFICOS DEL EVENTO
    private static String[] obtenerDetallesEvento(String clave) {
        String[] detalles = new String[3]; // [0] nombre, [1] horas, [2] fecha
        
        switch (clave) {
            case "4852":
                detalles[0] = "Semana de la Ingeniería";
                detalles[1] = "10";
                detalles[2] = "15 Noviembre 2024";
                break;
            case "7193":
                detalles[0] = "Feria Ulibro 2024";
                detalles[1] = "15";
                detalles[2] = "20 Octubre 2024";
                break;
            case "2367":
                detalles[0] = "Conservatorio de Música";
                detalles[1] = "4";
                detalles[2] = "05 Septiembre 2024";
                break;
            case "9041":
                detalles[0] = "Taller de Lectura";
                detalles[1] = "2";
                detalles[2] = "12 Agosto 2024";
                break;
            default:
                detalles[0] = "Evento No Especificado";
                detalles[1] = "0";
                detalles[2] = "Fecha No Especificada";
        }
        return detalles;
    }

    // ✅ MÉTODO PARA GENERAR CÓDIGO QR
    private static BufferedImage generarQRCode(String texto, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, width, height);
            
            BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }
            
            return qrImage;
            
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
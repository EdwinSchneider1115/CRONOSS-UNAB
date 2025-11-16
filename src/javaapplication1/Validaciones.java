package javaapplication1;

import javax.swing.*;
import java.awt.*;

public class Validaciones {
    
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
        
        JLabel claveLabel = new JLabel("Ingresa Clave");
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
        
        botonPanel.add(generarQRBtn);

        // Panel para el QR (más grande y centrado)
        JPanel qrPanel = new JPanel(new BorderLayout());
        qrPanel.setBackground(Color.WHITE);
        qrPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        qrPanel.setPreferredSize(new Dimension(300, 300));
        qrPanel.setMaximumSize(new Dimension(300, 300));
        
        JLabel qrLabel = new JLabel("QR Code Generator", SwingConstants.CENTER);
        qrLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 20));
        qrLabel.setForeground(new Color(51, 51, 51));
        qrPanel.add(qrLabel, BorderLayout.CENTER);

        // Centrar el panel del QR
        JPanel qrContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        qrContainerPanel.setBackground(new Color(245, 245, 245));
        qrContainerPanel.add(qrPanel);

        // Agregar componentes al panel central con espaciado
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(clavePanel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(botonPanel);
        centerPanel.add(Box.createVerticalStrut(40));
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
}
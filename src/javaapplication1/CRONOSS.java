package javaapplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRONOSS {
    public static void main(String[] args) {
        // Ejecutar en el hilo de EDT de Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        // Crear el frame principal
        JFrame frame = new JFrame("CRONOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        // Panel principal con gradiente o color sólido
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título centrado y estilizado
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        
        JLabel titleLabel = new JLabel("CRONOS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 100, 200));
        topPanel.add(titleLabel);
        
        // Panel central con mejor estética
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        
        // Mensaje de bienvenida
        JLabel welcomeLabel = new JLabel("Bienvenido, ingresa tu usuario");
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(60, 60, 60));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Descripción
        JLabel descLabel = new JLabel("Maneja tus horas libres con solo un click");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(new Color(120, 120, 120));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel para campos de entrada centrados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Campo ID
        JLabel idLabel = new JLabel("Ingresa tu ID");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setForeground(new Color(80, 80, 80));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextField idField = new JTextField();
        idField.setMaximumSize(new Dimension(280, 40));
        idField.setPreferredSize(new Dimension(280, 40));
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Campo Contraseña
        JLabel passLabel = new JLabel("Ingresa Contraseña");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setForeground(new Color(80, 80, 80));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(280, 40));
        passField.setPreferredSize(new Dimension(280, 40));
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Botón Login mejorado
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 45));
        loginButton.setMaximumSize(new Dimension(150, 45));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efectos hover para el botón
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 100, 190));
                loginButton.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 120, 215));
                loginButton.setForeground(Color.WHITE);
            }
        });
        
        // Mensaje inferior
        JLabel bottomLabel = new JLabel("¿Obtienes tu contraseña? Dispite a Multimedia");
        bottomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        bottomLabel.setForeground(new Color(150, 150, 150));
        bottomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Agregar componentes al panel de entrada
        inputPanel.add(idLabel);
        inputPanel.add(Box.createVerticalStrut(8));
        inputPanel.add(idField);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(passLabel);
        inputPanel.add(Box.createVerticalStrut(8));
        inputPanel.add(passField);
        
        // Agregar componentes al panel central en orden
        centerPanel.add(welcomeLabel);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(descLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(inputPanel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(bottomLabel);
        
        // Action listener para el botón
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passField.getPassword());
                
                if ("U00124073".equals(id) && "12345".equals(password)) {
                    JOptionPane.showMessageDialog(frame, 
                        "Login exitoso!\nBienvenido al sistema CRONOS", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "ID o contraseña incorrectos", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Permitir login con Enter
        ActionListener enterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        };
        
        idField.addActionListener(enterListener);
        passField.addActionListener(enterListener);
        
        // Ensamblar frame
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
        
        // Enfocar el campo ID al iniciar
        idField.requestFocusInWindow();
    }
}
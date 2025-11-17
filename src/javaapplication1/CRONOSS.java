package javaapplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CRONOSS {
    // Nombre del archivo para guardar los usuarios
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    // Mapa para almacenar los usuarios (ID, Contraseña)
    private static Map<String, String> usuarios = new HashMap<>();

    public static void main(String[] args) {
        // Cargar usuarios existentes al iniciar la aplicación
        cargarUsuarios();

        // Ejecutar en el hilo de EDT de Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    // --- Métodos de Persistencia ---

    private static void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    usuarios.put(partes[0], partes[1]);
                }
            }
            if (usuarios.isEmpty()) {
                usuarios.put("U00124073", "12345");
                guardarUsuario("U00124073", "12345");
            }
        } catch (FileNotFoundException e) {
            usuarios.put("U00124073", "12345");
            guardarUsuario("U00124073", "12345");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void guardarUsuario(String id, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            bw.write(id + ":" + password);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el nuevo usuario: " + e.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Lógica de la Interfaz Gráfica (Altura de Frame Corregida) ---

    private static void createAndShowGUI() {
        // Crear el frame principal
        JFrame frame = new JFrame("CRONOS - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600); // 👈 ¡MODIFICACIÓN CLAVE: Aumentamos la altura!
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

        // Mensaje/Enlace para crear cuenta
        JLabel createAccountLabel = new JLabel("<html><u>Crear nueva cuenta</u></html>");
        createAccountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        createAccountLabel.setForeground(new Color(0, 120, 215));
        createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Agregar componentes al panel de entrada (inputPanel)
        inputPanel.add(idLabel);
        inputPanel.add(Box.createVerticalStrut(8));
        inputPanel.add(idField);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(passLabel);
        inputPanel.add(Box.createVerticalStrut(8));
        inputPanel.add(passField);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(createAccountLabel);
        inputPanel.add(Box.createVerticalStrut(20));

        // Agregar componentes al panel central (centerPanel) en orden
        centerPanel.add(welcomeLabel);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(descLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(inputPanel); 
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(40));

        // 1. Action listener para el botón de Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passField.getPassword());

                if (usuarios.containsKey(id) && usuarios.get(id).equals(password)) {
                    JOptionPane.showMessageDialog(frame,
                            "¡Login exitoso! Bienvenido, " + id,
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    idField.setText("");
                    passField.setText("");
                    idField.requestFocusInWindow();
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "ID o contraseña incorrectos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 2. Action Listener para el enlace "Crear nueva cuenta"
        createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearNuevaCuenta(frame);
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

    /**
     * Muestra un diálogo simple para que el usuario cree una nueva cuenta.
     */
    private static void crearNuevaCuenta(JFrame parentFrame) {
        JTextField newIdField = new JTextField(10);
        JPasswordField newPassField = new JPasswordField(10);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("Nuevo ID (Usuario):"));
        myPanel.add(newIdField);
        myPanel.add(Box.createVerticalStrut(15));
        myPanel.add(new JLabel("Nueva Contraseña:"));
        myPanel.add(newPassField);

        int result = JOptionPane.showConfirmDialog(parentFrame, myPanel,
                "Crear Nueva Cuenta", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String newId = newIdField.getText().trim();
            String newPassword = new String(newPassField.getPassword());

            if (newId.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "El ID y la contraseña no pueden estar vacíos.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
            } else if (usuarios.containsKey(newId)) {
                JOptionPane.showMessageDialog(parentFrame, "El ID '" + newId + "' ya existe. Por favor, elige otro.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarios.put(newId, newPassword);
                guardarUsuario(newId, newPassword);
                JOptionPane.showMessageDialog(parentFrame, "¡Cuenta creada exitosamente! Ya puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
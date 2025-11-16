package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class PaginaPrincipalReal {

    private static Font kumbhSansPlain;
    private static Font kumbhSansBold;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // 1. PRIMERO cargar la fuente
        cargarFuenteKumbhSans();

        // Crear el frame principal
        JFrame frame = new JFrame("Cronos-UNAB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 850);
        frame.setLocationRelativeTo(null);

        // Variables para el funcionamiento del menú
        Color colorAzulBase = new Color(80, 156, 219);
        Color colorAzulOscuro = new Color(40, 120, 190);
        Color colorFondo = new Color(245, 245, 245);
        Color colorTexto = new Color(51, 51, 51);
        Color colorSubtitulo = new Color(127, 140, 141);
        Color colorGrisCombo = new Color(240, 240, 240);

        // CORRECCIÓN: Usar array final para botonActivo
        final JButton[] botonActivo = {null};

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(colorFondo);

        // ========== PANEL IZQUIERDO - MENÚ ==========
        JPanel leftMenuPanel = new JPanel();
        leftMenuPanel.setBackground(colorAzulBase);
        leftMenuPanel.setPreferredSize(new Dimension(320, 850));
        leftMenuPanel.setLayout(new BorderLayout());

        // Panel para los botones con BoxLayout
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(colorAzulBase);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        // Logo y título con imagen (MANTIENE SU CENTRADO)
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBackground(colorAzulBase);
        logoPanel.setPreferredSize(new Dimension(320, 175));
        logoPanel.setMaximumSize(new Dimension(320, 175));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 75, 20, 20));

        // Logo UNAB
        ImageIcon logoUnab = new ImageIcon(PaginaPrincipalReal.class.getResource("/imagenes/logoUnab.png"));
        JLabel imagenLogo = new JLabel(logoUnab);
        imagenLogo.setBorder(BorderFactory.createEmptyBorder(20, -30, 30, 25));

        JLabel cronosTitle = new JLabel("CRONOSS-UNAB", SwingConstants.CENTER);
        cronosTitle.setFont(getKumbhSansFont(Font.BOLD, 20));
        cronosTitle.setForeground(Color.WHITE);
        cronosTitle.setBorder(BorderFactory.createEmptyBorder(0, -45, 0, 0));

        logoPanel.add(imagenLogo, BorderLayout.CENTER);
        logoPanel.add(cronosTitle, BorderLayout.SOUTH);

        // Crear botones del menú con iconos
        JButton horasLibresBtn = crearBotonMenuConIcono("• Horas Libres", "/imagenes/iconoCasita.png", colorAzulBase);
        JButton eventosAsistidosBtn = crearBotonMenuConIcono("• Eventos Asistidos", "/imagenes/iconoCasita.png", colorAzulBase);
        JButton eventosEsteMesBtn = crearBotonMenuConIcono("• Eventos este mes", "/imagenes/iconoCasita.png", colorAzulBase);
        JButton calendarioBtn = crearBotonMenuConIcono("• Calendario de eventos", "/imagenes/iconoCasita.png", colorAzulBase);
        JButton validacionesBtn = crearBotonMenuConIcono("• Validaciones", "/imagenes/iconoCasita.png", colorAzulBase);

        // ========== PANEL DE CONTENIDO PRINCIPAL ==========
        JPanel contentPanel = new JPanel(new CardLayout()); // CAMBIA A CardLayout
        contentPanel.setBackground(colorFondo);

        // CORRECCIÓN: Usar el método correcto crearPanelEventosAsistidos
        JPanel eventosPanel = crearPanelEventosAsistidos(colorFondo, colorTexto, colorSubtitulo, colorGrisCombo);

        // Crear el panel de validaciones
        JPanel validacionesPanel = Validaciones.crearPanelValidaciones();

        // Agregar ambos paneles al contentPanel
        contentPanel.add(eventosPanel, "eventos");
        contentPanel.add(validacionesPanel, "validaciones");

        // Action Listeners para los botones
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botonClickeado = (JButton) e.getSource();

                // Resetear todos los botones
                for (Component comp : botonesPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton btn = (JButton) comp;
                        btn.setBackground(colorAzulBase);
                        btn.setFont(getKumbhSansFont(Font.PLAIN, 16));
                    }
                }

                // Resaltar botón clickeado
                botonClickeado.setBackground(colorAzulOscuro);
                botonClickeado.setFont(getKumbhSansFont(Font.BOLD, 16));
                // CORRECCIÓN: Usar el array final
                botonActivo[0] = botonClickeado;

                // CAMBIAR CONTENIDO SEGÚN EL BOTÓN PRESIONADO
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();

                if (botonClickeado.getText().equals("• Validaciones")) {
                    cardLayout.show(contentPanel, "validaciones");
                } else {
                    cardLayout.show(contentPanel, "eventos");
                }
            }
        };

        horasLibresBtn.addActionListener(menuListener);
        eventosAsistidosBtn.addActionListener(menuListener);
        eventosEsteMesBtn.addActionListener(menuListener);
        calendarioBtn.addActionListener(menuListener);
        validacionesBtn.addActionListener(menuListener);

        // Agregar botones al panel de botones
        botonesPanel.add(Box.createVerticalStrut(10));
        botonesPanel.add(horasLibresBtn);
        botonesPanel.add(Box.createVerticalStrut(8));
        botonesPanel.add(eventosAsistidosBtn);
        botonesPanel.add(Box.createVerticalStrut(8));
        botonesPanel.add(eventosEsteMesBtn);
        botonesPanel.add(Box.createVerticalStrut(8));
        botonesPanel.add(calendarioBtn);
        botonesPanel.add(Box.createVerticalStrut(8));
        botonesPanel.add(validacionesBtn);
        botonesPanel.add(Box.createVerticalGlue());

        // Panel de cuenta con imagen
        JPanel cuentaPanel = new JPanel();
        cuentaPanel.setLayout(new BorderLayout());
        cuentaPanel.setBackground(colorAzulBase);
        cuentaPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 30, 25));
        cuentaPanel.setMaximumSize(new Dimension(320, 100));

        // Imagen de cuenta
        ImageIcon imgCuenta = new ImageIcon(PaginaPrincipalReal.class.getResource("/imagenes/imgCuenta.png"));
        JLabel imagenLogin = new JLabel(imgCuenta);
        imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(colorAzulBase);

        JLabel cuentaLabel = new JLabel("Cuenta");
        cuentaLabel.setFont(getKumbhSansFont(Font.BOLD, 16));
        cuentaLabel.setForeground(Color.WHITE);

        JLabel emailLabel = new JLabel("egomez161@unab.edu.co");
        emailLabel.setFont(getKumbhSansFont(Font.PLAIN, 14));
        emailLabel.setForeground(Color.WHITE);

        infoPanel.add(cuentaLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(emailLabel);

        cuentaPanel.add(imagenLogin, BorderLayout.WEST);
        cuentaPanel.add(infoPanel, BorderLayout.CENTER);

        // Ensamblar el leftMenuPanel con BorderLayout
        leftMenuPanel.add(logoPanel, BorderLayout.NORTH);
        leftMenuPanel.add(botonesPanel, BorderLayout.CENTER);
        leftMenuPanel.add(cuentaPanel, BorderLayout.SOUTH);

        // Resaltar "Eventos Asistidos" por defecto
        eventosAsistidosBtn.setBackground(colorAzulOscuro);
        eventosAsistidosBtn.setFont(getKumbhSansFont(Font.BOLD, 16));
        botonActivo[0] = eventosAsistidosBtn;

        // Ensamblar mainPanel
        mainPanel.add(leftMenuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // MÉTODO PARA CREAR EL PANEL DE EVENTOS ASISTIDOS
    private static JPanel crearPanelEventosAsistidos(Color colorFondo, Color colorTexto, Color colorSubtitulo, Color colorGrisCombo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header con campanita y botón salir
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.setBackground(colorFondo);
        topBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Panel derecho con campanita y salir
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightTopPanel.setBackground(colorFondo);

        // Icono campanita
        ImageIcon campanitaIcon = new ImageIcon(PaginaPrincipalReal.class.getResource("/imagenes/campanita.png"));
        JLabel campanitaLabel = new JLabel(campanitaIcon);

        // Botón Salir
        JButton salirButton = new JButton("Salir");
        salirButton.setFont(getKumbhSansFont(Font.BOLD, 14));
        salirButton.setForeground(Color.WHITE);
        salirButton.setBackground(new Color(80, 156, 219));
        salirButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        salirButton.setFocusPainted(false);
        salirButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightTopPanel.add(campanitaLabel);
        rightTopPanel.add(salirButton);

        topBarPanel.add(rightTopPanel, BorderLayout.EAST);

        // Header principal
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(colorFondo);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel subtitleLabel = new JLabel("Maneja de forma eficaz y rapida tu control de horas libres en la UNAB");
        subtitleLabel.setFont(getKumbhSansFont(Font.PLAIN, 14));
        subtitleLabel.setForeground(colorSubtitulo);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));

        JPanel titleComboPanel = new JPanel(new BorderLayout());
        titleComboPanel.setBackground(colorFondo);

        JLabel titleLabel = new JLabel("Eventos Asistidos En el Semestre");
        titleLabel.setFont(getKumbhSansFont(Font.BOLD, 28));
        titleLabel.setForeground(colorTexto);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 0));

        JPanel semestrePanel = new JPanel();
        semestrePanel.setLayout(new BoxLayout(semestrePanel, BoxLayout.Y_AXIS));
        semestrePanel.setBackground(colorFondo);
        semestrePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel semestreLabel = new JLabel("Seleccionar Semestre");
        semestreLabel.setFont(getKumbhSansFont(Font.BOLD, 14));
        semestreLabel.setForeground(colorSubtitulo);
        semestreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JComboBox<String> semestreCombo = new JComboBox<>(new String[]{
            "Seleccione Semestre", "2024-1", "2024-2", "2023-2"
        });
        semestreCombo.setMaximumSize(new Dimension(250, 40));
        semestreCombo.setFont(getKumbhSansFont(Font.PLAIN, 14));
        semestreCombo.setBackground(colorGrisCombo);
        semestreCombo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        semestreCombo.setAlignmentX(Component.RIGHT_ALIGNMENT);

        semestrePanel.add(semestreLabel);
        semestrePanel.add(Box.createVerticalStrut(5));
        semestrePanel.add(semestreCombo);

        titleComboPanel.add(titleLabel, BorderLayout.CENTER);
        titleComboPanel.add(semestrePanel, BorderLayout.EAST);

        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(titleComboPanel);

        // Panel de tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        // Crear tabla exactamente como en la imagen
        String[] columnNames = {"Evento Asistido", "Horas libres"};
        Object[][] data = {
            {"• Semana de la ingeniería", "10 horas libres"},
            {"• Ulibro", "15 horas libres"},
            {"• Conservatorio de fsdgsff", "4 horas libres"},
            {"• Taller de lectura", "2 horas libres"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        JTable eventsTable = new JTable(model);
        eventsTable.setRowHeight(50);
        eventsTable.setFont(getKumbhSansFont(Font.PLAIN, 14));
        eventsTable.setForeground(colorTexto);
        eventsTable.setBackground(Color.WHITE);
        eventsTable.setShowGrid(true);
        eventsTable.setGridColor(new Color(230, 230, 230));

        eventsTable.getTableHeader().setFont(getKumbhSansFont(Font.BOLD, 14));
        eventsTable.getTableHeader().setBackground(new Color(250, 250, 250));
        eventsTable.getTableHeader().setForeground(colorTexto);
        eventsTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220, 220, 220)));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        eventsTable.setDefaultRenderer(String.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(eventsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Ensamblar contentPanel
        panel.add(topBarPanel, BorderLayout.NORTH);
        panel.add(headerPanel, BorderLayout.CENTER);
        panel.add(tablePanel, BorderLayout.SOUTH);

        return panel;
    }

    // MÉTODO PARA CARGAR LA FUENTE KUMBH SANS
    private static void cargarFuenteKumbhSans() {
        try {
            // Cargar la fuente desde la carpeta "fuentes"
            InputStream fontStream = PaginaPrincipalReal.class.getResourceAsStream("/fuentes/KumbhSans.ttf");

            if (fontStream != null) {
                // Crear la fuente desde el archivo TTF
                Font kumbhSansBase = Font.createFont(Font.TRUETYPE_FONT, fontStream);

                // Registrar la fuente en el sistema
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(kumbhSansBase);

                // Crear las variantes que vamos a usar
                kumbhSansPlain = kumbhSansBase.deriveFont(Font.PLAIN, 14f);
                kumbhSansBold = kumbhSansBase.deriveFont(Font.BOLD, 14f);

                fontStream.close();
                System.out.println("si se cargo la fuente vamoooos");
            } else {
                System.out.println("no se encontro el archivo de la fuente wtf");
            }
        } catch (Exception e) {
            System.out.println("error:  " + e.getMessage());
        }
    }

    // MÉTODO PARA OBTENER LA FUENTE KUMBH SANS
    public static Font getKumbhSansFont(int estilo, int tamaño) {
        // Si la fuente se cargó correctamente, usarla
        if (kumbhSansPlain != null) {
            return kumbhSansPlain.deriveFont(estilo, tamaño);
        } else {
            // Si no se pudo cargar, usar SansSerif como fallback
            return new Font("SansSerif", estilo, tamaño);
        }
    }

    private static JButton crearBotonMenuConIcono(String texto, String rutaIcono, Color colorBase) {
        JButton boton = new JButton(texto);

        // Cargar y redimensionar icono
        ImageIcon iconoOriginal = new ImageIcon(PaginaPrincipalReal.class.getResource(rutaIcono));
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        boton.setIcon(iconoRedimensionado);
        boton.setFont(getKumbhSansFont(Font.PLAIN, 16));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBase);

        // MARGENES AJUSTADOS: Menos padding izquierdo para pegar a la izquierda
        boton.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 20));

        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setMaximumSize(new Dimension(320, 45)); // Ancho completo
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setIconTextGap(15);

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (boton.getBackground().equals(colorBase)) {
                    boton.setBackground(new Color(70, 166, 229));
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (boton.getBackground().equals(new Color(70, 166, 229))) {
                    boton.setBackground(colorBase);
                }
            }
        });

        return boton;
    }
}
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class new_eventos {

    // Método para crear el panel del Cronograma de Eventos (Eventos este Mes)
    public static JPanel crearPanelCronogramaEventos(Color colorFondo, Color colorTexto, Color colorSubtitulo, Color colorGrisCombo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ========== TOP BAR CON CAMPANITA Y SALIR ==========
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.setBackground(colorFondo);
        topBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Panel derecho con campanita y salir
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightTopPanel.setBackground(colorFondo);

        // Icono campanita
        ImageIcon campanitaIcon = new ImageIcon(new_eventos.class.getResource("/imagenes/campanita.png"));
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

        // ========== HEADER PRINCIPAL CON TÍTULO Y COMBO BOX ==========
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(colorFondo);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Título del Panel
        JLabel titleLabel = new JLabel("Eventos De Este Mes", SwingConstants.CENTER);
        titleLabel.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 28));
        titleLabel.setForeground(colorTexto);

        // Panel para el ComboBox (Selector de Mes)
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        comboPanel.setBackground(colorFondo);
        comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JComboBox<String> mesCombo = new JComboBox<>(new String[]{
            "Seleccione Mes", "Enero 2024", "Febrero 2024", "Marzo 2024",
            "Abril 2024", "Mayo 2024", "Junio 2024"
        });
        mesCombo.setMaximumSize(new Dimension(200, 40));
        mesCombo.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        mesCombo.setBackground(colorGrisCombo);
        mesCombo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        comboPanel.add(mesCombo);

        // Panel para centrar título y combo
        JPanel centerHeader = new JPanel();
        centerHeader.setBackground(colorFondo);
        centerHeader.setLayout(new BoxLayout(centerHeader, BoxLayout.Y_AXIS));

        // Centrar el título horizontalmente
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerHeader.add(titleLabel);
        centerHeader.add(Box.createVerticalStrut(10));

        // Centrar el ComboBox/Selector de Mes
        JPanel selectorContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectorContainer.setBackground(colorFondo);
        selectorContainer.add(mesCombo);
        centerHeader.add(selectorContainer);
        centerHeader.add(Box.createVerticalStrut(20));

        // Ensamblar header
        headerPanel.add(centerHeader, BorderLayout.CENTER);

        // ========== PANEL DE TABLA ==========
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        // Crear tabla
        String[] columnNames = {"Eventos", "Horas libres"};
        Object[][] data = {
            {"• Taller de Programación en Python", "10 horas libres"},
            {"• Feria de Emprendimiento Tecnológico", "15 horas libres"},
            {"• Conferencia: Ciberseguridad 3.0", "4 horas libres"},
            {"• Maratón de Diseño UX/UI", "8 horas libres"},
            {"• Conversatorio sobre IA Generativa", "5 horas libres"}
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
        eventsTable.setFont(PaginaPrincipalReal.getKumbhSansFont(Font.PLAIN, 14));
        eventsTable.setForeground(colorTexto);
        eventsTable.setBackground(Color.WHITE);
        eventsTable.setShowGrid(true);
        eventsTable.setGridColor(new Color(230, 230, 230));

        eventsTable.getTableHeader().setFont(PaginaPrincipalReal.getKumbhSansFont(Font.BOLD, 14));
        eventsTable.getTableHeader().setBackground(new Color(250, 250, 250));
        eventsTable.getTableHeader().setForeground(colorTexto);
        eventsTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220, 220, 220)));

        // Alineación a la izquierda para el contenido de la tabla
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        // Alineación al centro para "Horas Libres"
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        eventsTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        eventsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(eventsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ========== ENSAMBLAJE FINAL ==========
        JPanel contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(colorFondo);
        contentArea.add(topBarPanel, BorderLayout.NORTH);
        contentArea.add(headerPanel, BorderLayout.CENTER);
        contentArea.add(tablePanel, BorderLayout.SOUTH);

        panel.add(contentArea, BorderLayout.CENTER);

        return panel;
    }
}

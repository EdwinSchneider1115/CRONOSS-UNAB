package javaapplication1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelCalendario {

    // Colores (Los mismos de tu app principal)
    private static final Color COLOR_AZUL_BTN = new Color(80, 156, 219);
    private static final Color COLOR_FONDO = new Color(245, 245, 245);
    private static final Color COLOR_HEADER_CAL = Color.BLACK;
    
    // Estructura para guardar eventos (DÃ­a -> Lista de eventos)
    // EN UNA APP REAL: AquÃ­ es donde se guardarÃ­an los datos que vienen de la API
    private static Map<Integer, List<String>> eventosDelMes = new HashMap<>();
    
    // Componentes grÃ¡ficos para actualizar
    private static JPanel panelDias;
    private static int anioActual = 2025;
    private static int mesActual = Calendar.NOVEMBER; // Noviembre

    public static JPanel crearPanelCalendario() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_FONDO);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- 1. SECCIÃ“N DE BOTONES SUPERIORES ---
        JPanel topButtonsPanel = new JPanel(new BorderLayout());
        topButtonsPanel.setBackground(COLOR_FONDO);
        topButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton btnEliminar = crearBotonEstilo("Eliminar evento");
        JButton btnAgregar = crearBotonEstilo("Agregar evento");

        // LÃ³gica del botÃ³n Agregar (SimulaciÃ³n API)
        btnAgregar.addActionListener(e -> agregarEventoLogica());
        
        // LÃ³gica del botÃ³n Eliminar (SimulaciÃ³n API)
        btnEliminar.addActionListener(e -> eliminarEventoLogica());

        topButtonsPanel.add(btnEliminar, BorderLayout.WEST);
        topButtonsPanel.add(btnAgregar, BorderLayout.EAST);

        // --- 2. TÃTULO DEL MES ---
        JLabel lblTitulo = new JLabel("Noviembre 2025", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24)); // Usa tu fuente Kumbh si estÃ¡ disponible
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // --- 3. GRID DEL CALENDARIO ---
        // Panel contenedor de la cuadrÃ­cula
        JPanel calendarioPanel = new JPanel(new BorderLayout());
        calendarioPanel.setBackground(Color.WHITE);
        
        // Panel para los dÃ­as (Lunes, Martes...)
        JPanel headerDiasPanel = new JPanel(new GridLayout(1, 7));
        headerDiasPanel.setBackground(COLOR_HEADER_CAL);
        
        String[] diasSemana = {"LUNES", "MARTES", "MIÃ‰RCOLES", "JUEVES", "VIERNES", "SÃBADO", "DOMINGO"};
        for (String dia : diasSemana) {
            JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
            lblDia.setForeground(Color.WHITE);
            lblDia.setFont(new Font("SansSerif", Font.BOLD, 12));
            lblDia.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            headerDiasPanel.add(lblDia);
        }

        // Panel para los nÃºmeros de los dÃ­as
        panelDias = new JPanel(new GridLayout(0, 7)); // 7 columnas, filas automÃ¡ticas
        panelDias.setBackground(Color.WHITE);
        
        // DIBUJAR LOS DÃAS
        renderizarDias(panelDias, mesActual, anioActual);

        calendarioPanel.add(headerDiasPanel, BorderLayout.NORTH);
        calendarioPanel.add(panelDias, BorderLayout.CENTER);

        // --- ENSAMBLAJE FINAL ---
        JPanel centroContainer = new JPanel(new BorderLayout());
        centroContainer.setBackground(COLOR_FONDO);
        centroContainer.add(lblTitulo, BorderLayout.NORTH);
        centroContainer.add(calendarioPanel, BorderLayout.CENTER);

        mainPanel.add(topButtonsPanel, BorderLayout.NORTH);
        mainPanel.add(centroContainer, BorderLayout.CENTER);

        return mainPanel;
    }

    // --- MÃ‰TODO PARA DIBUJAR LOS DÃAS DEL MES ---
    private static void renderizarDias(JPanel panel, int mes, int anio) {
        panel.removeAll(); // Limpiar calendario anterior

        Calendar cal = new GregorianCalendar(anio, mes, 1);
        int diasEnMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // Obtener en quÃ© dÃ­a de la semana empieza el mes (1=Domingo, 2=Lunes...)
        // Ajustamos para que Lunes sea el primero
        int diaInicioSemana = cal.get(Calendar.DAY_OF_WEEK); 
        int offset = (diaInicioSemana == Calendar.SUNDAY) ? 6 : diaInicioSemana - 2;

        // Rellenar espacios vacÃ­os del mes anterior
        for (int i = 0; i < offset; i++) {
            JPanel celdaVacia = new JPanel();
            celdaVacia.setBackground(new Color(230, 230, 230)); // Gris claro
            celdaVacia.setBorder(new LineBorder(Color.LIGHT_GRAY));
            panel.add(celdaVacia);
        }

        // Dibujar los dÃ­as reales
        for (int i = 1; i <= diasEnMes; i++) {
            JPanel celdaDia = new JPanel(new BorderLayout());
            celdaDia.setBackground(Color.WHITE);
            celdaDia.setBorder(new LineBorder(Color.LIGHT_GRAY));
            celdaDia.setPreferredSize(new Dimension(100, 80)); // TamaÃ±o de celda

            // NÃºmero del dÃ­a
            JLabel lblNumero = new JLabel(" " + i);
            lblNumero.setFont(new Font("SansSerif", Font.BOLD, 14));
            celdaDia.add(lblNumero, BorderLayout.NORTH);
            
            // Verificar si hay eventos para este dÃ­a (SIMULACIÃ“N API)
            if (eventosDelMes.containsKey(i)) {
                JPanel panelEventos = new JPanel();
                panelEventos.setLayout(new BoxLayout(panelEventos, BoxLayout.Y_AXIS));
                panelEventos.setBackground(Color.WHITE);
                
                for(String ev : eventosDelMes.get(i)) {
                    JLabel lblEv = new JLabel("â€¢ " + ev);
                    lblEv.setFont(new Font("SansSerif", Font.PLAIN, 10));
                    lblEv.setForeground(COLOR_AZUL_BTN);
                    panelEventos.add(lblEv);
                }
                celdaDia.add(panelEventos, BorderLayout.CENTER);
            }

            panel.add(celdaDia);
        }

        // Rellenar espacios vacÃ­os al final para completar la cuadrÃ­cula visualmente
        int totalCeldas = offset + diasEnMes;
        int celdasRestantes = 42 - totalCeldas; // 6 filas x 7 col = 42
        for (int i = 0; i < celdasRestantes; i++) {
            JPanel celdaVacia = new JPanel();
            celdaVacia.setBackground(new Color(230, 230, 230));
            celdaVacia.setBorder(new LineBorder(Color.LIGHT_GRAY));
            panel.add(celdaVacia);
        }
        
        panel.revalidate();
        panel.repaint();
    }

    // --- LÃ“GICA SIMULADA (AQUÃ CONECTARÃAS TU API) ---
    
    private static void agregarEventoLogica() {
        // 1. Pedir datos al usuario
        String diaStr = JOptionPane.showInputDialog("Ingrese el dÃ­a de Noviembre (1-31):");
        if(diaStr == null) return;
        
        String evento = JOptionPane.showInputDialog("Nombre del evento:");
        if(evento == null) return;

        try {
            int dia = Integer.parseInt(diaStr);
            if (dia < 1 || dia > 31) {
                JOptionPane.showMessageDialog(null, "DÃ­a invÃ¡lido");
                return;
            }

            // --- AQUÃ VA TU CÃ“DIGO DE CONEXIÃ“N A API (POST) ---
            // Ejemplo: apiService.crearEvento(2025, 1, dia, evento);
            System.out.println("Conectando a API para guardar: " + evento);

            // SIMULACIÃ“N LOCAL: Guardamos en el mapa
            eventosDelMes.computeIfAbsent(dia, k -> new ArrayList<>()).add(evento);
            
            // Refrescar calendario
            renderizarDias(panelDias, mesActual, anioActual);
            JOptionPane.showMessageDialog(null, "Evento agregado correctamente");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un nÃºmero vÃ¡lido");
        }
    }

    private static void eliminarEventoLogica() {
        String diaStr = JOptionPane.showInputDialog("Ingrese el dÃ­a del evento a eliminar:");
        if(diaStr == null) return;

        try {
            int dia = Integer.parseInt(diaStr);
            
            // --- AQUÃ VA TU CÃ“DIGO DE CONEXIÃ“N A API (DELETE) ---
            // Ejemplo: apiService.eliminarEventosDelDia(2025, 1, dia);
            
            if (eventosDelMes.containsKey(dia)) {
                eventosDelMes.remove(dia);
                renderizarDias(panelDias, mesActual, anioActual);
                JOptionPane.showMessageDialog(null, "Eventos del dÃ­a " + dia + " eliminados.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay eventos ese dÃ­a.");
            }
        } catch (Exception ex) {
           // manejo de error
        }
    }

    // Helper para estilo de botones
    private static JButton crearBotonEstilo(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_AZUL_BTN);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
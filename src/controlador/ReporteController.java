package controlador;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.ReporteDao;
import modelo.ReportePDF;
import vista.InterfazPrincipal;

public class ReporteController implements ActionListener {

    private InterfazPrincipal vista;

    private ReporteDao reporteDao;

    private JComboBox<String> cmbEstado;

    private JComboBox<String> cmbCategoria;

    private JButton btnIncidencias;

    private JButton btnTecnicos;

    private JButton btnCategorias;

    public ReporteController(InterfazPrincipal vista) {

        this.vista = vista;

        reporteDao = new ReporteDao();

        construirInterfaz();

        // Evento del menú Reportes
        this.vista.panelRepor.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        mostrarReportes();
                    }
                });
    }

    // CONSTRUIR INTERFAZ
    private void construirInterfaz() {

        JPanel panel = new JPanel();

        panel.setLayout(null);

        // TÍTULO
        JLabel titulo =
                new JLabel(
                        "REPORTES DEL SISTEMA HELPDESK");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        titulo.setBounds(
                30,
                20,
                500,
                30);

        panel.add(titulo);

        // ESTADO
        JLabel lblEstado =
                new JLabel("Estado:");

        lblEstado.setBounds(
                30,
                80,
                100,
                30);

        panel.add(lblEstado);

        cmbEstado =
                new JComboBox<String>();

        cmbEstado.addItem("Todos");
        cmbEstado.addItem("Pendiente");
        cmbEstado.addItem("Asignada");
        cmbEstado.addItem("En Proceso");
        cmbEstado.addItem("Resuelta");

        cmbEstado.setBounds(
                100,
                80,
                180,
                30);

        panel.add(cmbEstado);

        // CATEGORIA
        JLabel lblCategoria =
                new JLabel("Categoría:");

        lblCategoria.setBounds(
                320,
                80,
                100,
                30);

        panel.add(lblCategoria);

        cmbCategoria =
                new JComboBox<String>();

        cmbCategoria.addItem("Todas");
        cmbCategoria.addItem("Hardware");
        cmbCategoria.addItem("Software Hospitalario");
        cmbCategoria.addItem("Red");
        cmbCategoria.addItem("Impresoras");
        cmbCategoria.addItem("Correo Institucional");
        cmbCategoria.addItem("Base de Datos");
        cmbCategoria.addItem("Equipos Biomédicos");
        cmbCategoria.addItem("Sistema HIS");

        cmbCategoria.setBounds(
                410,
                80,
                200,
                30);

        panel.add(cmbCategoria);

        // BOTÓN INCIDENCIAS
        btnIncidencias =
                new JButton(
                        "Reporte de Incidencias");

        btnIncidencias.setBounds(
                30,
                150,
                230,
                40);

        btnIncidencias.addActionListener(this);

        panel.add(btnIncidencias);

        // BOTÓN TÉCNICOS
        btnTecnicos =
                new JButton(
                        "Incidencias por Técnico");

        btnTecnicos.setBounds(
                280,
                150,
                230,
                40);

        btnTecnicos.addActionListener(this);

        panel.add(btnTecnicos);

        // BOTÓN CATEGORÍAS
        btnCategorias =
                new JButton(
                        "Incidencias por Categoría");

        btnCategorias.setBounds(
                530,
                150,
                230,
                40);

        btnCategorias.addActionListener(this);

        panel.add(btnCategorias);

        /*
         * La pestaña Reportes ya existe
         * en el jTabbedPanel.
         *
         * Reportes es la séptima pestaña,
         * por eso el índice es 6.
         */
        vista.panelReportes.setComponentAt(
               6 ,
                panel);
    }

    // EVENTOS
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnIncidencias) {

            generarIncidencias();

        } else if (e.getSource() == btnTecnicos) {

            generarTecnicos();

        } else if (e.getSource() == btnCategorias) {

            generarCategorias();
        }
    }

    // REPORTE DE INCIDENCIAS
    private void generarIncidencias() {

        String estado =
                cmbEstado
                        .getSelectedItem()
                        .toString();

        String categoria =
                cmbCategoria
                        .getSelectedItem()
                        .toString();

        List<String[]> datos =
                reporteDao.reporteIncidencias(
                        estado,
                        categoria);

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No existen incidencias "
                    + "para los filtros seleccionados.");

            return;
        }

        String archivo =
                ReportePDF.generarIncidencias(
                        datos,
                        estado,
                        categoria);

        abrirPDF(archivo);
    }

    // REPORTE POR TÉCNICO
    private void generarTecnicos() {

        List<String[]> datos =
                reporteDao.reportePorTecnico();

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No existen datos de técnicos.");

            return;
        }

        String archivo =
                ReportePDF.generarPorTecnico(
                        datos);

        abrirPDF(archivo);
    }

    // REPORTE POR CATEGORÍA
    private void generarCategorias() {

        List<String[]> datos =
                reporteDao.reportePorCategoria();

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No existen datos de categorías.");

            return;
        }

        String archivo =
                ReportePDF.generarPorCategoria(
                        datos);

        abrirPDF(archivo);
    }

    // ABRIR PDF
    private void abrirPDF(String archivo) {

        if (archivo == null) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No se pudo generar el reporte.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        try {

            File pdf =
                    new File(archivo);

            Desktop.getDesktop().open(pdf);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "El reporte fue generado en:\n"
                    + archivo);
        }
    }

    // MOSTRAR REPORTES
    private void mostrarReportes() {

        vista.panelReportes
                .setSelectedIndex(6);
    }
}
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.usuarios;
import modelo.usuariosDao;
import vista.InterfazPrincipal;

public class UsuariosController implements ActionListener, MouseListener, KeyListener {

    // Encapsular variables
    private usuarios usuarios;
    private usuariosDao usuariosDao;
    private InterfazPrincipal interfazPrincipal;

    DefaultTableModel model = new DefaultTableModel();

    // Constructor
    public UsuariosController(usuarios usuarios, usuariosDao usuariosDao,
            InterfazPrincipal interfazPrincipal) {

        this.usuarios = usuarios;
        this.usuariosDao = usuariosDao;
        this.interfazPrincipal = interfazPrincipal;

        // Botón Registrar Usuario
        this.interfazPrincipal.btn_usuarios_registrar.addActionListener(this);

        // Tabla
        this.interfazPrincipal.tabla_usuarios.addMouseListener(this);

        // Buscador
        this.interfazPrincipal.txt_usuarios_buscar.addKeyListener(this);

        // Modificar
        this.interfazPrincipal.btn_usuarios_modificar.addActionListener(this);

        // Eliminar
        this.interfazPrincipal.btn_usuarios_eliminar.addActionListener(this);

        // Cancelar
        this.interfazPrincipal.btn_usuarios_cancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // REGISTRAR USUARIO
        if (e.getSource() == interfazPrincipal.btn_usuarios_registrar) {

            if (interfazPrincipal.txt_usuarios_nombrecompleto.getText().equals("")
                    || interfazPrincipal.txt_usuarios_Direccion.getText().equals("")
                    || interfazPrincipal.txt_usuarios_telefono.getText().equals("")
                    || interfazPrincipal.txt_usuarios_correo.getText().equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Todos los campos son obligatorios"
                );

            } else {

                usuarios.setNombrecompleto(
                        interfazPrincipal.txt_usuarios_nombrecompleto
                                .getText().trim()
                );

                usuarios.setDireccion(
                        interfazPrincipal.txt_usuarios_Direccion
                                .getText().trim()
                );

                usuarios.setTelefono(
                        interfazPrincipal.txt_usuarios_telefono
                                .getText().trim()
                );

                usuarios.setCorreo(
                        interfazPrincipal.txt_usuarios_correo
                                .getText().trim()
                );

                if (usuariosDao.registrarUsuariosQuery(usuarios)) {

                    cleanTabla();
                    listarUsuarios();
                    cleanFields();

                    JOptionPane.showMessageDialog(
                            null,
                            "Usuario registrado con éxito"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ha ocurrido un error al registrar el usuario"
                    );
                }
            }

        // ACTUALIZAR USUARIO
        } else if (e.getSource() == interfazPrincipal.btn_usuarios_modificar) {

            if (interfazPrincipal.txt_usuarios_id.getText().equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Selecciona una fila para continuar"
                );

            } else if (interfazPrincipal.txt_usuarios_nombrecompleto.getText().equals("")
                    || interfazPrincipal.txt_usuarios_Direccion.getText().equals("")
                    || interfazPrincipal.txt_usuarios_telefono.getText().equals("")
                    || interfazPrincipal.txt_usuarios_correo.getText().equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Todos los campos son obligatorios"
                );

            } else {

                usuarios.setId_usuarios(
                        Integer.parseInt(
                                interfazPrincipal.txt_usuarios_id
                                        .getText().trim()
                        )
                );

                usuarios.setNombrecompleto(
                        interfazPrincipal.txt_usuarios_nombrecompleto
                                .getText().trim()
                );

                usuarios.setDireccion(
                        interfazPrincipal.txt_usuarios_Direccion
                                .getText().trim()
                );

                usuarios.setTelefono(
                        interfazPrincipal.txt_usuarios_telefono
                                .getText().trim()
                );

                usuarios.setCorreo(
                        interfazPrincipal.txt_usuarios_correo
                                .getText().trim()
                );

                if (usuariosDao.actualizarUsuariosQuery(usuarios)) {

                    cleanTabla();
                    listarUsuarios();
                    cleanFields();

                    JOptionPane.showMessageDialog(
                            null,
                            "Datos del usuario modificados con éxito"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ha ocurrido un error al modificar usuario"
                    );
                }
            }

        // ELIMINAR USUARIO
        } else if (e.getSource() == interfazPrincipal.btn_usuarios_eliminar) {

            int row = interfazPrincipal.tabla_usuarios.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debes seleccionar un usuario para eliminar"
                );

            } else {

                int id = Integer.parseInt(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 0)
                                .toString()
                );

                int question = JOptionPane.showConfirmDialog(
                        null,
                        "¿Realmente quieres eliminar este usuario?",
                        "Eliminar usuario",
                        JOptionPane.YES_NO_OPTION
                );

                if (question == JOptionPane.YES_OPTION) {

                    if (usuariosDao.eliminarUsuariosQuery(id)) {

                        cleanFields();
                        cleanTabla();
                        listarUsuarios();

                        interfazPrincipal.btn_usuarios_registrar
                                .setEnabled(true);

                        JOptionPane.showMessageDialog(
                                null,
                                "Usuario eliminado con éxito"
                        );
                    }
                }
            }

        // CANCELAR
        } else if (e.getSource() == interfazPrincipal.btn_usuarios_cancelar) {

            interfazPrincipal.btn_usuarios_registrar.setEnabled(true);

            cleanFields();
        }
    }

    // LIMPIAR CAMPOS
    public void cleanFields() {

        interfazPrincipal.txt_usuarios_id.setText("");
        interfazPrincipal.txt_usuarios_id.setEditable(true);

        interfazPrincipal.txt_usuarios_nombrecompleto.setText("");
        interfazPrincipal.txt_usuarios_Direccion.setText("");
        interfazPrincipal.txt_usuarios_telefono.setText("");
        interfazPrincipal.txt_usuarios_correo.setText("");
    }
    // LISTAR USUARIOS
    public void listarUsuarios() {

        List<usuarios> list = usuariosDao.listaUsuariosQuery(
                interfazPrincipal.txt_usuarios_buscar.getText()
        );

        model = (DefaultTableModel)
                interfazPrincipal.tabla_usuarios.getModel();

        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId_usuarios();
            row[1] = list.get(i).getNombrecompleto();
            row[2] = list.get(i).getTelefono();
            row[3] = list.get(i).getDireccion();
            row[4] = list.get(i).getCorreo();

            model.addRow(row);
        }

        interfazPrincipal.tabla_usuarios.setModel(model);
    }
    // CLICK EN TABLA
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == interfazPrincipal.tabla_usuarios) {

            int row = interfazPrincipal.tabla_usuarios
                    .rowAtPoint(e.getPoint());

            if (row >= 0) {

                interfazPrincipal.txt_usuarios_id.setText(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 0)
                                .toString()
                );

                interfazPrincipal.txt_usuarios_nombrecompleto.setText(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 1)
                                .toString()
                );

                interfazPrincipal.txt_usuarios_telefono.setText(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 2)
                                .toString()
                );

                interfazPrincipal.txt_usuarios_Direccion.setText(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 3)
                                .toString()
                );

                interfazPrincipal.txt_usuarios_correo.setText(
                        interfazPrincipal.tabla_usuarios
                                .getValueAt(row, 4)
                                .toString()
                );

                interfazPrincipal.btn_usuarios_registrar
                        .setEnabled(false);

                interfazPrincipal.txt_usuarios_id
                        .setEditable(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    // TECLADO
   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == interfazPrincipal.txt_usuarios_buscar) {

            cleanTabla();
            listarUsuarios();
        }
    }

 
    // LIMPIAR TABLA
    
    public void cleanTabla() {

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
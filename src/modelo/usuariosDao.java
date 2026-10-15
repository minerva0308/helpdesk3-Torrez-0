package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class usuariosDao {

    // Instanciar Conexion
    ConexionMySQL cn = new ConexionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Registrar usuarios
    public boolean registrarUsuariosQuery(usuarios usuarios) {

        String query = "INSERT INTO usuario "
                + "(nombrecompleto, Direccion, telefono, correo, creado, actuallizar) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        try {

            conn = cn.getConexion();

            pst = conn.prepareStatement(query);

            pst.setString(1, usuarios.getNombrecompleto());
            pst.setString(2, usuarios.getDireccion());
            pst.setString(3, usuarios.getTelefono());
            pst.setString(4, usuarios.getCorreo());
            pst.setTimestamp(5, dateTime);
            pst.setTimestamp(6, dateTime);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al registrar usuario: " + e
            );

            return false;
        }
    }

    // Listar usuarios
    public List<usuarios> listaUsuariosQuery(String value) {

        List<usuarios> list_usuarios = new ArrayList<>();

        String query = "SELECT * FROM usuario";

        String query_search_usuarios =
                "SELECT * FROM usuario WHERE id LIKE ?";

        try {

            conn = cn.getConexion();

            if (value == null || value.equalsIgnoreCase("")) {

                pst = conn.prepareStatement(query);

            } else {

                pst = conn.prepareStatement(query_search_usuarios);

                pst.setString(1, "%" + value + "%");
            }

            rs = pst.executeQuery();

            while (rs.next()) {

                usuarios usuario = new usuarios();

                usuario.setId_usuarios(
                        rs.getInt("id_usuarios")
                );

                usuario.setNombrecompleto(
                        rs.getString("nombrecompleto")
                );

                usuario.setDireccion(
                        rs.getString("Direccion")
                );

                usuario.setTelefono(
                        rs.getString("telefono")
                );

                usuario.setCorreo(
                        rs.getString("correo")
                );

                usuario.setCreado(
                        rs.getTimestamp("creado")
                );

                usuario.setActuallizar(
                        rs.getTimestamp("actuallizar")
                );

                list_usuarios.add(usuario);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al listar usuarios: " + e
            );
        }

        return list_usuarios;
    }

    // Modificar usuario
    public boolean actualizarUsuariosQuery(usuarios usuarios) {

        String query = "UPDATE usuario SET "
                + "nombrecompleto = ?, "
                + "Direccion = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "actuallizar = ? "
                + "WHERE id_usuarios = ?";

        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        try {

            conn = cn.getConexion();

            pst = conn.prepareStatement(query);

            pst.setString(1, usuarios.getNombrecompleto());
            pst.setString(2, usuarios.getDireccion());
            pst.setString(3, usuarios.getTelefono());
            pst.setString(4, usuarios.getCorreo());
            pst.setTimestamp(5, dateTime);
            pst.setInt(6, usuarios.getId_usuarios());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al modificar datos del usuario: " + e
            );

            return false;
        }
    }

    // Eliminar usuario
    public boolean eliminarUsuariosQuery(int id) {

        String query = "DELETE FROM usuario WHERE id = ?";

        try {

            conn = cn.getConexion();

            pst = conn.prepareStatement(query);

            pst.setInt(1, id);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se puede eliminar el usuario: " + e
            );

            return false;
        }
    }
}
package modelo;

import java.sql.Timestamp;

public class usuarios {

    private int id_usuarios;
    private String nombrecompleto;
    private String Direccion;
    private String telefono;
    private String correo;
    private Timestamp creado;
    private Timestamp actuallizar;

    // Constructor vacío
    public usuarios() {
    }

    // Constructor
    public usuarios(
            int id_usuarios,
            String nombrecompleto,
            String Direccion,
            String telefono,
            String correo,
            Timestamp creado,
            Timestamp actuallizar) {

        this.id_usuarios = id_usuarios;
        this.nombrecompleto = nombrecompleto;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.creado = creado;
        this.actuallizar = actuallizar;
    }

    // Getters y Setters

    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getCreado() {
        return creado;
    }

    public void setCreado(Timestamp creado) {
        this.creado = creado;
    }

    public Timestamp getActuallizar() {
        return actuallizar;
    }

    public void setActuallizar(Timestamp actuallizar) {
        this.actuallizar = actuallizar;
    }
}
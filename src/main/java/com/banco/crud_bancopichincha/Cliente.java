package com.banco.crud_bancopichincha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    @OneToMany(mappedBy = "cliente")
private List<Cuenta> cuentas;
    // Constructor vacio
    public Cliente() {
    }
    // Constructor para crear clientes
    public Cliente(String nombre, String cedula, String correo, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
    }
    // Obtener el ID
    public int getId() {
        return id;
    }
    // Asignar el ID para actualizar un cliente
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public String getCorreo() {
        return correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public List<Cuenta> getCuentas() {
    return cuentas;
}

public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
}
}

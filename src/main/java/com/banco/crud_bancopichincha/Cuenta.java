package com.banco.crud_bancopichincha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroCuenta;
    private Double saldo;
    private String estado;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private TipoCuenta tipoCuenta;
    @OneToMany(mappedBy = "cuenta")
    private List<Transaccion> transacciones;
    @OneToMany(mappedBy = "cuenta")
    private List<Tarjeta> tarjetas;
    public Cuenta() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public List<Transaccion> getTransacciones() {
    return transacciones;
    }
    public void setTransacciones(List<Transaccion> transacciones) {
    this.transacciones = transacciones;
    }
    public List<Tarjeta> getTarjetas() {
    return tarjetas;
    }
    public void setTarjetas(List<Tarjeta> tarjetas) {
    this.tarjetas = tarjetas;
    }
}

package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "calificacion", nullable = false)
    private Double calificacion;

    @Column(name = "referenciaPago", nullable = false, length = 50)
    private String referenciaPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "departamentoCliente", nullable = false)
    private DepartamentoCliente departamentoCliente;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;

    @OneToOne(cascade = CascadeType.REMOVE) // <--- ¡CAMBIO APLICADO!
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    public Cliente() {}

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago,
                   DepartamentoCliente departamentoCliente, String ciudad, Usuario usuario) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamentoCliente = departamentoCliente;
        this.ciudad = ciudad;
        this.usuario = usuario;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }
    public String getReferenciaPago() { return referenciaPago; }
    public void setReferenciaPago(String referenciaPago) { this.referenciaPago = referenciaPago; }
    public DepartamentoCliente getDepartamentoCliente() { return departamentoCliente; }
    public void setDepartamentoCliente(DepartamentoCliente departamentoCliente) { this.departamentoCliente = departamentoCliente; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}
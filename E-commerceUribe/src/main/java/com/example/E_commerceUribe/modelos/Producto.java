package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.CategoriaProducto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name ="nombre", nullable = false, unique = false, length = 50)
    private String nombre;

    @Column (name = "fotografia", nullable = false, unique = false, length = 25)
    private String fotografia;

    @Column (name = "descripcion", nullable = false, unique = false, length = 100)
    private String descripcion;

    @Column (name = "categoriaProducto", nullable = false, unique = false)
    @Enumerated (EnumType.STRING)
    private CategoriaProducto categoriaProducto;

    @Column (name = "precioUnitario", nullable = false, unique = false)
    private Integer precioUnitario;

    @Column (name = "marca", nullable = false, unique = false, length = 25)
    private String marca;

    @Column (name = "aplicaDescuento", nullable = false, unique = false, length = 6)
    private Boolean aplicaDescuento;


    @ManyToOne
    @JoinColumn(name = "fk_pedido", referencedColumnName = "id")
    @JsonBackReference(value = "relacionpedidoproducto")
    private Pedido pedido; // Atributo de relación

    public Producto() {
    }

    public Producto(Integer id, String nombre, String fotografia, String descripcion, CategoriaProducto categoriaProducto, Integer precioUnitario, String marca, Boolean aplicaDescuento) {
        this.id = id;
        this.nombre = nombre;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
        this.categoriaProducto = categoriaProducto;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
        this.aplicaDescuento = aplicaDescuento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    // 💡 MÉTODOS DE ACCESO FALTANTES (CAUSA DEL ERROR EN EL SERVICIO)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
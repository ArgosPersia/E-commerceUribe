package com.example.E_commerceUribe.modelos.DTO;

public class ProductoDTO {

    private Integer id;
    private String nombre;
    private String fotografia;
    private String descripcion;
    private Integer precioUnitario;
    private String marca;
    private Boolean aplicaDescuento;

    public ProductoDTO() {
    }

    public ProductoDTO(Integer id, String nombre, String fotografia, String descripcion, Integer precioUnitario, String marca, Boolean aplicaDescuento) {
        this.id = id;
        this.nombre = nombre;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
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
}

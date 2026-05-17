package punto.de.venta;

import java.time.LocalDate;

public class Articulo {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private LocalDate fechaIngreso;

    public Articulo(String id, String nombre, double precio, int stock, String categoria, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.fechaIngreso = fechaIngreso;
    }

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
}
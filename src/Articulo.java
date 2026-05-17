package punto.de.venta;

import java.time.LocalDate;

public class Articulo {
    
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private LocalDate fechaIngreso;

    
    public Articulo() {}

    
    public Articulo(String id, String nombre, double precio, int stock, String categoria, LocalDate fechaIngreso) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
        setCategoria(categoria);
        setFechaIngreso(fechaIngreso);
    }

   
    
    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío.");
        }
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 30) { // Límite de caracteres para el cálculo de bytes posterior
            this.nombre = nombre.substring(0, 30);
            return;
        }
        this.nombre = nombre;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.precio = precio;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        this.categoria = categoria;
    }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) {
        if (fechaIngreso == null) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser nula.");
        }
        this.fechaIngreso = fechaIngreso;
    }

    
    @Override
    public String toString() {
        return id + "|" + nombre + "|" + precio + "|" + stock + "|" + categoria + "|" + fechaIngreso;
    }
}
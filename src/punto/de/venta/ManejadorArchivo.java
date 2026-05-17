package punto.de.venta;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivo {
    
    private static final String NOMBRE_ARCHIVO = "articulos.csv";

    // Método para guardar los artículos en el archivo TXT
    public static void guardarArticulos(List<Articulo> articulos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Articulo art : articulos) {
                bw.write(art.getId() + "," +
                         art.getNombre() + "," +
                         art.getPrecio() + "," +
                         art.getStock() + "," +
                         art.getCategoria() + "," +
                         art.getFechaIngreso());
                bw.newLine();
            }
        }
    }

    // Método para leer los artículos desde el archivo TXT
    public static List<Articulo> leerArticulos() throws IOException {
        List<Articulo> lista = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);
        
        if (!archivo.exists()) {
            return lista; // Si no existe el archivo, regresa la lista vacía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String id = datos[0];
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int stock = Integer.parseInt(datos[3]);
                    String categoria = datos[4];
                    LocalDate fecha = LocalDate.parse(datos[5]);
                    
                    lista.add(new Articulo(id, nombre, precio, stock, categoria, fecha));
                }
            }
        }
        return lista;
    }
}
package punto.de.venta;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManejadorArchivo {
    
    
    private static final String RUTA_TXT = "articulos.txt";

    
    public static void guardarArticulos(ArrayList<Articulo> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_TXT))) {
            for (Articulo art : lista) {
                pw.println(art.getId() + "," +
                           art.getNombre() + "," +
                           art.getPrecio() + "," +
                           art.getStock() + "," +
                           art.getCategoria() + "," +
                           art.getFechaIngreso());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo TXT principal: " + e.getMessage());
        }
    }

    
    public static ArrayList<Articulo> leerArticulos() {
        ArrayList<Articulo> lista = new ArrayList<>();
        File archivo = new File(RUTA_TXT);
        
        if (!archivo.exists()) {
            return lista; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue; 
                
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    int stock = Integer.parseInt(datos[3].trim());
                    String categoria = datos[4].trim();
                    LocalDate fecha = LocalDate.parse(datos[5].trim());
                    
                    lista.add(new Articulo(id, nombre, precio, stock, categoria, fecha));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo TXT principal: " + e.getMessage());
        }
        return lista;
    }

    public static int importarDesdeCSV(File archivoCSV, ArrayList<Articulo> listaActual) {
        int contadorImportados = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    int stock = Integer.parseInt(datos[3].trim());
                    String categoria = datos[4].trim();
                    LocalDate fecha = LocalDate.parse(datos[5].trim());
                    
                    // Programación defensiva: Verificar que el ID que viene en el CSV no exista ya en tu .txt
                    boolean duplicado = false;
                    for (Articulo art : listaActual) {
                        if (art.getId().equalsIgnoreCase(id)) {
                            duplicado = true;
                            break;
                        }
                    }
                    
                    // Si el ID es nuevo, lo agregamos a la app
                    if (!duplicado) {
                        listaActual.add(new Articulo(id, nombre, precio, stock, categoria, fecha));
                        contadorImportados++;
                    }
                }
            }
            
            // Si importó cosas nuevas del CSV, las guardamos de golpe en el .txt principal
            if (contadorImportados > 0) {
                guardarArticulos(listaActual);
            }
            
        } catch (Exception e) {
            System.out.println("Error al importar desde el archivo CSV externo: " + e.getMessage());
        }
        
        return contadorImportados; 
    }
}
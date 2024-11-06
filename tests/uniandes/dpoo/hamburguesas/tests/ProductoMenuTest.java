
package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

/**
 * Clase de prueba para la clase ProductoMenu.
 */
public class ProductoMenuTest {
    
    private ProductoMenu producto;

    @Before
    public void setUp() {
        // Crear un producto de ejemplo con nombre y precio
        producto = new ProductoMenu("Hamburguesa Sencilla", 10000);
    }

    @Test
    public void testGetNombre() {
        // Verifica que el nombre del producto sea correcto
        assertEquals("Hamburguesa Sencilla", producto.getNombre());
    }

    @Test
    public void testGetPrecio() {
        // Verifica que el precio base del producto sea correcto
        assertEquals(10000, producto.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Asumiendo que el método de generación de texto para la factura retorna nombre y precio
        String textoEsperado = "Hamburguesa Sencilla: $10000";
        assertEquals(textoEsperado, producto.generarTextoFactura());
    }
}

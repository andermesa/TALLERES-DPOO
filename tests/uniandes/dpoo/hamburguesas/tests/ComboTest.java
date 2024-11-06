
package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

/**
 * Clase de prueba para la clase Combo.
 */
public class ComboTest {

    private Combo combo;

    @Before
    public void setUp() {
        // Crear productos de ejemplo
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu papas = new ProductoMenu("Papas", 5000);
        
        // Crear una lista de productos y un combo con descuento del 10%
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(hamburguesa);
        items.add(papas);
        
        combo = new Combo("Combo Sencillo", 0.1, items);
    }

    @Test
    public void testGetNombreCombo() {
        // Verifica que el nombre del combo sea correcto
        assertEquals("Combo Sencillo", combo.getNombre());
    }

    @Test
    public void testCalcularPrecioConDescuento() {
        // Verifica que el precio total con descuento sea correcto
        int precioEsperado = 13500; // (10000 + 5000) * (1 - 0.1)
        assertEquals(precioEsperado, combo.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Verifica que el texto de la factura del combo se genere correctamente
        String textoEsperado = "Combo Sencillo (10% de descuento): $13500";
        assertEquals(textoEsperado, combo.generarTextoFactura());
    }
}

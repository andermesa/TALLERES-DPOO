
package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

/**
 * Clase de prueba para la clase ProductoAjustado.
 */
public class ProductoAjustadoTest {
    
    private ProductoAjustado productoAjustado;
    private Ingrediente queso;
    private Ingrediente tocineta;

    @Before
    public void setUp() {
        // Crear un producto base
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa Sencilla", 10000);
        productoAjustado = new ProductoAjustado(productoBase);
        
        // Crear ingredientes adicionales
        queso = new Ingrediente("Queso", 1000);
        tocineta = new Ingrediente("Tocineta", 2000);
    }

    @Test
    public void testAgregarIngrediente() {
        // Agregar ingredientes y verificar el precio ajustado
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.agregarIngrediente(tocineta);
        
        int precioEsperado = 13000; // 10000 base + 1000 queso + 2000 tocineta
        assertEquals(precioEsperado, productoAjustado.getPrecio());
    }

    @Test
    public void testEliminarIngrediente() {
        // Eliminar un ingrediente y verificar que el precio sigue siendo el mismo (eliminar no afecta el precio)
        productoAjustado.eliminarIngrediente(queso);
        
        int precioEsperado = 10000; // Eliminar no reduce el precio
        assertEquals(precioEsperado, productoAjustado.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Verificar que el texto de la factura se genere correctamente
        productoAjustado.agregarIngrediente(queso);
        String textoEsperado = "Hamburguesa Sencilla con: - Queso: +$1000 Total: $11000";
        assertEquals(textoEsperado, productoAjustado.generarTextoFactura());
    }
}


package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

/**
 * Clase de prueba para la clase Pedido.
 */
public class PedidoTest {

    private Pedido pedido;

    @Before
    public void setUp() {
        // Crear un nuevo pedido con datos de ejemplo del cliente
        pedido = new Pedido("Anderson Mesa", "Calle 123");
        
        // Agregar productos al pedido
        ProductoMenu hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu papas = new ProductoMenu("Papas", 5000);
        
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);
    }

    @Test
    public void testGetIdPedido() {
        // Verifica que el id del pedido se haya asignado correctamente
        assertEquals(1, pedido.getIdPedido()); // Suponiendo que sea el primer pedido
    }

    @Test
    public void testCalcularPrecioConIVA() {
        // Verifica que el precio total con IVA sea correcto
        int precioEsperado = (int)((10000 + 5000) * 1.19); // (precio base) * (1 + IVA)
        assertEquals(precioEsperado, pedido.getPrecioTotalConIVA());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Verifica que el texto de la factura se genere correctamente
        String textoEsperado = "Factura para Anderson Mesa\nHamburguesa: $10000\nPapas: $5000\nTotal (sin IVA): $15000\nIVA: $2850\nTotal (con IVA): $17850";
        assertEquals(textoEsperado, pedido.generarTextoFactura());
    }

    @Test
    public void testGuardarFactura() {
        // Verifica que la factura se guarde correctamente en un archivo
        pedido.guardarFactura();
        File factura = new File("factura-" + pedido.getIdPedido() + ".txt");
        assertEquals(true, factura.exists());
        
        // Eliminar el archivo de prueba después de verificar su existencia
        factura.delete();
    }
}

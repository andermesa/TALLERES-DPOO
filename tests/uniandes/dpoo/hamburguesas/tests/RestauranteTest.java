
package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import java.io.File;
import java.io.IOException;

/**
 * Clase de prueba para la clase Restaurante.
 */
public class RestauranteTest {

    private Restaurante restaurante;

    @Before
    public void setUp() throws ProductoRepetidoException, IOException, IngredienteRepetidoException, ProductoFaltanteException {
        // Crear una instancia del restaurante y cargar los datos
        restaurante = new Restaurante();
        restaurante.cargarMenu(new File("data/menu.txt"));
        restaurante.cargarIngredientes(new File("data/ingredientes.txt"));
        restaurante.cargarCombos(new File("data/combos.txt"));
    }

    @Test
    public void testCargarDatos() {
        // Verifica que los datos del menú, ingredientes y combos se hayan cargado correctamente
        assertNotNull(restaurante.getMenuBase());
        assertNotNull(restaurante.getIngredientes());
        assertNotNull(restaurante.getMenuCombos());
        assertTrue(restaurante.getMenuBase().size() > 0);
        assertTrue(restaurante.getIngredientes().size() > 0);
        assertTrue(restaurante.getMenuCombos().size() > 0);
    }

    @Test
    public void testIniciarYCerrarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        // Verifica el flujo de iniciar y cerrar un pedido
        restaurante.iniciarPedido("Juan", "Calle 45");
        assertNotNull(restaurante.getPedidoEnCurso());

        // Agrega productos y cierra el pedido
        restaurante.agregarProductoAPedido(restaurante.getMenuBase().get(0));
        restaurante.cerrarYGuardarPedido();

        File factura = new File("./facturas/factura-" + restaurante.getPedidoEnCurso().getIdPedido() + ".txt");
        assertTrue(factura.exists());

        // Eliminar el archivo de prueba después de verificar su existencia
        factura.delete();
    }

    @Test(expected = YaHayUnPedidoEnCursoException.class)
    public void testYaHayUnPedidoEnCursoException() throws YaHayUnPedidoEnCursoException {
        // Verifica que se lance la excepción al intentar iniciar un nuevo pedido cuando ya hay uno en curso
        restaurante.iniciarPedido("Ander", "Calle 45");
        restaurante.iniciarPedido("Mesa", "Avenida 10");
    }
}

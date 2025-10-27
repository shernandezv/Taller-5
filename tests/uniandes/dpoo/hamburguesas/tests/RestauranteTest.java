package uniandes.dpoo.hamburguesas.tests;






import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	Restaurante restaurante;
	
	@BeforeEach
	public void setUp( ){
		Pedido.reiniciarContadorPedidos();
        this.restaurante = new Restaurante();
	}

	@Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
		this.restaurante.iniciarPedido("Santiago", "SD_307");
		Pedido pedido = this.restaurante.getPedidoEnCurso();
		assertEquals("Santiago", pedido.getNombreCliente());
		assertEquals(0, pedido.getIdPedido());
		assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
			this.restaurante.iniciarPedido("Juan", "SD_308");
	    });
    }

	@Test
    public void testgetPedidoEnCurso() throws NoHayPedidoEnCursoException, YaHayUnPedidoEnCursoException {
		this.restaurante.iniciarPedido("Santiago", "SD_307");
		Pedido pedido = this.restaurante.getPedidoEnCurso();
		assertEquals("Santiago", pedido.getNombreCliente());
		assertEquals(0, pedido.getIdPedido());
    }
	
	@Test
    public void testCerrarYGuardarPedido() throws NoHayPedidoEnCursoException, YaHayUnPedidoEnCursoException, IOException {
		assertThrows(NoHayPedidoEnCursoException.class, () -> {
			this.restaurante.cerrarYGuardarPedido();
	    });
		this.restaurante.iniciarPedido("Santiago", "SD_307");
		Pedido pedido = this.restaurante.getPedidoEnCurso();
		ProductoMenu producto = new ProductoMenu("corral", 14000);
		pedido.agregarProducto(producto);
		this.restaurante.cerrarYGuardarPedido();
		assertEquals(null, this.restaurante.getPedidoEnCurso());
    }
	
	@Test
    public void testCargarInformacionRestaurante() throws IOException, NumberFormatException, HamburguesaException {
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
		assertEquals(22, this.restaurante.getMenuBase().size());
		assertEquals(4, this.restaurante.getMenuCombos().size());
		assertEquals(15, this.restaurante.getIngredientes().size());
    }
	
	@Test
    public void testIngredientesRepetidos() throws IOException, NumberFormatException, HamburguesaException {
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientesRepetidos.txt");
		assertThrows(IngredienteRepetidoException.class, () -> {
			this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
	    });
    }
	
	@Test
    public void testMenuRepetidos() throws IOException, NumberFormatException, HamburguesaException {
		File menu = new File("data/menuRepetidos.txt");
		File combos = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		assertThrows(ProductoRepetidoException.class, () -> {
			this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
	    });
    }
	
	@Test
    public void testCombosRepetidos() throws IOException, NumberFormatException, HamburguesaException {
		File menu = new File("data/menu.txt");
		File combos = new File("data/combosRepetidos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		assertThrows(ProductoRepetidoException.class, () -> {
			this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
	    });
    }
	
	@Test
    public void testCombosFaltantes() throws IOException, NumberFormatException, HamburguesaException {
		File menu = new File("data/menu.txt");
		File combos = new File("data/combosFaltantes.txt");
		File ingredientes = new File("data/ingredientes.txt");
		assertThrows(ProductoFaltanteException.class, () -> {
			this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
	    }); 
    }
	
	@Test
    public void testGetPedidos() throws IOException, NumberFormatException, HamburguesaException {
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		this.restaurante.iniciarPedido("Santiago", "SD_307");
		Pedido pedido = this.restaurante.getPedidoEnCurso();
		ProductoMenu producto = new ProductoMenu("corral", 14000);
		pedido.agregarProducto(producto);
		lista.add(pedido);
		this.restaurante.cerrarYGuardarPedido();
		assertEquals(lista, this.restaurante.getPedidos());
    }
	
	@Test
	public void testConsultarPedidoExistente() {
	    Pedido pedido = new Pedido("Santiago", "SD_307");
	    int id = pedido.getIdPedido();
	    restaurante.getPedidos().add(pedido);
	    assertDoesNotThrow(() -> restaurante.consultarPedido(id),"No debería lanzar excepción al consultar un pedido existente");
	}
}

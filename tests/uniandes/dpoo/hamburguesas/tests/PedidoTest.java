package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	Pedido pedido;
	private final ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
    private final PrintStream salidaOriginal = System.out;
    
	@BeforeEach
	public void setUp( ) throws FileNotFoundException{
		System.setOut(new PrintStream(salidaConsola));
		Pedido.reiniciarContadorPedidos();
        ProductoMenu producto1 = new ProductoMenu("corral", 14000);
        ProductoMenu producto2 = new ProductoMenu("papas", 4000);
        ProductoMenu producto3 = new ProductoMenu("gaseosa", 5000);
        pedido = new Pedido("Santiago", "SD_307");
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        pedido.agregarProducto(producto3);
        String nombreArchivo =  "facturas/factura_0.txt";
        pedido.mostrarInformacion();
		pedido.guardarFactura( new File( nombreArchivo ) );
	}
	@AfterEach
	public void tearDown() {
		System.setOut(salidaOriginal);
	}
	
	@Test
	public void testGetIdPedido() {
		assertEquals(0, this.pedido.getIdPedido(), "El id del pedido no es el esperado.");
	}
	
	@Test
	public void testGetNombreCliente() {
		assertEquals("Santiago", this.pedido.getNombreCliente(), "El nombre del cliente no es el esperado.");
	}
	
	@Test
	public void testGetPrecioNetoPedido() {
		assertEquals(27370, this.pedido.getPrecioTotalPedido(), "El nombre del cliente no es el esperado.");
	}
	
	@Test
    public void testGenerarTextoFactura() {
        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Cliente: Santiago"), "Debe incluir el nombre del cliente.");
        assertTrue(factura.contains("Dirección: SD_307"), "Debe incluir la dirección del cliente.");

        assertTrue(factura.contains("corral"), "Debe incluir el producto 'corral'.");
        assertTrue(factura.contains("papas"), "Debe incluir el producto 'papas'.");
        assertTrue(factura.contains("gaseosa"), "Debe incluir el producto 'gaseosa'.");

        assertTrue(factura.contains("Precio Neto:  23000"), "Debe incluir el precio neto.");
        assertTrue(factura.contains("IVA:          4370"), "Debe incluir el IVA.");
        assertTrue(factura.contains("Precio Total: 27370"), "Debe incluir el precio total.");
    }
	
	@Test
    public void testGuardarFactura() throws IOException {
		File archivoFactura = new File("facturas/factura_0.txt");
		assertTrue(archivoFactura.exists(), "El archivo de factura no fue creado.");
    }
	
	@Test
    public void testMostrarInformacion() {
        String salida = salidaConsola.toString();

        assertTrue(salida.contains("Información pedido número: 0."), "Debe mostrar el ID del pedido.");
        assertTrue(salida.contains("Cliente: Santiago."), "Debe mostrar el nombre del cliente.");
        assertTrue(salida.contains("Dirección: SD_307."), "Debe mostrar la dirección del cliente.");
        assertTrue(salida.contains("Productos:"), "Debe indicar la sección de productos.");

        assertTrue(salida.contains("corral"), "Debe mostrar el producto 'corral'.");
        assertTrue(salida.contains("papas"), "Debe mostrar el producto 'papas'.");
        assertTrue(salida.contains("gaseosa"), "Debe mostrar el producto 'gaseosa'.");
    }
}

package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	private ProductoMenu producto1;
	
	@BeforeEach
	public void setUp( ){
        this.producto1 = new ProductoMenu( "corral", 14000);
    }
	
	@Test
	public void testGetNombre() {
		assertEquals("corral", this.producto1.getNombre(), "El nombre del producto básico no es el esperado. ");
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(14000, this.producto1.getPrecio(), "El precio del producto básico no es el esperado. ");
	}
	
	@Test
    public void testGenerarTextoFactura() {
        String resultado = producto1.generarTextoFactura();
        String esperado = "corral\n            14000\n";
        assertEquals(esperado, resultado, "El texto de la factura no tiene el formato o valores esperados.");
    }
	
	@Test
    public void testToString() {
        String resultado = producto1.toString();
        String esperado = "Nombre: corral. Precio: 14000.";
        assertEquals(esperado, resultado, "El texto del método toString() no coincide con el formato esperado.");
    }
}

package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private Combo combo;
	@BeforeEach
	public void setUp( ){
        ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
        ProductoMenu producto1 = new ProductoMenu("corral", 14000);
        ProductoMenu producto2 = new ProductoMenu("papas", 4000);
        ProductoMenu producto3 = new ProductoMenu("gaseosa", 5000);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        combo = new Combo("corral con papas y gaseosa", 0.1, productos);
    }
	
	@Test
	public void testGetNombre() {
		assertEquals("corral con papas y gaseosa", this.combo.getNombre(), "El nombre del producto básico no es el esperado. ");
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(20700, this.combo.getPrecio(), "El precio del combo no es el esperado. ");
	}
	
	@Test
	public void testGenerarTextoFactura() {
        String textoFactura = combo.generarTextoFactura();
        assertTrue(textoFactura.contains("Combo corral con papas y gaseosa"));
        assertTrue(textoFactura.contains("Descuento: 0.1"));
        assertTrue(textoFactura.contains("20700"));
    }
	
    @Test
    public void testToString() {
        String texto = combo.toString();
        assertEquals("Nombre: corral con papas y gaseosa. Precio: 20700.", texto);
    }
}

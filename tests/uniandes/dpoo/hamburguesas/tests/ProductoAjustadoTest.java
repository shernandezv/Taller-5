package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	ProductoAjustado producto1;
	@BeforeEach
	public void setUp( ){
        ProductoMenu productoBase = new ProductoMenu("corral", 14000);
        producto1 = new ProductoAjustado(productoBase);
        ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
        ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
        Ingrediente ingrediente1 = new Ingrediente("lechuga", 1000);
        Ingrediente ingrediente2 = new Ingrediente("tomate", 1000);
        Ingrediente ingrediente3 = new Ingrediente("cebolla", 1000);
        Ingrediente ingrediente4 = new Ingrediente("queso mozzarella", 2500);
        Ingrediente ingrediente5 = new Ingrediente("huevo", 2500);
        Ingrediente ingrediente6 = new Ingrediente("queso americano", 2500);
        
        agregados.add(ingrediente1);
        producto1.agregarIngrediente(ingrediente1);
        agregados.add(ingrediente2);
        producto1.agregarIngrediente(ingrediente2);
        agregados.add(ingrediente3);
        producto1.agregarIngrediente(ingrediente3);
        
        eliminados.add(ingrediente4);
        producto1.eliminarIngrediente(ingrediente4);
        eliminados.add(ingrediente5);
        producto1.eliminarIngrediente(ingrediente5);
        eliminados.add(ingrediente6);
        producto1.eliminarIngrediente(ingrediente6);
    }
	
	@Test
	public void testGetNombre() {
		assertEquals("corral", this.producto1.getNombre(), "El nombre del producto básico no es el esperado. ");
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(17000, this.producto1.getPrecio(), "El precio del producto ajustado no es el esperado. ");
	}
	
    @Test
    public void testToString() {
        String esperado = "Nombre: corral. Precio Ajustado: 17000.";
        assertEquals(esperado, producto1.toString(),
            "El texto de toString() no coincide con el formato esperado.");
    }
    
    @Test
    public void testGenerarTextoFactura() {
        String resultado = producto1.generarTextoFactura();

        assertTrue(resultado.contains("corral"),
            "El texto de factura no incluye el nombre del producto base.");
        assertTrue(resultado.contains("+lechuga"),
            "El texto de factura no incluye el ingrediente agregado.");
        assertTrue(resultado.contains("+tomate"),
            "El texto de factura no incluye el ingrediente agregado.");
        assertTrue(resultado.contains("+cebolla"),
                "El texto de factura no incluye el ingrediente agregado.");
        assertTrue(resultado.contains("-queso mozzarella"),
                "El texto de factura no incluye el ingrediente eliminado.");
            assertTrue(resultado.contains("-huevo"),
                "El texto de factura no incluye el ingrediente eliminado.");
            assertTrue(resultado.contains("-queso americano"),
                    "El texto de factura no incluye el ingrediente eliminado.");
        assertTrue(resultado.contains("17000"),
            "El texto de factura no incluye el precio final correcto.");
    }
}

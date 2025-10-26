package presentacion;

import java.io.File;
import java.io.IOException;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class Principal {
	Restaurante restaurante;
	
	public Principal() {
		this.restaurante = new Restaurante();
		cargarArchivos();
		imprimirMenu();
		nuevoPedido();
		consultarPedido();
	}
	
	private void consultarPedido() {
		this.restaurante.consultarPedido(0);
	}

	public void cargarArchivos() {
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		File ingredientes = new File("data/ingredientes.txt");
		try {
			this.restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
			
		} catch (NumberFormatException | HamburguesaException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirMenu() {
		System.out.println("Bienvenido!");
		System.out.println("Productos Básicos:");
		for (ProductoMenu p: this.restaurante.getMenuBase()) {
			System.out.println(p.toString());
		}
		System.out.println("\nCombos:");
		for (Combo c: this.restaurante.getMenuCombos()) {
			System.out.println(c.toString());
		}
	}
	
	public void nuevoPedido(){
		try {
			this.restaurante.iniciarPedido("Santiago", "SD_307");
			ProductoAjustado producto = new ProductoAjustado(this.restaurante.getMenuBase().get(0));
			for (int i = 0; i < 3; i++) {
				producto.agregarIngrediente(this.restaurante.getIngredientes().get(i));
			}
			for (int i = 3; i < 6; i++) {
				producto.eliminarIngrediente(this.restaurante.getIngredientes().get(i));
			}
			this.restaurante.getPedidoEnCurso().agregarProducto(producto);
			this.restaurante.cerrarYGuardarPedido();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Principal();
	}
}

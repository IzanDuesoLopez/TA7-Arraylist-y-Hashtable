package ejercicio4;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class EjercicioCuatroApp {

	public static void main(String[] args) {

		Hashtable<String, Double> articulosPrecio = new Hashtable<>();
		articulosPrecio = inicializarPrecios(); // Inicializamos los precios de los artículos
		
		Hashtable<String, Integer> articulosCantidad = new Hashtable<>();
		articulosCantidad = inicializarCantidad(); // Inicializamos el stock de cada artículo
		
		Hashtable<String, Integer> articulosIVA = new Hashtable<>();
		articulosIVA = inicializarIVA(); // Inicializamos el IVA que tiene cada artículo
		
		// Método que pasadas 3 tablas realiza la funcionalidad completa para comprar artículos en la tienda
		comprarArticulos(articulosPrecio, articulosCantidad, articulosIVA); 
	}
	
	// Método para comprar artículos
	public static void comprarArticulos(Hashtable<String, Double> articulosPrecio, 
			Hashtable<String, Integer> articulosCantidad, Hashtable<String, Integer> articulosIVA) {
		
		String listaComprar = " - A continuación escribe el nombre del artículo que deseas comprar - \n\n";
		String comprobarStockDespuesDeCompra = " - Stock después del paso por caja - \n\n";
		
		String lista = mostrarLista(articulosPrecio, articulosCantidad, articulosIVA); // Mostramos la lista y la guardamos en la String lista
		
		String opcionCompra = JOptionPane.showInputDialog(listaComprar + lista); // Pedimos al usuario que introduzca que artículo desea comprar
		
		pagarCarrito(opcionCompra, articulosPrecio, articulosCantidad, articulosIVA); // Método que paga el carrito de la compra
		
		lista = mostrarLista(articulosPrecio, articulosCantidad, articulosIVA);
		
		JOptionPane.showMessageDialog(null, comprobarStockDespuesDeCompra + lista); // Mostramos la lista de nuevo con el stock modificado en caso de haber comprado
	}
	
	// Método para pagar el carrito y mostrar por pantalla los datos referentes al pago
	public static void pagarCarrito(String articulo, Hashtable<String, Double> articulosPrecio, Hashtable<String, Integer> articulosCantidad, 
			Hashtable<String, Integer> articulosIVA) {
		// Pedimos al usuario que introduzca la cantidad de dinero que va a pagar
		
		double precio = articulosPrecio.get(articulo); // Obtenemos el precio del artículo
		double IVA = articulosIVA.get(articulo); // Obtenemos el IVA del artículo
		IVA = IVA / 100; 
		
		
		// Preguntamos al usuario que cantidad de artículos desea comprar
		String cantidadProductos = JOptionPane.showInputDialog("Disponemos de " + articulosCantidad.get(articulo) + " " + articulo + 
				"\n ¿Qué cantidad deseas comprar?\n\n");
		int cantidadProductosEntero = Integer.parseInt(cantidadProductos);
		
		// Si introduce una cantidad no válida
		while(cantidadProductosEntero > articulosCantidad.get(articulo)) {
			cantidadProductos = JOptionPane.showInputDialog("¡No hay suficiente stock!\nDisponemos de " + articulosCantidad.get(articulo) + " " + articulo + 
					"\n ¿Qué cantidad deseas comprar?\n\n");
			cantidadProductosEntero = Integer.parseInt(cantidadProductos);
		}
		
		double precioBruto = precio * cantidadProductosEntero; // Calculamos el precio bruto de los artículos
		double precioConIva = (precioBruto + (precioBruto * IVA)); // Calculamos el precio con IVA de los artículos
		
		
		// Pedimos al usuario que pague
		String dineroPagado = JOptionPane.showInputDialog(
						"Introduce a continuación la cantidad de dinero que vas a pagar: \n" + "> Precio bruto: "
						+ precioBruto + "€\n> IVA: " + String.format("%.2f", (precioBruto * IVA))
						+ "€ \n> Precio total: " + String.format("%.2f", precioConIva) + "€\n");
		
		boolean clienteHaPagado = false; // En este booleano guardaremos si el usuario ha pagado o no
		double dineroPagadoDecimal = Double.parseDouble(dineroPagado); 
		
		
		// Controlamos los diferentes casos dependiendo de la cantidad de dinero que haya pagado y el precio de los artículos
		if(dineroPagadoDecimal < precioConIva) {
			JOptionPane.showMessageDialog(null, "Pago insuficiente. Aún deberías pagar " + String.format("%.2f", (precioConIva - dineroPagadoDecimal)) + "€");
		} else if (dineroPagadoDecimal == precioConIva) {
			clienteHaPagado = true;
			JOptionPane.showMessageDialog(null, "El pago ha sido exacto, no necesitas el cambio.");
		} else if (dineroPagadoDecimal > precioConIva) {
			clienteHaPagado = true;
			JOptionPane.showMessageDialog(null, "Compra realizada correctamente.\n\nCambio a devolver al cliente: " + String.format("%.2f", (dineroPagadoDecimal - precioConIva)) + "€");
		}
		
		// Si el cliente ha pagado
		if(clienteHaPagado) {
			int cantidadActual = articulosCantidad.get(articulo);
			cantidadActual -= cantidadProductosEntero; // Reducimos el stock de la base de datos
			articulosCantidad.replace(articulo, cantidadActual); // Actualizamos el stock
		}
	}
	
	// Método que retorna y muestra por pantalla la lista de artículos con sus características
	public static String mostrarLista(Hashtable<String, Double> articulosPrecio, 
		Hashtable<String, Integer> articulosCantidad, Hashtable<String, Integer> articulosIVA) {
		
		// Mediante los Enumeration formaremos una string con todos los artículos y sus características
		Enumeration<String> articulosPrecioKey = articulosPrecio.keys();
		Enumeration<Double> articulosPrecioElements = articulosPrecio.elements();

		Enumeration<String> articulosCantidadKey = articulosCantidad.keys();
		Enumeration<Integer> articulosCantidadElements = articulosCantidad.elements();

		Enumeration<String> articulosIVAKey = articulosIVA.keys();
		Enumeration<Integer> articulosIVAElements = articulosIVA.elements();
		
		String listaTotal = ""; // En la String listaTotal concatenaremos las características de los diferentes artículos

		// Mientras haya artículos, los concatenamos a la lista
		while (articulosPrecioKey.hasMoreElements()) {
			listaTotal += "> Artículo: " + articulosPrecioKey.nextElement() + " < [ Precio: "
					+ articulosPrecioElements.nextElement() + "€ ] " + " [ Stock: "
					+ articulosCantidadElements.nextElement() + " ] " + " [ IVA: " + articulosIVAElements.nextElement()
					+ "% ]\n";
		}

		listaTotal += "\n";
		
		return listaTotal; // Devolvemos la lista
	}
	
	// Método que añade a una HashTable auxiliar 10 artículos con su precio
	public static Hashtable<String, Double> inicializarPrecios(){
		Hashtable<String, Double> articulosPrecio = new Hashtable<>();
		
		articulosPrecio.put("Patata", 3.39);
		articulosPrecio.put("Lechuga", 4.59);
		articulosPrecio.put("Chocolate", 7.33);
		articulosPrecio.put("Pipas", 2.19);
		articulosPrecio.put("Cacahuetes", 1.99);
		articulosPrecio.put("Yogur", 1.59);
		articulosPrecio.put("Agua", 0.79);
		articulosPrecio.put("Aceite", 4.49);
		articulosPrecio.put("Pescado", 12.99);
		articulosPrecio.put("Carne", 6.99);
		
		return articulosPrecio;
	}
	
	// Método que añade a una HashTable auxiliar la cantidad que 10 artículos distintos
	public static Hashtable<String, Integer> inicializarCantidad(){
		Hashtable<String, Integer> articulosCantidad = new Hashtable<>();
		
		articulosCantidad.put("Patata", 20);
		articulosCantidad.put("Lechuga", 25);
		articulosCantidad.put("Chocolate", 15);
		articulosCantidad.put("Pipas", 30);
		articulosCantidad.put("Cacahuetes", 10);
		articulosCantidad.put("Yogur", 50);
		articulosCantidad.put("Agua", 100);
		articulosCantidad.put("Aceite", 45);
		articulosCantidad.put("Pescado", 33);
		articulosCantidad.put("Carne", 69);
		
		return articulosCantidad;
	}
	
	// Método que añade a una HashTable auxiliar el IVA que 10 artículos distintos
	public static Hashtable<String, Integer> inicializarIVA(){
		Hashtable<String, Integer> articulosIVA = new Hashtable<>();
		
		articulosIVA.put("Patata", 21);
		articulosIVA.put("Lechuga", 21);
		articulosIVA.put("Chocolate", 4);
		articulosIVA.put("Pipas", 21);
		articulosIVA.put("Cacahuetes", 4);
		articulosIVA.put("Yogur", 21);
		articulosIVA.put("Agua", 21);
		articulosIVA.put("Aceite", 21);
		articulosIVA.put("Pescado", 4);
		articulosIVA.put("Carne", 4);
		
		return articulosIVA;
	}

}

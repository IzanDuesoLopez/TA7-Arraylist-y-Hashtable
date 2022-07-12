package ejercicio2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class EjercicioDosApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArrayList<Integer> lista = new ArrayList<>(); // Definimos el ArrayList en el que guardaremos el número de artículos por carrito de la compra
		lista = introducirCantidadArticulos(sc);
		mostrarLista(lista, sc);
		
	}
	
	// Método que nos permite introducir datos en la ArrayList
	public static ArrayList<Integer> introducirCantidadArticulos(Scanner sc) {
		
		ArrayList<Integer> lista = new ArrayList<>();
		
		// Pedimos al usuario que introduzca cantidades de artículos por carrito de la
		// compra. Mientras no escriba -1 por consola le continuaremos pidiendo número de artículos por carrito.
		System.out.println("Introduce cantidades del carrito de compra, te pediremos cantidades hasta que lo desees. (Escribir -1 para salir)");
		int cantidades = sc.nextInt();
		if (cantidades > 0) {
			lista.add(cantidades);
		}

		// Mientras el usuario siga introduciendo número de artículos
		while (cantidades > 0) {
			System.out.println("Introduce la cantidad de elementos del siguiente carrito de la compra: (-1 para salir)");
			cantidades = sc.nextInt();
			if (cantidades > 0) {
				lista.add(cantidades);
			}
		}
		
		return lista;
	}
	
	public static void mostrarLista(ArrayList<Integer> lista, Scanner sc) {
		final double IVA = 0.21;
		final int PRECIO_ARTICULO = 4; // Asumiremos que todos los artículos tienen el mismo precio
		
		int contador = 1; // Contador para mostrar el número del artículo
		// Si la lista no está vacía
		if (!lista.isEmpty()) {
			Iterator<Integer> it = lista.iterator(); // Método iterator para recorrer el ArrayList
			int numElementosCarrito;
			double precio, precioConIva;
			double cantidadPagada = 0;

			while (it.hasNext()) { // Mientras la lista contenga elementos
				numElementosCarrito = it.next(); // Asignamos a la variable numElementosCarrito el número de elementos
				System.out.println("\nCarrito de la compra " + contador + ":");
				System.out.println("IVA Aplicado: " + (IVA * 100) + "%"); // Mostramos el IVA por consola

				precio = numElementosCarrito * PRECIO_ARTICULO; // El precio será = al número de elementos multiplicado por el precio de los artículos
				precioConIva = (precio + (precio * IVA)); // Sumamos el IVA al precio bruto y lo guardamos
				
				// Mostramos la información por consola
				System.out.println("Precio bruto: " + precio + "€" +" Precio mas IVA: " + precioConIva + "€");
				System.out.println("Número de artículos comprados: " + numElementosCarrito);
				System.out.println("Tienes que pagar la lista de la compra por un total de " + precioConIva + "€");
				
				pagarCarrito(sc, cantidadPagada, precioConIva); // Llámamos al método para pagar el carrito
				
				contador++;
			}

		// Si la lista está vacía
		} else {
			System.out.println("¡No hay carritos de la compra!");
		}
	}
	
	// Método para pagar el carrito y mostrar por pantalla los datos referentes al pago
	public static void pagarCarrito(Scanner sc, double cantidadPagada, double precioConIva) {
		// Pedimos al usuario que introduzca la cantidad de dinero que va a pagar
		System.out.println("Introduce a continuación la cantidad de dinero que vas a pagar: ");
		cantidadPagada = sc.nextDouble();
		System.out.println("Cantidad pagada: " + cantidadPagada + "€");

		// Controlamos los diferentes casos dependiendo de la cantidad de dinero que haya pagado y el precio de los artículos
		if (cantidadPagada < precioConIva) {
			// Utilizamos el String.format para únicamente permitir que se muestren un máximo de 2 decimales
			System.out.println("Pago insuficiente. Aún deberías pagar " + String.format("%.2f", (precioConIva - cantidadPagada)) + "€");
		} else if (cantidadPagada == precioConIva) {
			System.out.println("El pago ha sido exacto, no necesitas el cambio.");
		} else if (cantidadPagada > precioConIva) {
			System.out.println("Cambio a devolver al cliente: " + String.format("%.2f", (cantidadPagada - precioConIva)) + "€");
		}
	}

}

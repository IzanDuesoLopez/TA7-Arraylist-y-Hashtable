package ejercicio3;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class EjercicioTresApp {

	public static void main(String[] args) {

		final int NUM_ARTICULOS = 10;
		String nombre;
		
		Hashtable<String, Integer> articulos = new Hashtable<>(); // Definimos la Hashtable para los artículos
		articulos = introducirArticulos(NUM_ARTICULOS); // Introducimos los artículos en la HashTable con el método introducirArticulos
		
		// Dejamos al usuario que elija la opción que desee del menú
		int opcionMenu = menuUsuario();
		funcionalidadMenu(opcionMenu, articulos);
	}
	
	// Método que utilizamos para introducir artículos en la HashTable. Este método devuelve la tabla con los artículos ya introducidos
	public static Hashtable<String, Integer> introducirArticulos(final int NUM_ARTICULOS){
		Hashtable<String, Integer> articulos = new Hashtable<>();
		
		String nombre;
		int precio;
		
		// En este bucle FOR pedimos al usuario los artículos con su respectivo precio
		for (int i = 0; i < NUM_ARTICULOS; i++) {
			nombre = JOptionPane.showInputDialog("Nombre del artículo " + i + ":");
			precio = Integer.parseInt(JOptionPane.showInputDialog("Precio del artículo " + i + ": (Número entero)"));
			articulos.put(nombre, precio);
		}
		
		return articulos; // Devolvemos la HashTable con los artículos ya introducidos por el usuario
	}
	
	// Método que nos permite elegir la opción del menú que deseemos
	public static int menuUsuario() {
		// En el menú dejaremos elegir al usuario si desea consultar la información
		// de un producto en específico o listar en consola toda la lista de
		// productos que se encuentra en la base de datos.
		int opcionMenu = Integer.parseInt(JOptionPane.showInputDialog("Bienvenido/a al menú de la base de datos. "
				+ "A continuación escribe el número referente a la opción que deseas realizar:\n(1) Consultar información \n"
				+ "(2) Listar toda la información en el terminal"));
		while (opcionMenu != 1 && opcionMenu != 2) {
			opcionMenu = Integer.parseInt(JOptionPane.showInputDialog("¡Opción introducida incorrecta!. "
					+ "A continuación escribe el número referente a la opción que deseas realizar:\n(1) Consultar información \n"
					+ "(2) Listar toda la información en el terminal"));
		}
		
		return opcionMenu;
	}
	
	// Método que permite mostrar por interfaz y consola los elementos de la HashTable
	public static void funcionalidadMenu(int opcionMenu, Hashtable<String, Integer> articulos) {
		String nombre;
		
		// Buscamos el artículo por ID y lo mostramos por interfaz
		if (opcionMenu == 1) {

			nombre = JOptionPane.showInputDialog("Introduce el nombre del artículo que deseas buscar: ");
			if (articulos.get(nombre) != null) {
				JOptionPane.showMessageDialog(null,"El precio del artículo " + nombre + " = " + articulos.get(nombre) + "€");
			} else {
				JOptionPane.showMessageDialog(null, "¡El artículo " + nombre + " no existe en la base de datos!");
			}
			
		// Mostramos por consola todos los artículos disponibles en la base de datos
		} else if (opcionMenu == 2) {
			Enumeration<String> articulosKey = articulos.keys();
			Enumeration<Integer> articulosPrecio = articulos.elements();
			
			while (articulosKey.hasMoreElements()) {
				System.out.println("[Producto: " + articulosKey.nextElement() + "] [Precio: " + articulosPrecio.nextElement() + "€]");

			}
		}
	}

}

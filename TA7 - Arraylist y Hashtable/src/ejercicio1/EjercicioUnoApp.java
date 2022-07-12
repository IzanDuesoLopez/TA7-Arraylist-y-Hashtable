package ejercicio1;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class EjercicioUnoApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Solicitamos al usuario que nos facilite un número de alumnos
		System.out.println("Introduce el número de alumnos pertenecientes al curso de programación: ");
		int numAlumnos = sc.nextInt();
		// Realizamos una pequeña comprobación para asegurarnos de que al menos introduce 1 alumno. De no
		// ser así le volveremos a pedir que introduzca el número de alumnos.
		while(numAlumnos < 1) {
			System.out.println("Tienes que introducir al menos 1 alumno. Introduce el número de alumnos pertenecientes al curso de programación: ");
			numAlumnos = sc.nextInt();
		}
		
		Hashtable<String, Float> mediaAlumnos = new Hashtable<>(); // Definimos la estructura de datos para almacenar a los alumnos
		
		mediaAlumnos = introducirDatosAlumnos(sc, numAlumnos); // Llamamos al método que nos permite introducir datos y lo asignamos a la hashtable
		
		sc.close(); // Cerramos el Scanner
		
		mostrarAlumnos(mediaAlumnos); // Llamamos al método que muestra a los alumnos por consola

	}
	
	// Método que retorna una HashTable con los datos de los alumnos introducidos
	public static Hashtable<String, Float> introducirDatosAlumnos(Scanner sc, int numAlumnos){
		
		Hashtable<String, Float> mediaAlumnos = new Hashtable<>(); // Definimos la estructura de datos para almacenar a los alumnos
		String nombreAlumno;
		float notaUno, notaDos, notaTres;
		float resultadoMedia;
		
		// Bucle FOR para introducir los datos de los alumnos indicados por el usuario anteriormente
		for(int i = 0; i < numAlumnos; i++) {
			System.out.println("Introduce el nombre del alumno/a " + i + ".");
			sc.nextLine(); // Hacemos un nextLine para que el scanner no se salte el input del alumno
			nombreAlumno = sc.nextLine(); // Pedimos el nombre del alumno
			System.out.println("Introduce la nota 1: ");
			notaUno = sc.nextFloat(); // Pedimos la primera nota
			System.out.println("Introduce la nota 2: ");
			notaDos = sc.nextFloat(); // Pedimos la tercera nota
			System.out.println("Introduce la nota 3: ");
			notaTres = sc.nextFloat(); // Pedimos la cuarta nota
			
			resultadoMedia = ((notaUno+notaDos+notaTres) / 3); // Calculamos la media y la almacenamos en la variable resultadoMedia
			
			// Introducimos en la estructura de datos la key y el contenido vinculado a la key, que sería el nombre del alumno y
			// su nota media. La nota media la pasaremos a string para poder almacenarla correctamente.
			mediaAlumnos.put(nombreAlumno, resultadoMedia); 
		}
		
		return mediaAlumnos;
	}
	
	// Método que imprime los alumnos con sus respectivas medias por pantalla
	public static void mostrarAlumnos(Hashtable<String, Float> mediaAlumnos) {
		Enumeration<String> keysAlumnos = mediaAlumnos.keys(); // En este enumeration guardamos las keys (Nombre de los alumnos)
		Enumeration<Float> contenidoAlumnos = mediaAlumnos.elements(); // En este enumeration guardamos las notas medias de los alumnos
		
		// Mientras haya alumnos, imprimiremos por pantalla su nombre y nota media
		while(keysAlumnos.hasMoreElements()) {
			System.out.println("Nota media del alumno/a " + keysAlumnos.nextElement() + " = " + String.format("%.2f", contenidoAlumnos.nextElement()));
		}
	}
}

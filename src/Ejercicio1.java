import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/*Crea una aplicación que calcule la nota media de los alumnos pertenecientes al 
 * curso de programación. Una vez calculada la nota media se guardará esta 
 * información en un diccionario de datos que almacene la nota media de cada alumno. 
 * Todos estos datos se han de proporcionar por pantalla.*/

public class Ejercicio1 {

	boolean masAlumnos = true;
	boolean añadirNotas = true;
	String input;
	double media = 0;

	String[] nombre = new String[3];
	ArrayList<Double> notas = new ArrayList<Double>();
	Hashtable<String[], Double> indexAlumnos = new Hashtable<String[], Double>();

	// Inicializamos el scanner
	Scanner sc = new Scanner(System.in);

	// Creamos un metodo general para el calculo del Area
	public void notaAlumno() {

		// Imprimimos mensaje de bienvenida y explicación de la app
		System.out.println("Bienvenido a la app Calcule y almacene la nota media de alumnos!");

		while (masAlumnos) {

			System.out.println("Introduzca el nombre del alumno: ");
			nombre[0] = sc.nextLine();
			System.out.println("Introduzca el primer apellido del alumno: ");
			nombre[1] = sc.nextLine();
			System.out.println("Introduzca el segon apellido del alumno: ");
			nombre[2] = sc.nextLine();

			while (añadirNotas) {

				System.out.println("Introduzca la nota del alumno: ");
				notas.add(Double.parseDouble(sc.nextLine()));
				System.out.println("¿Deseas añadir otra nota? (si o no)");
				input = sc.nextLine().toLowerCase();
				switch (input) {
				case "si":
					añadirNotas = true;
					break;

				case "no":
					añadirNotas = false;
					break;
				default:
					System.out.println("No has introducido un valor correcto.");
				}

			}

			calcularMedia(notas);
			insertarDatos(nombre, media);
			
			Set<String[]> indexNombres = indexAlumnos.keySet();

			for (String[] indexNombre : indexNombres) {

				System.out.println("Has añadido al alumno " + indexNombre[0] + " " + indexNombre[1] + " " + indexNombre[2]
						+ ", la nota média de " + indexAlumnos.get(nombre) + ".");
			}

			System.out.println("¿Deseas añadir otro alumno? (si o no)");
			input = sc.nextLine().toLowerCase();
			switch (input) {
			case "si":
				añadirNotas = true;
				break;

			case "no":
				añadirNotas = false;
				break;
			default:
				System.out.println("No has introducido un valor correcto.");
			}

		}

		// Cerramos el scanner
		sc.close();

	}

	public double calcularMedia(ArrayList<Double> notas) {
		media = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

		return media;
	}

	public void insertarDatos(String[] nombreAlumno, double nota) {
		indexAlumnos.put(nombreAlumno, nota);
		

	}

}

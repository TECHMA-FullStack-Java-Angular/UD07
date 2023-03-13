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
	boolean anadirNotas = true;
	String input;
	double media = 0;
	String dni;

	String[] nombre;
	ArrayList<Double> notas;
	Hashtable<String, String[]> indexAlumnos = new Hashtable<String, String[]>();

	// Inicializamos el scanner
	Scanner sc = new Scanner(System.in);

	// Creamos un metodo general para el calculo del Area
	public void notaAlumno() {

		// Imprimimos mensaje de bienvenida y explicación de la app
		System.out.println("Bienvenido a la app Calcule y almacene la nota media de alumnos!");

		while (masAlumnos) {

			nombre = new String[4];
			System.out.println("Introduzca el nombre del alumno: ");
			nombre[0] = sc.nextLine();
			System.out.println("Introduzca el primer apellido del alumno: ");
			nombre[1] = sc.nextLine();
			System.out.println("Introduzca el segon apellido del alumno: ");
			nombre[2] = sc.nextLine();
			System.out.println("¿Introduzca el DNI del alumno: ");
			dni = sc.nextLine();

			while (anadirNotas) {
				

				notas = new ArrayList<Double>();
				System.out.println("Introduzca la nota del alumno: ");
				notas.add(Double.parseDouble(sc.nextLine()));
				System.out.println("¿Deseas añadir otra nota? (si o no)");
				input = sc.nextLine().toLowerCase();
				switch (input) {
				case "si":
					anadirNotas = true;
					break;

				case "no":
					anadirNotas = false;
					break;
				default:
					System.out.println("No has introducido un valor correcto.");
				}

			}

			nombre[3] = Double.toString(calcularMedia(notas));

			insertarDatos(dni, nombre);

			System.out.println("¿Deseas añadir otro alumno? (si o no)");
			input = sc.nextLine().toLowerCase();
			switch (input) {
			case "si":
				masAlumnos = true;
				break;

			case "no":
				masAlumnos = false;
				break;
			default:
				System.out.println("No has introducido un valor correcto.");
			}
			
			mostrarDatos(indexAlumnos);

			

			// reinicia el bucle interno
			anadirNotas = true;

		}

		// Cerramos el scanner
		sc.close();

	}

	public double calcularMedia(ArrayList<Double> notas) {
		media = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

		return media;
	}

	public void insertarDatos(String dni, String[] datos) {

		indexAlumnos.put(dni, datos);

	}
	
	public void mostrarDatos(Hashtable<String, String[]> indexAlumnos) {
		for (String clave : indexAlumnos.keySet()) {
			String[] valor = indexAlumnos.get(clave);
			System.out.print("Datos alumno"+clave + ": ");
			for (String s : valor) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}

}

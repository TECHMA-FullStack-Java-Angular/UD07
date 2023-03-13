import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/*EJERCICIO 2:
 * Crea una aplicación que gestione el flujo de ventas de una caja de supermercado. 
 * El programa guardara las cantidades del carrito de compra dentro de una lista. 
 * Mostrará por pantalla la siguiente información:
 * 
 * - IVA aplicado (21% o 4%)
 * - precio total bruto y precio más IVA.
 * - numero de articulos comprados.
 * - Cantidad pagada.
 * - Cambio a devolver al cliente.*/

public class Ejercicio2 {

	// Variables necesarias

	ArrayList<Integer> cantidad = new ArrayList<Integer>();
	ArrayList<Double> totalPagar = new ArrayList<Double>();
	ArrayList<Double> totalPagarIva = new ArrayList<Double>();

	boolean masProductos = true;
	String input;
	double precio;
	double iva = 0;
	boolean ivaIncorrecto = false;
	double totalProducto;
	double totalProductoIva;
	double totalCompra;
	String producto;
	int cantidadProducto;
	double dinero;
	double cambio;

	// Inicializamos el scanner
	Scanner sc = new Scanner(System.in);

	public void flujoCaja() {

		System.out.println("Bienvenido a la APP Flujo de Caja");

		// Abrimos el bucle de añadir productos
		while (masProductos) {

			System.out.println("Introduzca el producto comprado: ");

			producto = sc.nextLine();

			System.out.println("Introduzca ela cantidad comprada de este producto: ");
			cantidadProducto = Integer.parseInt(sc.nextLine());

			// guaradamos el valor en una lista

			cantidad.add(cantidadProducto);

			System.out.println("Introduzca el precio del producto: ");
			precio = Double.parseDouble(sc.nextLine());

			System.out.println("Introduzca el IVA aplicado a este producto (21 o 4): ");
			String respuesta = sc.nextLine().toLowerCase();

			do {
				switch (respuesta) {
				case "21":
					iva = 0.21;
					System.out.println("Producto con IVA de 21%");
					break;
				case "4":
					iva = 0.04;
					System.out.println("Producto con IVA de 4%");
					break;
				default:
					System.out.println("El valor introducido no es correcto.");
					ivaIncorrecto = true;
				}
			} while (ivaIncorrecto);
			ivaIncorrecto = false;

			// Metodo para calcular el precio de la primera entrada sin IVA

			totalProducto = precioProducto(precio, cantidadProducto);

			// guardamos en una lista

			totalPagar.add(totalProducto);

			// Metodo para calcular el precio de la primera entrada con IVA

			totalProductoIva = precioProductoIva(precio, producto, cantidadProducto, iva);

			// Guardamos en una lista

			totalPagarIva.add(totalProductoIva);

			System.out.println("¿Deseas añadir otro producto? (si o no)");
			input = sc.nextLine().toLowerCase();
			switch (input) {
			case "si":
				masProductos = true;
				break;

			case "no":
				masProductos = false;
				break;
			default:
				System.out.println("No has introducido un valor correcto.");
			}

			// Cerramos el bucle de añadir productos
		}

		// Metodo para controlar la cantidad de productos comprados
		cantidadProducto(cantidad);

		System.out.println("Total compra sin IVA: " + valorAPagar(totalPagar));
		totalCompra = valorAPagar(totalPagarIva);
		System.out.println("El total a pagar con IVA es " + totalCompra);

		System.out.println("Indroduzca el dinero recibido: ");
		dinero = Double.parseDouble(sc.nextLine());
		System.out.println("Cantidad entregada: " + dinero);

		cambio = calcularCambio(totalCompra, dinero);

		// Cerramos el scanner
		sc.close();

	}

	public double precioProducto(double precio, int cantidad) {

		return precio * cantidad;

	}

	public double precioProductoIva(double precio, String producto, int cantidad, double iva) {

		return (precio * cantidad) + (precio * cantidad) * iva;

	}

	public void cantidadProducto(ArrayList<Integer> cantidad) {

		int total = 0;

		for (Integer unidad : cantidad) {
			total += unidad;
		}

		System.out.println("Se han comprado un total de  " + total + " productos.");

	}

	public double valorAPagar(ArrayList<Double> unidad) {

		double total = 0;

		for (double u : unidad) {
			total += u;
		}

		return total;
	}

	public double calcularCambio(double total, double dinero) {
		double cambio = dinero - total;
		System.out.println("Cambio a devolcer: " + cambio);
		return cambio;
	}

}

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

	//Lista de cantidades compradas
	ArrayList<Integer> cantidad = new ArrayList<Integer>();
	
	//Lista de precios sin IVA
	ArrayList<Double> totalPagar = new ArrayList<Double>();
	
	//Lista de precios con IVA
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

			// guaradamos la cantidad comprada en la lista de cantidades compradas
			cantidad.add(cantidadProducto);
			
			//Solicitamos el precio por unidad del producto comprado y guardamos el valor en una variable
			System.out.println("Introduzca el precio del producto: ");
			precio = Double.parseDouble(sc.nextLine());

			//Solicitamos que indique el tipo de IVA aplicado a este producto
			System.out.println("Introduzca el IVA aplicado a este producto (21 o 4): ");
			String respuesta = sc.nextLine().toLowerCase();

			//Imprimimos el IVA aplicable y inicializamos la variable iva con el valor indicado
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

			// Llamamos al Metodo para calcular el precio de la primera entrada sin IVA y 
			//guardamos su valor en una variable
			totalProducto = precioProducto(precio, cantidadProducto);

			// Guardamos el precio del produco en la lista precios sin IVA
			totalPagar.add(totalProducto);

			// Llamamos al metodo para calcular el precio de la primera entrada con IVA y
			//guardamos su valor en una variable
			totalProductoIva = precioProductoIva(precio, producto, cantidadProducto, iva);

			// Guardamos el precio del produco en la lista precios CON IVA
			totalPagarIva.add(totalProductoIva);
			
			//Preguntamos si desea añadir más productos
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

		// Ahora que ya tenemos todos los productos añadidos al sistema, llamamos al metodo 
		//para calcular la cantidad de productos comprados
		cantidadProducto(cantidad);

		//Imprimimos el total a pagar en bruto llamando al metodo valorAPagar()
		System.out.println("Total compra sin IVA: " + valorAPagar(totalPagar));
		
		//Llamamos al metodo valorAPagar() y asignamos un valor a totalCompra (IVA).
		totalCompra = valorAPagar(totalPagarIva);
		//Imprimimos el total a pagar con IVA
		System.out.println("El total a pagar con IVA es " + totalCompra);

		//Solicitamos que introduzca la cantidad de dinero recibida y lo guardamos en una variable
		System.out.println("Indroduzca el dinero recibido: ");
		dinero = Double.parseDouble(sc.nextLine());
		
		//Imprimimos la cantidad de dinero recibida
		System.out.println("Cantidad entregada: " + dinero);

		//Llamamos al metodo calcularCambio y guardamos el valor en una variable
		cambio = calcularCambio(totalCompra, dinero);

		// Cerramos el scanner
		sc.close();

	}

	//Calculo precio sin IVA
	public double precioProducto(double precio, int cantidad) {

		return precio * cantidad;

	}

	//Calculo precio con IVA
	public double precioProductoIva(double precio, String producto, int cantidad, double iva) {

		return (precio * cantidad) + (precio * cantidad) * iva;

	}

	//Suma de valores introducidos en la lista de cantidades
	public void cantidadProducto(ArrayList<Integer> cantidad) {

		int total = 0;

		for (Integer unidad : cantidad) {
			total += unidad;
		}

		System.out.println("Se han comprado un total de  " + total + " productos.");

	}

	//Suma de valores introducidos en lista de precios y retorna el valor total
	public double valorAPagar(ArrayList<Double> unidad) {

		double total = 0;

		for (double u : unidad) {
			total += u;
		}

		return total;
	}

	//Calculo del cambio a retornar al cliente y retorna el valor del cambio
	public double calcularCambio(double total, double dinero) {
		double cambio = dinero - total;
		System.out.println("Cambio a devolcer: " + cambio);
		return cambio;
	}

}

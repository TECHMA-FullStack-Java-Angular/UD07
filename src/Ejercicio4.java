import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

/*EJERCICIO 4:
 * 
 * Combina los metodos generados en las actividades 2 y 3 creando una aplicación que gestione 
 * ventas y control de stock en una misma interfaz. Utiliza para ello las estructuras de datos
 *  que creas conveniente.*/

public class Ejercicio4 {

	// Variables necesarias ejercicio 2 y 3

	// Lista de cantidades compradas
	ArrayList<Integer> cantidad = new ArrayList<Integer>();

	// Lista de precios sin IVA
	ArrayList<Double> totalPagar = new ArrayList<Double>();

	// Lista de precios con IVA
	ArrayList<Double> totalPagarIva = new ArrayList<Double>();

	// Lista multidimensional para añadir productos y la cantidad de cada;
	ArrayList<String[][]> articulos = new ArrayList<String[][]>();

	// Lista multidimensional para añadir productos y la cantidad de cada;
	ArrayList<String[]> listaCompra = new ArrayList<String[]>();

	// Diccionario de datos (articulo: precio)
	Hashtable<String, Double> catalogoPrecio = new Hashtable<String, Double>();

	// String inicial para introducir articulos a la lista
	String[][] articulosBase = new String[2][10];

	boolean masProductos = true;
	int input;
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
	
	

	public void gestiontotal() {

		// Añadimos los los articulos iniciales a la Array multidimencional

		articulosBase[0][0] = "avena";
		articulosBase[1][0] = "100";
		articulosBase[0][1] = "leche";
		articulosBase[1][1] = "100";
		articulosBase[0][2] = "pan";
		articulosBase[1][2] = "100";
		articulosBase[0][3] = "arroz";
		articulosBase[1][3] = "100";
		articulosBase[0][4] = "chocolate";
		articulosBase[1][4] = "100";
		articulosBase[0][5] = "manzana";
		articulosBase[1][5] = "100";
		articulosBase[0][6] = "harina";
		articulosBase[1][6] = "100";
		articulosBase[0][7] = "pollo";
		articulosBase[1][7] = "100";
		articulosBase[0][8] = "shampoo";
		articulosBase[1][8] = "100";
		articulosBase[0][9] = "jamon";
		articulosBase[1][9] = "100";

		// Añadimos el array a la lista
		articulos.add(articulosBase);

		// añadimos los articulos iniciales al catalogo de precios

		catalogoPrecio.put("avena", 0.9);
		catalogoPrecio.put("leche", 1.25);
		catalogoPrecio.put("pan", 1.5);
		catalogoPrecio.put("arroz", 0.95);
		catalogoPrecio.put("chocolate", 2.5);
		catalogoPrecio.put("manzana", 0.89);
		catalogoPrecio.put("harina", 1.09);
		catalogoPrecio.put("pollo", 6.90);
		catalogoPrecio.put("shampoo", 4.99);
		catalogoPrecio.put("jamon", 8.99);

		JOptionPane.showMessageDialog(null, "Bienvenido a la APP Gestiona tu tienda!");

		int stockApp = JOptionPane.showConfirmDialog(null, "¿Deseas realizar gestion de stock?");

		if (stockApp == 0) {

			// Abrimos gestion de stock con mensaje de bienvenida
			JOptionPane.showMessageDialog(null, "Bienvenido a la APP control de Stock!");

			// Preguntamos si desea introducir nuevo articulo y guardamos el valor en una
			// variable
			int nuevoArticulo = JOptionPane.showConfirmDialog(null, "¿Deseas introducir nuevo articulo?");

			// En caso positivo, pedimos para la introduccion de nombre, cantidad, precio
			// sin IVA y guardamos
			// los valores en variables.
			if (nuevoArticulo == 0) {
				String articulo = JOptionPane.showInputDialog("Introduzca el nombre del artículo").toLowerCase();
				String cantidad = JOptionPane.showInputDialog("Introduzca la cantidad a añadir en stock");
				String precio = JOptionPane.showInputDialog("Introduzca el precio sin IVA de la unidad.");
				double precioSt = Double.parseDouble(precio);

				// Añadimos los valores nombre y precio en el diccionario de datos(catalogo
				// precio)
				catalogoPrecio.put(articulo, precioSt);

				// Añadimos los valores nombre y cantidad en la lista de productos
				String[][] nuevoPorducto = { { articulo }, { cantidad } };
				articulos.add(nuevoPorducto);

			}

			// Preguntamos si desea alterar la cantidad en stock de algun producto
			int stock = JOptionPane.showConfirmDialog(null, "¿Deseas añadir de productos al stock?");

			// En caso positivo solicitamos que ingrese el nombre del producto a alterar y
			// la nueva cantidad en stock
			if (stock == 0) {
				String articuloBuscado = JOptionPane
						.showInputDialog("Introduzca el nombre del artículo")
						.toLowerCase();
				String cantidad = JOptionPane.showInputDialog("Introduzca la cantidad a añadir");

				// Buscar articulo en lista de productos (.equals()) y alterar su
				// cantidad(.valueOf())
				for (String[][] array : articulos) {
					for (int i = 0; i < array.length; i++) {
						for (int j = 0; j < array[i].length; j++) {
							if (array[i][j].equals(articuloBuscado)) {
								int cantidadInicial = Integer.parseInt(array[1][j]);
								array[1][j] = String.valueOf(cantidadInicial + Integer.parseInt(cantidad));
							}
						}
					}
				}

			}

			
			// Preguntamos si desea visualizar la lista de informacion Stock
			int infoStock = JOptionPane.showConfirmDialog(null, "¿Deseas visualizar el stock de productos?");

			// En caso positivo, imprimimos la lista de productos
			if (infoStock == 0) {

				for (String[][] array : articulos) {
					for (int i = 0; i < array.length; i++) {
						for (int j = 0; j < array[i].length; j++) {
							System.out.printf("%-15s", array[i][j]);
						}
						System.out.println();
					}
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				}
			}

			// Preguntamos si desea visualizar la lista de información precios (diccionario)
			int infoCatalogo = JOptionPane.showConfirmDialog(null, "¿Deseas visualizar el precio de los productos?");

			if (infoCatalogo == 0) {
				catalogoPrecio.forEach((producto, precio) -> System.out.print(producto + ": \t" + precio + " €\t"));
				System.out.println();
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			}

			// Cerramos gestion de stocks
		} else {
			int CajaApp = JOptionPane.showConfirmDialog(null, "¿Deseas abrir caja?");

			if (CajaApp == 0) {

				JOptionPane.showMessageDialog(null, "Bienvenido a la Gestión de caja");
				
				// Preguntamos si desea consultar información concreta del diccionario
				// (catalogoPrecio)
				int busqueda = 	JOptionPane.showConfirmDialog(null, "¿Deseas buscar el precio de algun producto?");


				// En caso positivo, solicitamos que introduzca el nombre del articulo a buscar
				// (index)
				while (busqueda == 0) {
					
					String articulo = JOptionPane.showInputDialog("Introduzca el nombre del artículo").toLowerCase();
					Double precio = catalogoPrecio.get(articulo);

					// Si lo encontramos (precio existe) imprimimos el valor
					if (precio != null) {
						JOptionPane.showMessageDialog(null, "El precio de " + articulo + " sin IVA es " + precio + " €");

					} else {
						JOptionPane.showMessageDialog(null, "No se encontró el precio del articulo en el catálogo.");

					}
					int seguirBusqueda = 	JOptionPane.showConfirmDialog(null, "¿Deseas buscar el precio de otro producto?");
						if(seguirBusqueda == 0) {
							continue;
						}else {
							break;
						}
				}
				
				int abrirCaja =JOptionPane.showConfirmDialog(null, "¿Deseas intoducir uma compra?");
				
				if(abrirCaja ==0) {
				
				// Abrimos el bucle de añadir productos
				while (masProductos) {

					String producto = JOptionPane.showInputDialog("Introduzca el producto").toLowerCase();
					Double precio = catalogoPrecio.get(producto);

					// Si lo encontramos (precio existe) imprimimos el valor
					if (precio == null) {
						JOptionPane.showMessageDialog(null, "No se encontró el precio del articulo en el catálogo.");

						// Preguntamos si desea visualizar la lista de informacion Stock
						int infoNuevoProducto = JOptionPane.showConfirmDialog(null,
								"¿Deseas añadir el producto al catálogo?");

						// En caso positivo, imprimimos la lista de productos
						if (infoNuevoProducto == 0) {
							precio = Double.parseDouble(
									JOptionPane.showInputDialog(null, "Introduzca el precio del producto"));
							catalogoPrecio.put(producto, precio);

						} else {
							precio = Double.parseDouble(
									JOptionPane.showInputDialog(null, "Introduzca el precio del producto"));
						}
					}

					String inputCantidad = JOptionPane.showInputDialog(null,
							"Introduzca ela cantidad comprada de este producto");
					cantidadProducto = Integer.parseInt(inputCantidad);

					// guardamos la cantidad comprada en la lista de cantidades compradas
					cantidad.add(cantidadProducto);

					// Solicitamos que indique el tipo de IVA aplicado a este producto
					String respuesta = JOptionPane
							.showInputDialog(null, "Introduzca el IVA aplicado a este producto (21 o 4)").toLowerCase();

					// Imprimimos el IVA aplicable y inicializamos la variable iva con el valor
					// indicado
					do {
						switch (respuesta) {
						case "21":
							iva = 0.21;
							JOptionPane.showMessageDialog(null, "Producto con IVA de 21%");

							break;
						case "4":
							iva = 0.04;
							JOptionPane.showMessageDialog(null, "Producto con IVA de 4%");
							break;
						default:
							JOptionPane.showMessageDialog(null, "El valor introducido no es correcto.");
							ivaIncorrecto = true;
						}
					} while (ivaIncorrecto);
					ivaIncorrecto = false;

					// Llamamos al Metodo para calcular el precio de la primera entrada sin IVA y
					// guardamos su valor en una variable
					totalProducto = precioProducto(precio, cantidadProducto);

					// Guardamos el precio del produco en la lista precios sin IVA
					totalPagar.add(totalProducto);

					// Llamamos al metodo para calcular el precio de la primera entrada con IVA y
					// guardamos su valor en una variable
					totalProductoIva = precioProductoIva(precio, producto, cantidadProducto, iva);

					// Guardamos el precio del produco en la lista precios CON IVA
					totalPagarIva.add(totalProductoIva);

					// Creamos variables para guardar productos a añadir a lista de compra
					
					String articuloCant = producto + " X " + cantidad + " = " + totalProductoIva + " €";
					String[] productoComprado = { articuloCant };

					listaCompra.add(productoComprado);

					// Preguntamos si desea añadir más productos
					input = JOptionPane.showConfirmDialog(null, "¿Deseas añadir otro producto?");

					switch (input) {
					case 0:
						masProductos = true;
						break;

					case 1:
						masProductos = false;
						

						dinero = Double.parseDouble(JOptionPane.showInputDialog("Total con IVA: "
								+ valorAPagar(totalPagarIva) + " €." +"\nIntroduzca el dinero recibido"));
						cambio = calcularCambio(valorAPagar(totalPagarIva), dinero);

						JOptionPane.showMessageDialog(null,
								"Se han comprado un total de  " + cantidadProducto(cantidad) + " productos.\n"
										+ "Total sin IVA: " + valorAPagar(totalPagar) + " €" + "\nTotal con IVA: "
										+ valorAPagar(totalPagarIva) + " €" + "\nValor recibido: " + dinero + " €"
										+ "\nCambio a retornar: " + cambio + " €.");

						break;
					default:
						System.out.println("No has introducido un valor correcto.");
					}

					// Cerramos el bucle de añadir productos
				}
				

				}else {
					JOptionPane.showMessageDialog(null, "Gracias por confiar en nosotros.");
				}
				
				// Cerramos app Gestion compras
			} 

		}

	}

	// Calculo precio sin IVA
	public double precioProducto(double precio, int cantidad) {

		return precio * cantidad;

	}

	// Calculo precio con IVA
	public double precioProductoIva(double precio, String producto, int cantidad, double iva) {

		return (precio * cantidad) + (precio * cantidad) * iva;

	}

	// Suma de valores introducidos en la lista de cantidades
	public int cantidadProducto(ArrayList<Integer> cantidad) {

		int total = 0;

		for (Integer unidad : cantidad) {
			total += unidad;
		}

		return total;
	}

	// Suma de valores introducidos en lista de precios y retorna el valor total
	public double valorAPagar(ArrayList<Double> unidad) {

		double total = 0;

		for (double u : unidad) {
			total += u;
		}

		return total;
	}

	// Calculo del cambio a retornar al cliente y retorna el valor del cambio
	public double calcularCambio(double total, double dinero) {
		double cambio = dinero - total;
//			JOptionPane.showMessageDialog(null, "Cambio a devolcer: " + cambio);
		return cambio;
	}

}

import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/*EJERCICIO 3:
 * 
 * Crea una base de datos de 10 artículos para controlar el stock de productos de una 
 * tienda por medio de un diccionario de datos (articulo:precio). El usuario podrá 
 * añadir, por medio de interfaz visual artículos nuevos y cantidades de estos. El 
 * usuario podrá consultar la información almacenada en el diccionario referente a 
 * un artículo concreto e incluso listar toda la información en la terminal del 
 * programa.*/

public class Ejercicio3 {

	// Lista multidimensional para añadir productos y la cantidad de cada;
	ArrayList<String[][]> articulos = new ArrayList<String[][]>();

	//Diccionario de datos (articulo: precio)
	Hashtable<String, Double> catalogoPrecio = new Hashtable<String, Double>();

	//String inicial para introducir articulos a la lista
	String[][] articulosBase = new String[2][10];

	

	public void baseDatos() {

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

		//Añadimos el array a la lista
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
		
		
		//Una vez insertados los valores iniciales, activamos la app con un mensaje de bienvenida visual
		JOptionPane.showMessageDialog(null, "Bienvenido a la APP control de Stock!");

		//Preguntamos si desea introducir nuevo articulo y guardamos el valor en una variable
		int nuevoArticulo = JOptionPane.showConfirmDialog(null, "¿Deseas introducir nuevo articulo?");

		//En caso positivo, pedimos para la introduccion de nombre, cantidad, precio sin IVA y guardamos 
		//los valores en variables.
		if (nuevoArticulo == 0) {
			String articulo = JOptionPane.showInputDialog("Introduzca el nombre del artículo").toLowerCase();
			String cantidad = JOptionPane.showInputDialog("Introduzca la cantidad a añadir en stock");
			String precio = JOptionPane.showInputDialog("Introduzca el precio sin IVA de la unidad.");
			double precioSt = Double.parseDouble(precio);

			//Añadimos los valores nombre y precio en el diccionario de datos(catalogo precio)
			catalogoPrecio.put(articulo, precioSt);

			//Añadimos los valores nombre y cantidad en la lista de productos
			String[][] nuevoPorducto = { { articulo }, { cantidad } };
			articulos.add(nuevoPorducto);

		}
		
		//Preguntamos si desea alterar la cantidad en stock de algun producto
		int stock = JOptionPane.showConfirmDialog(null, "¿Deseas añadir de productos al stock?");

		//En caso positivo solicitamos que ingrese el nombre del producto a alterar y la nueva cantidad en stock
		if (stock == 0) {
			String articuloBuscado = JOptionPane
					.showInputDialog("Introduzca el nombre del artículo que deseas aumentar el stock").toLowerCase();
			String cantidad = JOptionPane.showInputDialog("Introduzca la cantidad a añadir a stock");

			// Buscar articulo en lista de productos (.equals()) y alterar su cantidad(.valueOf())
			for (String[][] array : articulos) {
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[i].length; j++) {
						if (array[i][j].equals(articuloBuscado)) {
							int cantidadInicial = Integer.parseInt(array[1][j]) ;
							array[1][j] = String.valueOf(cantidadInicial+Integer.parseInt(cantidad));
						}
					}
				}
			}

		}

		// Preguntamos si desea consultar información concreta del diccionario (catalogoPrecio)
		int busqueda = JOptionPane.showConfirmDialog(null, "¿Deseas buscar el precio de algun producto?");

		//En caso positivo, solicitamos que introduzca el nombre del articulo a buscar (index)
		if (busqueda == 0) {

			String articulo = JOptionPane.showInputDialog("Introduzca el nombre del artículo").toLowerCase();
			Double precio = catalogoPrecio.get(articulo);

			//Si lo encontramos (precio existe) imprimimos el valor
			if (precio != null) {
				JOptionPane.showMessageDialog(null, "El precio de " + articulo + " sin IVA es " + precio+" €");

			} else {
				JOptionPane.showMessageDialog(null, "No se encontró el precio del articulo en el catálogo.");

			}
		}

		// Preguntamos si desea visualizar la lista de informacion Stock
		int infoStock = JOptionPane.showConfirmDialog(null, "¿Deseas visualizar el stock de productos?");

		//En caso positivo, imprimimos la lista de productos
		if (infoStock == 0) {

			for (String[][] array : articulos) {
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array[i].length; j++) {
						System.out.printf("%-15s",array[i][j]);
					}
					System.out.println();
				}
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
		}
		
		//Preguntamos si desea visualizar la lista de información precios (diccionario)
		int infoCatalogo = JOptionPane.showConfirmDialog(null, "¿Deseas visualizar el precio de los productos?");

		if (infoCatalogo == 0) {
			catalogoPrecio.forEach((producto, precio) -> System.out.print(producto + ": \t" + precio+" €\t"));
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
	}
}

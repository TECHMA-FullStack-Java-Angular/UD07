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
	
	//Array multidimensional para añadir productos y la cantidad de cada;
	ArrayList<String[][]> articulos = new ArrayList<String[][]>();
	
	Hashtable<String, Double> catalogoPrecio = new Hashtable<String, Double>();
	
	String [][] articulosBase = new String[2][10];
	
	String[] productosIniciales= {"Avena", "Leche", "Pan", "Arroz", "Chocolate", 
			"Manzana", "Harina","Pollo", "Shampoo", "Jamón"};
	
	
	
	
	
	public void baseDatos() {
		
		//Añadimos los los articulos iniciales a la base de datos
		
		articulosBase[0][0]= "Avena";		articulosBase[1][0]="100";
		articulosBase[0][1]= "Leche";		articulosBase[1][1]="100";
		articulosBase[0][2]= "Pan";			articulosBase[1][2]="100";
		articulosBase[0][3]= "Arroz";		articulosBase[1][3]="100";
		articulosBase[0][4]= "Chocolate";	articulosBase[1][4]="100";
		articulosBase[0][5]= "Manzana"; 	articulosBase[1][5]="100";
		articulosBase[0][6]= "Harina"; 		articulosBase[1][6]="100";
		articulosBase[0][7]= "Pollo"; 		articulosBase[1][7]="100";
		articulosBase[0][3]= "Shampoo"; 	articulosBase[1][8]="100";
		articulosBase[0][3]= "Jamón"; 		articulosBase[1][9]="100";
		
		
		articulos.add(articulosBase);
		
		
		//añadimos los articulos iniciales al catalogo de precios
		
		catalogoPrecio.put("Avena", 0.9);
		catalogoPrecio.put("Leche", 1.25);
		catalogoPrecio.put("Pan", 1.5);
		catalogoPrecio.put("Arroz", 0.95);
		catalogoPrecio.put("Chocolate", 2.5);
		catalogoPrecio.put("Manzana", 0.89);
		catalogoPrecio.put("Harina", 1.09);
		catalogoPrecio.put("Pollo", 6.90);
		catalogoPrecio.put("Shampo", 4.99);
		catalogoPrecio.put("Jamón", 8.99);
		
		
		JOptionPane.showMessageDialog(null, "Bienvenido a la APP control de Stock!");
		
		int nuevoArticulo =JOptionPane.showConfirmDialog(null, "¿Deseas introducir nuevo articulo?");
		
		if(nuevoArticulo==0) {
			String articulo= JOptionPane.showInputDialog("Introduzca el nombre del artículo");
			String cantidad = JOptionPane.showInputDialog("Introduzca la cantidad a añadir en stock");
			String precio = JOptionPane.showInputDialog("Introduzca el precio sin IVA de la unidad.");
			double precioSt = Double.parseDouble(precio);
		
			catalogoPrecio.put(articulo, precioSt);
			
			String[][] nuevoPorducto = {{articulo, cantidad}};
			articulos.add(nuevoPorducto);
			
			
		}
		
		
		
		
		
	}
	
	
}

package algorithms;

import java.io.IOException;

public class Medians {
	static int CU�NTOS = 10000;
	static String archivoDeN�meros = "/Users/rincon/Downloads/median.txt";
	static long [] arreglo;
	
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		arreglo = Aux.leerArchivo(archivoDeN�meros, CU�NTOS);
		
		//La idea es: la mediana siempre va a estar en el izquierdo.
		
		Heap menores = new Heap (false);
		Heap mayores = new Heap (true);
		Long mediana = new Long ( 0 );
		Long sumaDeMedianas = new Long ( 0 );
		
		menores.insert(arreglo [ 0 ]);
		mediana = arreglo [ 0 ];
		sumaDeMedianas = mediana;
		
		System.out.println ("Hola");
		
		for ( int i = 1; i < arreglo.length; i++){

			Long valor = arreglo [ i ];
			
			
			Long topMenores = menores.getTop();
			Long topMayores = mayores.getTop();
			
			//System.out.println (topMayores);
			//menores.displayHeapArray ();
			
			if ( i % 2 == 0 ){
				//Both heaps must have the same size.
				if ( valor < topMayores){
					//Simplemente m�talo en el heap de menores y ya.
					menores.insert(valor);
				}
				else {
					//Dos pasos: M�talo en mayores, y luego s�que
					//el top de mayores y p�selo a menores.
					
					mayores.insert(valor);
					menores.insert( mayores.extractTop() );
				}
			}
			
			else {
				//El de la izquierda debe ser mayor que el de la derecha por uno.
				if ( valor > topMenores){
					//Simplemente m�talo en el heap de mayores y ya.
					mayores.insert(valor);
				}
				else {
					//Dos pasos: M�talo en menores, y luego s�que
					//el top de menores y p�selo a mayores.
					
					menores.insert(valor);
					mayores.insert( menores.extractTop() );
				}
				
			}
			
			mediana = menores.getTop();
			sumaDeMedianas += mediana;

		}
		
		System.out.println ("Y la suma es... " + sumaDeMedianas);
		
	}

}

package algorithms;

import java.io.IOException;
import java.util.Hashtable;

public class Sumas {
	static int CU�NTOS = 1000000;
	static long [] arreglo = new long [ CU�NTOS ];
	static String archivoDeN�meros = "/Users/rincon/Downloads/algo1-programming_prob-2sum.txt";
	static Hashtable <Long, Integer> h = new Hashtable <Long, Integer> (  );
	static long COTA_INFERIOR = -10000;
	static long COTA_SUPERIOR = 10000;
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * Pasos:
		 * Leer archivo y guardarlo en un arreglo A.
		 * Luego hacer un doble ciclo en el cual recorro el arreglo y
		 * para el valor i meto todos los posibles valores A[i] + A[j] para
		 * j > i en unhash.
		 * 
		 * Y luego al final cuento cu�ntas llaves ten�a el hash.
		 */
		
		arreglo = Aux.leerArchivo(archivoDeN�meros, CU�NTOS);
		
		Quicksort q = new Quicksort();
		q.arreglo = arreglo;
		q.quicksort(0, arreglo.length - 1);
		
		System.out.println ( "Ya...");
		
		//Ahora, pues ir uno por uno, y meter los valores de las sumas en h.
		
		long suma = 0;
		for ( int i = 0; i < CU�NTOS; i ++ ){
			if ( i % 10000 == 0)		System.out.println (i);
			for ( int j = CU�NTOS - 1; j > i + 1; j--){
				suma = arreglo [ i ] + arreglo [ j ];
				if ( suma < COTA_INFERIOR ) break;
				if ( suma <= COTA_SUPERIOR ) {
					if ( ! h.containsKey(suma) ){
						h.put(suma, 0);
					}
				}
			}
		
		}
		
		/*
		for ( int i = 0; i < CU�NTOS; i ++ ){
			System.out.println (i);
			for ( int j = i + 1; j < CU�NTOS; j++){
				if ( arreglo [ i ] !=  arreglo [ j ]){
					long suma = arreglo [ i ] + arreglo [ j ];
					if (suma <= 10000 && suma >= -10000){ 
						if ( ! h.containsKey(suma) ){
							h.put(arreglo [ i ] + arreglo [ j ], 0);
						}
					}
				}
			}
		}
		*/
		
		System.out.println ( h.size() );
		
	}
	

}

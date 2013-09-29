package algorithms;

import java.io.IOException;

public class Quicksort {

	//long [] arreglo = { 5, 6, 4, 3, 2, 1};
	long [] arreglo; // = { 2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504, 4092, 1582, 9572, 1542, 5697, 2081, 4218, 3130, 7923, 9595, 6558, 3859, 9832, 3062, 6788, 7578, 7432, 479, 8439, 9079, 7173, 2667, 2770, 2655, 972, 4264, 2014, 3171, 4715, 345, 4388, 3816, 8887, 3915, 3490, 2327, 123, 4596, 4307, 8737, 4007, 6798, 6551, 1627, 1190, 4984, 2480, 3404, 2027, 4778, 2951, 2795, 5002, 8121, 8910, 9593, 5254, 448, 6237, 5565, 1816, 392, 8143, 9310, 9293, 3138, 4869, 6756, 872, 6183, 3517, 3513, 1676, 5498, 9172, 5739, 6108, 7538, 7671, 5780, 8666, 540, 9771, 6837, 9341, 1590, 5689, 1605, 1103, 5859, 1622, 4371, 3113, 488, 6676, 6020, 2630, 6541, 6893, 6729, 4506, 3230, 2290, 72, 1976, 8259, 1373, 9962, 782, 4427, 6349, 9619, 4456, 7750, 41, 8687, 5506, 2128, 578, 8001, 4493, 9107, 746, 2639, 1456, 1361, 2841, 3198, 6344, 1450, 3346, 6221, 4816, 3800, 9975, 8075, 2400, 1353, 2282, 147, 2821, 497, 9028, 9448, 3574, 2209, 138, 8201, 2331, 3895, 7803, 2406, 9949, 1349, 965, 1320, 8836, 9562, 5753, 1039, 2348, 2184, 8212, 4909, 3025, 1690, 6117, 7318, 6478, 768, 4551, 1054, 7000, 97, 6868, 5870, 7601, 6257, 5461, 8029, 7797, 1149, 1216, 4359, 3524, 556, 1451, 8976, 1988};
	//long [] arreglo = { 4,9,7,3,6,1,2,8,5,10};
	
	int suma = 0;
	
	public static void main ( String args [] ) throws IOException {
		
		System.out.println ("Hola, amigos");

		Quicksort q = new Quicksort ();

		String pathArchivo =  "/Users/rincon/Downloads/QuickSort.txt";
		
		q.arreglo = Aux.leerArchivo(pathArchivo, 10000);
		
		q.quicksort ( 0, q.arreglo.length - 1 );
		
		Aux.imprimirArreglo (q.arreglo);
		
		System.out.println ( "\n" + q.suma );
	}
	
	void quicksort (  int ’ndicePrimero, int ’ndiceòltimo){
		//System.out.println ( ’ndicePrimero + " y " + ’ndiceòltimo);
		int ’ndicePivote = seleccionarPivote (’ndicePrimero, ’ndiceòltimo);
		
		int i = -1, j = ’ndicePrimero, ’ndiceInicial;
		int tama–o = ’ndiceòltimo - ’ndicePrimero + 1;

		if ( ’ndicePrimero < ’ndiceòltimo){	

			//Caso 2: Swap ciego entre el primero y el œltimo.
			//swap ( ’ndicePrimero, ’ndiceòltimo);
			
			//Caso 3: Escoger el mediano entre los 3 valores y usarlo como pivote.
			if ( tama–o > 2 ){
				int ’ndiceMedio = ’ndicePrimero + ((’ndiceòltimo - ’ndicePrimero) / 2 );

				long valorMedio = Aux.’ndiceMedio ( arreglo [ ’ndicePrimero ], arreglo [ ’ndiceMedio ], arreglo [ ’ndiceòltimo ] );
				//if ( arreglo [ ’ndicePrimero ] == 15) System.out.println ( ’ndicePrimero + " " + ’ndiceMedio + " " + ’ndiceòltimo);
				if ( valorMedio == arreglo [ ’ndiceMedio  ] ){ swap ( ’ndicePrimero, ’ndiceMedio   ); //System.out.println ( "Medio");
				
				}
				if ( valorMedio == arreglo [ ’ndiceòltimo ] ){  swap ( ’ndicePrimero, ’ndiceòltimo ); //System.out.println ( "òltimo");
				}	
			}
			
			suma += ’ndiceòltimo - ’ndicePrimero;

			j = m‡sUno ( j, ’ndicePivote);
			’ndiceInicial = j;			

			//Organizamos, pasando por encima del pivote.
			for ( ; j <= ’ndiceòltimo; j = m‡sUno ( j, ’ndicePivote) ){

				//System.out.println ( "Y j vale " + j );
				
				if ( arreglo [ j ] < arreglo [ ’ndicePivote ] ){
					//Ponemos a i  en el valor del siguiente ’ndice y hacemos swap entre el valor que tiene y el de j.
					if ( i == -1 ){
						//Todos los elementos hasta ahora han sido m‡s grandes que el pivote.
						//Ponemos a i en el valor del ’ndice inicial y hacemos "swap" entre el valor encontrado y el inicial.
						i = ’ndiceInicial;
					}
					else {
						i = m‡sUno ( i, ’ndicePivote);
					}
					swap ( i, j );
				}			
					
			}
			
			//Pongamos el pivote donde va.
			//Como es el primero, podemos hacer lo siguiente:
			if ( i > -1){
				swap ( ’ndicePivote, i );
				’ndicePivote = i;
			}
			else {
				//El ’ndice del pivote es el primer valor, porque todos fueron mayores que Žl.
				swap ( ’ndicePivote, ’ndicePrimero );
				’ndicePivote = ’ndicePrimero;
			}
			
			
			//Finalmente, llamemos la sub-rutina de nuevo. Ya el pivote est‡ colocado en su sitio.

			//informe (’ndicePrimero, ’ndicePivote, ’ndiceòltimo);
			
			if ( tama–o > 2 ){
				if ( ’ndicePivote == ’ndicePrimero){
					//S—lo mande pa'l resto.
					quicksort ( ’ndicePivote + 1, ’ndiceòltimo);
				}
				
				else if ( ’ndicePivote == ’ndiceòltimo){
					//Todos fueron menores que este elemento. Mande pa' los primeros solamente.
					quicksort ( ’ndicePrimero, ’ndicePivote - 1);
				}
				else {
					//Ahora s’, parta en 2.
					quicksort ( ’ndicePrimero, ’ndicePivote - 1);
					quicksort ( ’ndicePivote + 1, ’ndiceòltimo);
				}
				
			}
		}
	
	}
	
	void informe (int ’ndicePrimero, int ’ndicePivote, int ’ndiceòltimo){
		System.out.println ( "Hola.");
		System.out.print ( "Mi arreglo es ");
		Aux.imprimirArreglo(this.arreglo);
		System.out.println ( "Mi ’ndice primero es " + ’ndicePrimero);
		System.out.println ( "Mi Pivote es " + ’ndicePivote);
		System.out.println ( "Mi ’ndice œltimo es " + ’ndiceòltimo);
		System.out.println ( "Hola.");
		//Aux.pausa();
		
	}
	
	int m‡sUno ( int ’ndice, int ’ndicePivote){
		’ndice++;
		if ( ’ndice == ’ndicePivote){
			’ndice++;
		}
		return ’ndice;
	}

	int seleccionarPivote ( int ’ndicePrimero, int ’ndiceòltimo){
		return ’ndicePrimero;
	}
	
	void swap ( int i, int j ){
		//System.out.println ( "Swapeando "+ i + " y " + j );
		long tempy = arreglo [ i ];
		arreglo [ i ] = arreglo [ j ];
		arreglo [ j ] = tempy;
	}
}

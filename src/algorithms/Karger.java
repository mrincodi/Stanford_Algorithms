/**
 * Esta clase no sirve.
 */

package algorithms;

import java.util.ArrayList;

public class Karger {

	ArrayList<Integer> v�rtices;
	ArrayList<Integer[]> arreglo;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Karger k = new Karger ();
		k.v�rtices = new ArrayList<Integer> ();
		
		for ( int i = 1; i <= 8; i++){
			k.v�rtices.add (new Integer ( i ));
		}
		
		k.arreglo = new ArrayList<Integer[]> ();
		
		int [ ] [ ] arr = { { 1, 2 }, { 1, 4 }, { 1, 3 }, { 2, 3 }, { 2, 4 }, { 3, 4 }, { 2, 5 }, { 4, 7 }, { 5, 6 }, { 5, 8 }, { 5, 7 }, { 6, 8 }, { 6, 7 }, { 7, 8 } };

		for ( int i = 0; i < arr.length; i++){
			Integer qq1 = new Integer (arr [ i ][ 0 ]);
			Integer qq2 = new Integer (arr [ i ][ 1 ]);
			
			Integer [] qq_qq = { qq1, qq2 };
			
			k.arreglo.add (qq_qq );
		}
		
		int semilla = 4;
		
		int n�meroDeCortesM�nimos = k.karger ( k.v�rtices, k.arreglo, semilla);
		
		System.out.println ("y tengo " + n�meroDeCortesM�nimos);

	}
	
	int karger ( ArrayList<Integer> v�rtices, ArrayList<Integer[]> arreglo, int semilla ) throws Exception{
		
		int n�meroDeV�rtices = v�rtices.size();
		//Si el n�mero de v�rtices es dos, retornar 1.
		if ( n�meroDeV�rtices == 2) return arreglo.size();

		Integer [] v�rticesATomar = Aux.tomarDosValoresDiferentesAlAzar ( v�rtices, semilla);
		
		v�rtices.remove ( v�rticesATomar [ 0 ] );
		arreglo = unificarV�rtices ( arreglo, v�rticesATomar);
		
		return karger ( v�rtices, arreglo, semilla );
		
	}
	
	ArrayList<Integer[]> unificarV�rtices ( ArrayList<Integer[]> arreglo, Integer [] v�rticesATomar ){
		
		ArrayList<Integer[]> nuevoArreglo = new  ArrayList<Integer[]> () ;

		Integer par [] = new Integer [ 2 ];

		for ( int i = 0; i < arreglo.size();i++){
			par = arreglo.get(i);
			
			//Si es un ciclo, remu�velo.
			if ((( par [ 0 ] == v�rticesATomar [ 0 ] ) && (par [ 1 ] == v�rticesATomar [ 1 ]))
					|| ( (par [ 0 ] == v�rticesATomar [ 1 ]) && (par [ 1 ] == v�rticesATomar [ 0 ]))){
				//Ign�relo.
			}
			//Enlaces del segundo a uno diferente pasan al primero.
			else if (par [ 0 ] == v�rticesATomar [ 1 ]){
				Integer [] nuevoPar = { v�rticesATomar [ 0 ], par [ 1 ] };
				arreglo.remove( i );
				nuevoArreglo.add(nuevoPar);
			}
			else {
				//Los dem�s quedan igualitos.
				nuevoArreglo.add( par );
			}
		}
		
		return nuevoArreglo;
	}
	

}

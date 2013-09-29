/**
 * Esta clase no sirve.
 */

package algorithms;

import java.util.ArrayList;

public class Karger {

	ArrayList<Integer> vértices;
	ArrayList<Integer[]> arreglo;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Karger k = new Karger ();
		k.vértices = new ArrayList<Integer> ();
		
		for ( int i = 1; i <= 8; i++){
			k.vértices.add (new Integer ( i ));
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
		
		int númeroDeCortesMínimos = k.karger ( k.vértices, k.arreglo, semilla);
		
		System.out.println ("y tengo " + númeroDeCortesMínimos);

	}
	
	int karger ( ArrayList<Integer> vértices, ArrayList<Integer[]> arreglo, int semilla ) throws Exception{
		
		int númeroDeVértices = vértices.size();
		//Si el número de vértices es dos, retornar 1.
		if ( númeroDeVértices == 2) return arreglo.size();

		Integer [] vérticesATomar = Aux.tomarDosValoresDiferentesAlAzar ( vértices, semilla);
		
		vértices.remove ( vérticesATomar [ 0 ] );
		arreglo = unificarVértices ( arreglo, vérticesATomar);
		
		return karger ( vértices, arreglo, semilla );
		
	}
	
	ArrayList<Integer[]> unificarVértices ( ArrayList<Integer[]> arreglo, Integer [] vérticesATomar ){
		
		ArrayList<Integer[]> nuevoArreglo = new  ArrayList<Integer[]> () ;

		Integer par [] = new Integer [ 2 ];

		for ( int i = 0; i < arreglo.size();i++){
			par = arreglo.get(i);
			
			//Si es un ciclo, remuévelo.
			if ((( par [ 0 ] == vérticesATomar [ 0 ] ) && (par [ 1 ] == vérticesATomar [ 1 ]))
					|| ( (par [ 0 ] == vérticesATomar [ 1 ]) && (par [ 1 ] == vérticesATomar [ 0 ]))){
				//Ignórelo.
			}
			//Enlaces del segundo a uno diferente pasan al primero.
			else if (par [ 0 ] == vérticesATomar [ 1 ]){
				Integer [] nuevoPar = { vérticesATomar [ 0 ], par [ 1 ] };
				arreglo.remove( i );
				nuevoArreglo.add(nuevoPar);
			}
			else {
				//Los demás quedan igualitos.
				nuevoArreglo.add( par );
			}
		}
		
		return nuevoArreglo;
	}
	

}

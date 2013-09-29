package algorithms2;

import java.io.IOException;
import java.util.ArrayList;

import algorithms.Aux;

public class WeightedCompletionTimes {

	static ArrayList <Double[]> listaOrdenadaDePuntajes= new ArrayList <Double[]> (); //Ordenada de mayor a menor.
	static String archivoDeInformaci—nDeProcesos="/Users/rincon/Downloads/jobs.txt";
	static int [] [] arregloDePesoYDuraci—n;
	double getScoreDifference (double weight, double lenght){
		return weight - lenght;
	}
	
	/**
	 * @param args
	 * @throws IOException Si no puedo leer el archivo con la inforlaci—n de los procesos
	 * @throws NumberFormatException  Si una de esas entradas no es un entero.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		/*
		 * Algorithm:
		 * Read file
		 * Calculate score for each pair
		 * Sort the pairs by score
		 * Calculate the sum of weighted completion times= Sum ( w(i) * Sum (L(1)->L(i))  
		 * 
		 */
		int peso, duraci—n;
		arregloDePesoYDuraci—n = Aux.leerPesoYDuraci—nDeProcesos ( archivoDeInformaci—nDeProcesos );
		double [] puntaje = new double [arregloDePesoYDuraci—n.length];
		for ( int i = 0; i < arregloDePesoYDuraci—n.length; i++){
			peso = arregloDePesoYDuraci—n [i][ 0 ];
			duraci—n = arregloDePesoYDuraci—n [i][ 1 ];
			puntaje [ i ] = calcularPuntaje ( peso, duraci—n );
			
			//System.out.println (i);
			meterEnLista (i, puntaje [ i ]);
		}
		//MR... Continuar: Ordene por peso y luego a hacer el c‡lculo.
		
		long acumulado = 0;
		long duraci—nAcumulada = 0;
		
		for ( int i = 0; i < listaOrdenadaDePuntajes.size(); i++ ){
			int posici—nATomar = listaOrdenadaDePuntajes.get(i)[0].intValue();
			peso = arregloDePesoYDuraci—n [ posici—nATomar ][ 0 ];
			duraci—nAcumulada += arregloDePesoYDuraci—n [ posici—nATomar ][ 1 ];
			acumulado += peso * duraci—nAcumulada;
		}
		
		System.out.println (acumulado);
		
	}
	
	static double calcularPuntaje ( int peso, int duraci—n ){
		double pesoDouble = (double) peso;
		double duraci—nDouble = (double) duraci—n;
		
		//Cambiar a "-" o a "/" segœn como se quiera probar. 
		return pesoDouble/duraci—nDouble;
	}
	
	/**
	 * Este mŽtodo mete la posici—n y el puntaje en una lista
	 * manteniendo el orden, de mayor a menor, con respecto al puntaje.
	 * @param pos
	 * @param puntaje
	 */
	static void meterEnLista (int pos, double puntaje){
		
		//No es la manera —ptima, pero por ahora...

		boolean siga = true;
		int i = 0;
		for (; i < listaOrdenadaDePuntajes.size() && siga;){
			
			if (listaOrdenadaDePuntajes.get(i)[1] < puntaje ){
				siga = false;
			}
			else if (listaOrdenadaDePuntajes.get(i)[1] == puntaje){
				
				int posDelElementoAComparar = listaOrdenadaDePuntajes.get(i)[0].intValue();
				if (arregloDePesoYDuraci—n [ pos ][0] > arregloDePesoYDuraci—n [ posDelElementoAComparar ][0]){
					siga = false;
				}
				else i++;
			}
			else {
				i++;
			}
		}
		Double [] nuevosValores = new Double [ 2 ];
		nuevosValores [ 0 ] = (double) pos;
		nuevosValores [ 1 ] = puntaje;
		
		listaOrdenadaDePuntajes.add(i, nuevosValores);
		
	}

	
	
	
}

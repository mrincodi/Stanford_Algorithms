package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Aux {
	static long [] leerArchivo ( String pathArchivo, int cu‡ntos ) throws IOException{
		long [] nœmeros = new long [ cu‡ntos ];
		BufferedReader reader = new BufferedReader(new FileReader(pathArchivo));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			nœmeros [ i ] = Long.parseLong(line);
			i++;
		}
		
		return nœmeros;	
	}
	
	static void imprimirArreglo ( long [] arreglo ){
		for ( int i = 0; i < arreglo.length; i++){
			System.out.print ( arreglo [ i ] + " ");
		}
		System.out.println ("\n");
	}
	
	static void imprimirLista( ArrayList <Integer> arreglo ){
		for ( int i = 0; i < arreglo.size(); i++){
			System.out.print ( arreglo.get(i) + " ");
		}
		System.out.println ("\n");
	}
	
	static void imprimirListaDeListasDeIntegers ( ArrayList<ArrayList<Integer>> X ){
		int i = 0;
		for ( ArrayList<Integer> g:X ){
			System.out.print ( i + " -> ");
			for ( Integer q: g ){
				System.out.print ( q + " ");
			}
			System.out.println ("");
			i++;
		}
	}
	
	static void imprimirPosici—nYValorDeArreglo ( int [] tiempos ){
		for ( int i = 1; i < tiempos.length; i++ ){
			System.out.println ( i + " es " + tiempos [ i ]);
		}
	}
	
	static void pausa () {
		try{
			BufferedReader br = 
	                      new BufferedReader(new InputStreamReader(System.in));
	 
			String input;
	 
			while((input=br.readLine())!=null){
				System.out.println(input);
			}
	 
		}catch(IOException io){
			io.printStackTrace();
		}
	}
	
	static long ’ndiceMedio ( long a, long b, long c){
		//retorna la mediana de 3 nœmeros distintos.

		if ( a > b && b > c) return b;
		if ( a > c && c > b) return c;
		if ( b > a && a > c) return a;
		if ( b > c && c > a) return c;
		if ( c > a && a > b) return a;
		if ( c > b && b > a) return b;
		
		System.out.println ("NOOOO" + a + " " + b + " " + c );
		return -1000;
	}
	
	/**
	 * Este proceso lee de un archivo con este formato:
	 * Nœmero de procesos (N)
	 * Peso1 Duraci—n1
	 * Peso2 Duraci—n2
	 * (...)
	 * PesoN Duraci—nN
	 * 
	 * @param pathArchivo Ubicaci—n del archivo con el formato de los procesos.
	 * @return Arreglo con parejas peso - duraci—n
	 * @throws NumberFormatException Si hay problemas haciendo el "parsing"
	 * @throws IOException Si el archivo no se puede leer.
	 */
	public static int [][] leerPesoYDuraci—nDeProcesos (String pathArchivo) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(pathArchivo));
		
		String line = null;
		line = reader.readLine();
		int cu‡ntos = Integer.parseInt(line);
		
		int [][] parejas = new int [cu‡ntos][2];
		
		int i = 0;
		String [] parejaString;
		
		while ((line = reader.readLine()) != null) {
			parejaString = line.split(" ");
			parejas [ i ][0] = Integer.parseInt( parejaString [ 0 ]);
			parejas [ i ][1] = Integer.parseInt( parejaString [ 1 ]);
			i++;
		}
		
		return parejas;

	}
	
	static Integer [] tomarDosValoresDiferentesAlAzar ( ArrayList<Integer> vŽrtices, int semilla) throws Exception {
		
		Random generador = new Random ( semilla );
		int size = vŽrtices.size ();
		
		if ( size < 2 ){
			throw new Exception ("tomarDosValoresDiferentesAlAzar requiere al menos un arreglo de dos enteros.");
		}
		
		int primerêndice = generador.nextInt(size);
		
		int segundoêndice = generador.nextInt(size - 1);
		
		if ( segundoêndice >= primerêndice){
			segundoêndice++;
		}
		
		Integer [] arregloARetornar = { vŽrtices.get(primerêndice), vŽrtices.get(segundoêndice) };
		
		return arregloARetornar;
	}
	
	public static ArrayList <Integer []> copyOfRange ( ArrayList <Integer []> origen, int lowerBound, int upperBound ){
		
		ArrayList <Integer []> destination = new ArrayList <Integer []> ();
		Integer [] newArray;
		
		for (int i = lowerBound; i < upperBound; i++){
			newArray = Arrays.copyOf(origen.get(i), origen.get(i).length);
			destination.add(newArray);
		}
		
		return destination;
	}
	
}

package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Kosaraju {
	static String archivoGrafo="/Users/rincon/Downloads/SCC.txt";
	static int numVŽrtices = 875714;
	static ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> GInv = new ArrayList<ArrayList<Integer>>();
	static int [] explorados = new int [ numVŽrtices + 1 ];
	static int [] tiempos = new int [ numVŽrtices + 1 ];
	static int t = 0;
	static int cu‡ntosPorGrupo=0;
	//static ArrayList<ArrayList<Integer>> GTrad = new ArrayList<ArrayList<Integer>>();
	
	
	//static int [][] G = new int [9][];
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*
		 * Los pasos son:
		 * - Crear el arreglo: es un arreglo de arreglos.
		 * - Crear el arreglo invertido.
		 * - Ejecutar DFSLoop para computar el reordenamiento m‡gico. Retornar un arreglo pa' c/ posici—n su nuevo valor.
		 * - Ejecutar DFSLoop con el arreglo anterior pa' traducir. 
		 * 
		 */
		
		//inicializarG ();
		
		System.out.println(java.lang.Runtime.getRuntime().maxMemory()); 
	
		inicializarGYGInv (archivoGrafo);
		
		System.out.println  ("G tiene " + G.size());
		System.out.println  ("GInv tiene " + GInv.size());
		
		
		//Aux.imprimirListaDeListasDeIntegers ( G );
		
		//invertirG ();

		//Aux.imprimirListaDeListasDeIntegers ( GInv );

		DFSLoop1 ();

		System.out.println ( "Ser‡ un milagro...");
		
		Aux.imprimirPosici—nYValorDeArreglo (tiempos);
		
		explorados = new int [ numVŽrtices + 1 ];
		
		//traducirGrafo ( );
		
		System.out.println ("********");
		DFSLoop2 ();
		
		/* 
		for ( ArrayList<Integer> g:G ){
			for ( Integer q: g ){
				System.out.print ( q + " ");
			}
			System.out.println ("");
		}
		*/

	}
	
	/**
	 * Traduce del grafo viejo al nuevo con la lista ordenada.
	 */
	static void traducirGrafo () {
		
	}
	
	/**
	 * Ac‡ usamos la lista inteligente de DFSLoop1 para hallar los componentes. 
	 */
	static void DFSLoop2 ( ){
		for ( int i = numVŽrtices; i >= 1; i-- ){
			
			int valorDelVŽrtice = tiempos [ i ];
			cu‡ntosPorGrupo=0;
			if ( explorados [ valorDelVŽrtice ] == 0 ){
				DFS (valorDelVŽrtice);
				System.out.println ("--> " + cu‡ntosPorGrupo);
			}
		}		
	}
	/**
	 * DFS recibe la posici—n en la lista inteligente.
	 * @param s
	 */
	static void DFS ( int s ){
		explorados [ s ] = 1;
		cu‡ntosPorGrupo++;
		
		for ( Integer j: G.get(s) ){
			if ( explorados [ j ] == 0 ){
				DFS (j);
			}
		}
		
		
	}
	
	
	
	/**
	 * Este mŽtodo ejecuta DFSLoop y retorna la "lista inteligente" que usaremos en DFSLoop2
	 */
	static void DFSLoop1 (){
		for ( int i = numVŽrtices; i >= 1; i-- ){
			if ( explorados [ i ] == 0 ){
				//System.out.println ( "Bueee... Vamos por " + i );
				
				if ( i == 874931) {
					System.out.println ("ÁPare!");
				}
				
				
				DFSInv (i);
			}
		}
	}

	static void DFSInv ( int s ){
		explorados [ s ] = 1;
		//Aux.imprimirLista (GInv.get(s));
		for ( Integer j: GInv.get(s) ){
			if ( explorados [ j ] == 0 ){
				DFSInv (j);
			}
		}
		t++;
		tiempos [ t ] = s;
	}
	
	

	/**
	 * La fea pero necesaria tarea de leer el archivo.
	 * @param archivo
	 * @throws IOException 
	 */
	static void inicializarGYGInv (String archivo) throws IOException {
		
		for ( int i = 0; i < numVŽrtices + 1; i++){
			ArrayList<Integer> g = new ArrayList<Integer>();
			G.add(g);
			g = new ArrayList<Integer>();
			GInv.add(g);
		}
		
		//Abrir el archivo
		BufferedReader reader = new BufferedReader(new FileReader(archivo));
		String line = null;
		
		int origen = 0, destino = 0;
		int cu‡ntos = 0;
		while ((line = reader.readLine()) != null) {
			//Separar las l’neas 
			String[] parts = line.split(" ");
			origen  = Integer.parseInt(parts [ 0 ]);
			destino = Integer.parseInt(parts [ 1 ]);
			
			cu‡ntos++;
			//if (cu‡ntos%100000 == 0){ System.out.println ("Wepa" + cu‡ntos);}
			G.get(origen).add(destino);
			GInv.get(destino).add(origen);
		}
	}
	
	/**
	 * 
	 */
	static void inicializarG () {
	
		ArrayList<Integer> g = new ArrayList<Integer>();
		G.add(g);
		
		G.add(g);
		
		g = new ArrayList<Integer>();
		g.add(1);
		g.add(3);
		G.add(g);
		
		g = new ArrayList<Integer>();
		g.add (4);
		G.add(g);

		g = new ArrayList<Integer>();
		g.add (2);
		g.add (5);
		G.add(g);

		g = new ArrayList<Integer>();
		g.add (6);
		g.add (7);
		G.add(g);

		g = new ArrayList<Integer>();
		g.add (7);
		G.add(g);

		g = new ArrayList<Integer>();
		g.add (8);
		G.add(g);
		
		g = new ArrayList<Integer>();
		g.add (5);
		G.add(g);
		
	}
	
	static void invertirG () {
		GInv.clear();
		
		//ArrayList<Integer> g = new ArrayList<Integer>();
		
		for (int i = 0; i < G.size(); i++){
			ArrayList<Integer> g = new ArrayList<Integer>();
			GInv.add( g );
		}
		
		int i = 0;
		for ( ArrayList <Integer> g:G ){
			for (Integer q: g){
				GInv.get(q).add(i);
			}
			i++;
			
			if ( i % 100000 == 0 )	System.out.println (i);
		}
		
		
	}
	
	
	ArrayList<Integer> arrayToList (int [] array){
		ArrayList<Integer> newOne = new ArrayList<Integer>();;
		for ( int i = 0; i < array.length; i++){
			newOne.add(i);
		}
		return newOne;
	}
	
	
	
	

}

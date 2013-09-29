package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



public class Karger2 {
	//Representamos el grafo como un arreglo de arreglos. Cada uno de estos subarreglos tiene
	//como primer valor el origen, y los dem‡s los enlaces destino.
	static ArrayList<ArrayList<Integer>> GOriginal = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
//	static int numVŽrtices = 200;
	static String archivo = "/Users/rincon/Downloads/kargerMinCut.txt";
	static Random rand = new Random();
	static int metasemilla=6;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		llenarGrafo ();
		
		System.out.println ("Hola");
		 
		for ( int i = 0; i < 100; i++){
	
			G = clonar (GOriginal);
			int semilla = rand.nextInt(metasemilla);  
			
			//Ejecutar karger con la semilla. Luego debo contar cu‡ntos enlaces quedaron...
			// y luego volver a correr todo esto con otra semilla. Ya veremos...
			int cu‡ntos = karger (semilla);
			
			System.out.println ( cu‡ntos );
		}
	}
	
	public static int karger (int semilla){
		
		if ( G.size() == 2){
			return G.get(0).size() - 1; 
		}
		//Tomar un enlace al azar.
		
		int [] par = tomarEnlaceAlAzar ( semilla );

		if ( par [ 0 ] == par [ 1 ]) System.out.println ("Waaaat??");
		contraer (par);
		
		return karger ( semilla );
	}
	
	public static ArrayList<ArrayList<Integer>> clonar ( ArrayList<ArrayList<Integer>> GOrigen ){
		ArrayList<ArrayList<Integer>> GDestino = new ArrayList<ArrayList<Integer>> ();

		for ( int i = 0 ; i < GOrigen.size(); i++){

			ArrayList<Integer> gOrigen = GOrigen.get(i);
			ArrayList<Integer> gDestino = new ArrayList<Integer> ();

			for ( int j = 0; j < gOrigen.size(); j++){
				gDestino.add(gOrigen.get(j));
			}
			
			GDestino.add(gDestino);
		}
		
		return GDestino;
	}
	
	public static void contraer ( int [] par ){
		
		int origen  = par [ 0 ];
		int destino = par [ 1 ];
		int ’ndiceOrigen, ’ndiceDestino;
		//Primero, busquemos los ’ndices del origen y el destino.
		
		’ndiceOrigen = getêndiceDeVŽrtice ( G, origen );
		’ndiceDestino = getêndiceDeVŽrtice ( G, destino );

		ArrayList<Integer> gOrigen = G.get (’ndiceOrigen);
		ArrayList<Integer> gDestino = G.get (’ndiceDestino);
		
		//Saquemos las referencias al destino en el origen y viceversa (porque Žstos son bucles).

		while ( gOrigen.indexOf(destino) != -1){ 
			gOrigen.remove(gOrigen.indexOf(destino));
			gDestino.remove(gDestino.indexOf(origen));
		}
		
		//Ahora recorra la lista de gDestino.
		//Para cada elemento, bœsquelo en el grafo, y
		//cambie las referencias de ese elemento al destino por referencias de ese elemento al origen
		//Luego, agregue una entrada del origen a ese elemento.
		//Finalmente, elimine el elemento destino
		
		for ( int i = 1; i < gDestino.size(); i++){
			int elemento = gDestino.get(i);
			int ’ndiceElemento = getêndiceDeVŽrtice(G, elemento);
			
			ArrayList<Integer> gElemento = G.get (’ndiceElemento);
			gElemento.remove(gElemento.indexOf(destino));
			gElemento.add(origen);
			
			gOrigen.add(elemento);	
		}
		
		G.remove(’ndiceDestino);
		//ÁY listo!
		
	}
	
	public static int getêndiceDeVŽrtice ( ArrayList<ArrayList<Integer>> G, int valor ){
		int ’ndiceOrigen = -1;
		for (int i = 0; i < G.size(); i++){
			int voyPor = G.get(i).get(0);
			if ( voyPor == valor) ’ndiceOrigen = i;
		}
		return ’ndiceOrigen;
	}
	
	public static int [] tomarEnlaceAlAzar ( int semilla ){
		
		//Tomamos del grafo un arreglo al azar, y de Žste, su primer elemento.
		int ’ndiceOrigen = rand.nextInt(G.size());
		int origen = G.get ( ’ndiceOrigen ).get(0);
		
		int ’ndiceDestino = rand.nextInt(G.get ( ’ndiceOrigen ).size() - 1) + 1;
		int destino = G.get(’ndiceOrigen).get(’ndiceDestino);
		
		int [] par = { origen, destino };
		return par;
	}
	
	public static void llenarGrafo () throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(archivo));
		String line = null;
		ArrayList<Integer> g = new ArrayList<Integer> ();
		
		/*
		for (int i = 0; i < numVŽrtices; i++ ){
			g = new ArrayList<Integer> ();
			G.add( g );
		}
		*/
		
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split("\t");
			g = new ArrayList<Integer> ();
			
			for (int i = 0; i < parts.length; i++){				
				g.add ( Integer.parseInt(parts [ i ]) );
			}

			GOriginal.add(g);
			
		}
	}
	

}

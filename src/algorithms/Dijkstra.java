package algorithms;

import java.util.ArrayList;


public class Dijkstra {
	static ArrayList<ArrayList<Integer []>> G = new ArrayList<ArrayList<Integer []>>();
	int [] keys;
	int [] [] heap;
	int n = 0; // Nœmero de elementos del heap.
	int N = 200;
	int INFINITY = 1000000;
	static int [] X;	// ƒsta es la lista de las distancias.

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Leer archivo. 
		//Hacer heap inicial. Elementos del 2 al 200.
		//Dijkstra!

	}
	
	
	public void dijkstra (){
/*
		emptyQ();
		
		insert ( 1, 0 );
		
		for ( int i = 2; i <= n; i++ ){
			insert ( i, INFINITY );
		}
		
		for ( int ii = 2; ii <= n; ii++ ){
			int [] par = extractMin ();
			
			int i = par [ 0 ];
			int d = par [ 1 ];
			
			X [ i ] = d;
			
			for ( int jj = 0; jj < G.get(i).size(); jj++ ){
				Integer [] edge = G.get(i).get(jj);
				int j = edge [ 0 ];
				int w = edge [ 1 ];
				if  ( d + w < X [ j ]){
					decreaseKey ( j, d + w );
				}
			}
		}
		
		*/
		
	}
	
	public void emptyQ(){
		n=0;
		
	}
	
	public void insert (int x, int k){
		int [] nuevoPar = { x, k };
		heap[ n ] = nuevoPar;
		n++;
		//decreaseKey ()... 
	}
	
	public void decreaseKey ( int x, int k ){
		
	}
	
	

}

package algorithms;

import java.io.IOException;
import java.util.ArrayList;

public class Dijkstra2 {

	static DirectedGraph G;
	static String graphFile = "/Users/rincon/Downloads/dijkstraData.txt";
	
	static ArrayList < Integer > X = new ArrayList<Integer>();	 //Known vertices.
	static int [] dist; //Distances to each vertex.
	static int NUM_VERTICES;
	static int s = 1;
	static int MAX_WEIGHT = 100000;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException { 
		
		G =  new DirectedGraph ( graphFile );
		NUM_VERTICES = G.getSize();

		dist = new int [ NUM_VERTICES + 1 ];
		
		X.add( s );
		dist [ s ] = 0;

		for ( int i = 2; i <= NUM_VERTICES; i++ ){
			int [] threeValues = takeCheapestVertex ( );	//Source, destination and weight.
			int v = threeValues [ 0 ];
			int w = threeValues [ 1 ];
			int l = threeValues [ 2 ];
			
			System.out.println ("--> " + w );
			X.add ( w );
			dist [ w ] = dist [ v ] + l;
		}
		
		for ( int i = 0; i < dist.length; i++){
			System.out.println ( i + ":"+ dist [ i ]);
		}
	}

	public static int [] takeCheapestVertex (){
		
		int minSource = 0;
		int minDestination = 0;
		int minWeight = MAX_WEIGHT;
		int minimum = MAX_WEIGHT;
		//Get all Vertices and weights from the current X.
		for ( int i = 0; i < X.size(); i++){
			int possibleSource = X.get(i);
			ArrayList < Integer [] > destinationsAndWeights =  G.getDestinationsAndWeightsFromSource(possibleSource);

			for ( int j = 0; j < destinationsAndWeights.size(); j++ ){
				
				int possibleDestination = destinationsAndWeights.get(j) [ 0 ];
				int possibleWeight = destinationsAndWeights.get(j) [ 1 ];
				
				//If it's not inside the vertices already checked...
				if ( X.indexOf(possibleDestination) == -1) { 

					//And if it's a minimum...
					if ( dist [ possibleSource ] + possibleWeight < minimum){
						minimum = dist [ possibleSource ] + possibleWeight;
						minSource = possibleSource;
						minDestination = possibleDestination;
						minWeight = possibleWeight;
					}
				}
			}
		}
		
		int [] returnValue  = { minSource, minDestination, minWeight };
		return returnValue;
	}
	
}

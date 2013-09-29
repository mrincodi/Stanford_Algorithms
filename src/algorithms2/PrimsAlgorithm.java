package algorithms2;

import java.io.IOException;
import java.util.ArrayList;

public class PrimsAlgorithm {

	static String graphFilePath = "/Users/rincon/Downloads/edges.txt";
	static UndirectedGraph G;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		/* Steps:
		 * - Read the graph
		 * -
		 */
		
		G = new UndirectedGraph(graphFilePath);
		int totalSum = getSpanningTreeValue();
		System.out.println (totalSum);
	}

	public static int getSpanningTreeValue ( ) throws Exception{
		
		int totalSum = 0;
		ArrayList <Integer> knownSetOfVertices = new ArrayList <Integer> ();
		//Let's start by the first vertex.
		
		knownSetOfVertices.add( 1 );
		
		int i = 1;
		Integer [] verticesAndWeight;
		while ( i < G.getNumberOfVertices()){
			verticesAndWeight = G.getSmallestLinkToNewVertex ( knownSetOfVertices );
			
			if (!knownSetOfVertices.contains(verticesAndWeight [ 0 ])){
				knownSetOfVertices.add(verticesAndWeight [ 0 ]);
			}
			else if (!knownSetOfVertices.contains(verticesAndWeight [ 1 ])){
				knownSetOfVertices.add(verticesAndWeight [ 1 ]);
			}
			
			totalSum += verticesAndWeight [ 2 ];
			i++;
		}
		return totalSum;
	}

}

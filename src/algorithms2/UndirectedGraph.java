package algorithms2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import algorithms.Aux;
import algorithms.Mergesort;

/**
 * This class is a data structure for undirected graphs, with weighted edges.
 * @author rincon
 *
 */

	enum graphFormat {
		nNodes_nEdges_then_n1_n2_cost,
		nNodes_then_n1_n2_cost
	}

public class UndirectedGraph {
	int size = 0;
	ArrayList<Integer[]> links = new ArrayList<Integer[]> ();	//Saves the links in format: Vertex1 Vertex2 Weight

	static int MAXIMUM_POSSIBLE_WEIGHT = 1000000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Constructor. Reads from a file with this structure:
	 * 
	 * [number_of_nodes] [number_of_edges]
	 * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
	 * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
	 * ...
	 * 
	 * or 
	 * 
	 * [number_of_nodes]
	 * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
	 * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
	 * ...
	 * 
	 * @param graphPath Location of graph file
	 * @param gf Structure
	 * @throws IOException 
	 */
	public UndirectedGraph ( String graphPath, graphFormat gf ) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(graphPath));
		String line = null;

		String [] valuesStringArray;
	
		//No matter the format, this split will work for the first line 
		line = reader.readLine();
		valuesStringArray = line.split(" ");
		
		this.size = Integer.parseInt(valuesStringArray [ 0 ]);
		
		int i = 1;
		while ((line = reader.readLine()) != null) {
			valuesStringArray = line.split(" ");
			Integer[] valuesArray = { 
					Integer.parseInt( valuesStringArray [ 0 ] ),
					Integer.parseInt( valuesStringArray [ 1 ] ),
					Integer.parseInt( valuesStringArray [ 2 ] )};
			
			this.links.add(valuesArray);
					i++;
		}
	}
	
	public UndirectedGraph ( String graphPath ) throws IOException{
		this ( graphPath, graphFormat.nNodes_nEdges_then_n1_n2_cost );
	}

	
	public int getNumberOfVertices (){
		return this.size;
	}

	public Integer [] getSmallestLinkToNewVertex ( ArrayList <Integer> knownSetOfVertices ) throws Exception{
		
		Integer [] currentSmallestLink = {-1, -1, MAXIMUM_POSSIBLE_WEIGHT};
		Integer [] currentLink = null;
		
//		int possibleNewVertex = -1;
		boolean isPossibleLink = false;
		
		for( int i = 0 ; i < links.size() ; i++ ){
			currentLink = links.get(i);
			isPossibleLink = false;
			if ( knownSetOfVertices.contains(currentLink [ 0 ]) && ! knownSetOfVertices.contains(currentLink [ 1 ])){
//				possibleNewVertex = currentLink [ 1 ];
				isPossibleLink = true;
			}
			else if ( knownSetOfVertices.contains(currentLink [ 1 ]) && ! knownSetOfVertices.contains(currentLink [ 0 ])){
//				possibleNewVertex = currentLink [ 0 ];
				isPossibleLink = true;
			}
			
			if (isPossibleLink){
				if ( currentLink [ 2 ] < currentSmallestLink [ 2 ]){
					currentSmallestLink [ 0 ] = currentLink [ 0 ];
					currentSmallestLink [ 1 ] = currentLink [ 1 ];
					currentSmallestLink [ 2 ] = currentLink [ 2 ];
				}
			}
		}
		
		//We should have the smallest link at this moment.

		if ( currentSmallestLink [ 0 ] == -1 ){
			throw new Exception ( "No links to unknown vertices! Either the whole vertex set was given or the graph is not connected"); 
		}
		
		return currentSmallestLink;
	}
	
	
	public Integer [] getLinkAndWeight (int i ){
		return this.links.get(i);
	}
	
	public void mergeSort (){
		merge(links);
	}
	
	ArrayList<Integer[]> merge ( ArrayList<Integer[]> array){
		//divide in 2
				
		ArrayList<Integer[]> sortedArray = array;
		
		
		if ( links.size() > 1 ){ 
			int middle = links.size() / 2;
			
			
			ArrayList<Integer[]> lowerArray = Aux.copyOfRange(links, 0, middle);
			ArrayList<Integer[]> upperArray = Aux.copyOfRange(links, middle, links.size());
			
			ArrayList<Integer[]> sortedLowerArray = merge ( lowerArray );
			ArrayList<Integer[]> sortedUpperArray = merge ( upperArray );
			
			sortedArray = sortArrays ( sortedLowerArray, sortedUpperArray);
		}
		return sortedArray;
	}
	
	private ArrayList<Integer[]> sortArrays ( ArrayList<Integer[]> sortedLower, ArrayList<Integer[]> sortedUpper ){
		ArrayList<Integer[]> sortedArray = new ArrayList<Integer[]>();

		int i = 0, j = 0, k = 0;
		for ( ; k < sortedLower.size() + sortedUpper.size();){
			if ( sortedLower.get(i)[2] <= sortedUpper.get(j)[2] ){
				
				sortedArray.add(sortedLower.get(i));
				i++;
			}
			else {
				sortedArray.add(sortedLower.get(j));
				j++;
			}
			
			k++;
			
			if ( i == sortedLower.size() ){
				for ( ; j < sortedUpper.size(); j++ ){
					sortedArray.add(sortedUpper.get(j));
					k++;
				}
			}
			else if ( j == sortedUpper.size() ){
				for ( ; i < sortedLower.size(); i++ ){
					sortedArray.add(sortedLower.get(i));
					k++;
				}
			}
		}
		
		return sortedArray;
	}
	
}

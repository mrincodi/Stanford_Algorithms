package algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DirectedGraph {

	int numVertices = 0;
	ArrayList < ArrayList < Integer [] > > G = new ArrayList < ArrayList < Integer [] > > ();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public DirectedGraph ( ArrayList < ArrayList < Integer [] > > G ){
		this.G=G;
		this.numVertices = G.size() + 1; //First one doesn't count. We start at 1.
	}
	
	
	/**
	 * We are going to suppose that the file has the right format!
	 * source	dest1,weight1	dest2,weight2,...,	destn/weightn
	 * Tab-separated. First source is 1 and souces go in numeric order.
	 * @param fileLocation
	 * @return
	 * @throws IOException
	 */
	public DirectedGraph ( String fileLocation ) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
		String line = null;
		
		this.G = new ArrayList < ArrayList < Integer [] > > ();
		this.numVertices = 0;
		G.add(null);
		while ((line = reader.readLine()) != null) {
			//Split line accordingly.
			String [] splitLine = line.split("\t");
			ArrayList < Integer [] > destAndWeight = new ArrayList < Integer [] > ();
			
			//splitLine [ 0 ] is just the node number, the source. 
			for ( int j = 1; j < splitLine.length; j++ ){
				String [] stringPair = splitLine [ j ].split(",");
				Integer destAndWeightPair [ ] = {  Integer.valueOf(stringPair [ 0 ]), Integer.valueOf(stringPair [ 1 ]) };
				
				destAndWeight.add(destAndWeightPair);
			}
			
			this.G.add(destAndWeight);
			this.numVertices++;
		}
		System.out.println ("Weno...");
		// MR: ÀY ya?
	}
	
	public int getSize (){
		return this.numVertices;
	}
	
	public  ArrayList < Integer [] > getDestinationsAndWeightsFromSource ( int s ){
		return G.get(s);
	}

}

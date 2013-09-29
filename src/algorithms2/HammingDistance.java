package algorithms2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Programming Assignment #2 

The due date for this quiz is Sun 22 Sep 2013 11:59 PM PDT (UTC -0700).
 * 
 * Question 2
In this question your task is again to run the clustering algorithm from lecture, but on a MUCH bigger graph. So big, in fact, that the distances (i.e., edge costs) are only defined implicitly, rather than being provided as an explicit list.

The data set is here. The format is:
[# of nodes] [# of bits for each node's label]
[first bit of node 1] ... [last bit of node 1]
[first bit of node 2] ... [last bit of node 2]
...
For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1" denotes the 24 bits associated with node #2.

The distance between two nodes u and v in this problem is defined as the Hamming distance--- the number of differing bits --- between the two nodes' labels. For example, the Hamming distance between the 24-bit label of node #2 above and the label "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ in the 3rd, 7th, and 21st bits).

The question is: what is the largest value of k such that there is a k-clustering with spacing at least 3? That is, how many clusters are needed to ensure that no pair of nodes with all but 2 bits in common get split into different clusters?

NOTE: The graph implicitly defined by the data file is so big that you probably can't write it out explicitly, let alone sort the edges by cost. So you will have to be a little creative to complete this part of the question. For example, is there some way you can identify the smallest distances without explicitly looking at every pair of nodes?

 * @author rincon
 *
 */
public class HammingDistance {
	//static String graphPath = "/Users/rincon/Downloads/clustering_big.txt";
	//static String graphPath = "/Users/rincon/Downloads/clustering_test_case_2.txt";
	static String graphPath = "/Users/rincon/Downloads/clustering_big.txt";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*
		 * Read file
		 * Create data structure for Hamming distance
		 * while (not the end of file)
		 * read line
		 * compare against all the elements in the structure.
		 * If it's at distance 0, from an element, ignore.
		 * If it's at distance 1 or 2:
		 * 	Add it to the cluster
		 *  Continue looking in the elements of the other clusters.
		 *  if found then:
		 *  	join the two groups together.
		 *  	continue looking.
		 *  
		 */
		
		//Read file.
		BufferedReader reader = new BufferedReader(new FileReader(graphPath));
		String line = null;

		String [] valuesStringArray;
	
		line = reader.readLine();
		valuesStringArray = line.split(" ");
		int numberNodes = Integer.parseInt(valuesStringArray [ 0 ]);
		int numberBitsPerEntry = Integer.parseInt(valuesStringArray [ 0 ]);
		
		ArrayList<ArrayList<String>> unionFind = new  ArrayList<ArrayList<String>>();
		
		int i = 0;
		while ((line = reader.readLine()) != null) {
			if ( i % 1000 == 0){
				System.out.println ( i + ", " + unionFind.size() );
			}
			//System.out.println ( "Empa" );
			
			processBits ( line, unionFind );
			i++;
		}
		
		//printClusters (unionFind);
		System.out.println (unionFind.size() + " clusters.");
		
	}

	public static void processBits ( String bits, ArrayList<ArrayList<String>> unionFind ){
		
		int clusterAlreadyFound = -1;
		
		for ( int i = 0; i < unionFind.size(); i++){
			
			ArrayList<String> entriesInCurrentCluster = unionFind.get(i);
			
			for ( int j = 0; j < entriesInCurrentCluster.size(); j++){
				int distance = getDistanceIfCloseEnough (bits, entriesInCurrentCluster.get(j), 2);
				
				if ( distance == 0){
					return;
				}
				
				if ( distance != -1){
					if ( clusterAlreadyFound == -1 ){
						entriesInCurrentCluster.add(bits);
						clusterAlreadyFound=i;
						break;
					}
					else {
						//The element is in another cluster. Join this cluster with the other one!
						unionFind.get(clusterAlreadyFound).addAll(entriesInCurrentCluster);
						unionFind.remove(i);
						break;
					}
				}
			}
		}
		if ( clusterAlreadyFound == -1 ){
			ArrayList<String> newEntry = new ArrayList<String> ();
			newEntry.add(bits);				
			unionFind.add(newEntry);
		}

	}
	
	public static void printClusters (ArrayList<ArrayList<String>> unionFind){
		for ( int i = 0; i < unionFind.size(); i++){
			System.out. print ("{ ");
			
			ArrayList<String> entriesInCurrentCluster = unionFind.get(i);
			for ( int j = 0; j < entriesInCurrentCluster.size(); j++){
				System.out.print ( entriesInCurrentCluster.get(j) + ", ");
			}
			System.out.println ("}");
		}
	}
		

	/**
	 * If distance between bits1 and bits2 is greater or equal to maxDistance, will return -1
	 * else, will return the difference.
	 * @param bits1
	 * @param bits2
	 * @param maxDistance
	 * @return
	 */
	static int getDistanceIfCloseEnough ( String bits1, String bits2, int maxDistance ){
		int distanceSoFar = 0;
		for (int i = 0; i < bits1.length();i++){
			if ( bits1.charAt(i) != bits2.charAt(i)){
				distanceSoFar++;
				if ( distanceSoFar > maxDistance){
					return -1;
				}				
			}
		}
		return distanceSoFar;
	}
	
}

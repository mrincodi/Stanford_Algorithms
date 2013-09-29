package algorithms2;

import java.io.IOException;

import algorithms.Mergesort;

public class Clustering {

	static String graphPath = "/Users/rincon/Downloads/clustering_sorted_1.txt";
	//static String graphPath = "/Users/rincon/Downloads/clustering_sorted_other.txt";
	
	/**
	 * 
	 * By: Mario R. Rinc—n-D’az (mrincodi)
	 * 
	 * Algorithms: Design and Analysis, Part 2 
	 * by Tim Roughgarden
	 * Programming Assignment #2
	 * 
	 * The due date for this quiz is Sun 22 Sep 2013 11:59 PM PDT (UTC -0700).
	 * 
	 * Question 1
	 * 
	 * Problem definition:
	 * 
	 * In this programming problem and the next you'll code up the clustering algorithm 
	 * from lecture for computing a max-spacing k-clustering. Download the text file here. 
	 * This file describes a distance function (equivalently, a complete graph with edge costs). 
	 * It has the following format:
		[number_of_nodes]
		[edge 1 node 1] [edge 1 node 2] [edge 1 cost]
		[edge 2 node 1] [edge 2 node 2] [edge 2 cost]
		...
	 * There is one edge (i,j) for each choice of 1²i<j²n, where n is the number of nodes. 
	 * For example, the third line of the file is "1 3 5250", indicating that the distance 
	 * between nodes 1 and 3 (equivalently, the cost of the edge (1,3)) is 5250. You can assume 
	 * that distances are positive, but you should NOT assume that they are distinct.
	 * Your task in this problem is to run the clustering algorithm from lecture on this data set, 
	 * where the target number k of clusters is set to 4. What is the maximum spacing of a 
	 * 4-clustering?
	 * ADVICE: If you're not getting the correct answer, try debugging your algorithm using some 
	 * small test cases. And then post them to the discussion forum!
	 * 
	 * 
	 * @param args Discarded
	 * @throws IOException If the path for the graph doesn't exist.
	 */
	public static void main(String[] args) throws IOException {
		
		/*
		 * Steps:
		 * - Read the graph. Save it in the undirected graph data structure.
		 * - Sort the edges according to their distances.
		 * - Start a union-find structure with this graph.
		 * - While the number of clusters is more than 4, go over the ordered list of links this way:
		 * 	- Let p, q be the vertices:
		 * 	- Merge the clusters that contain p and q. This is, perform:
		 * 		UNION ( FIND (p), FIND (q) )
		 * 
		 * The distance of the link after the clustering is the  maximum spacing of the 4-clustering.
		 * 
		 */
		
		UndirectedGraph g = new UndirectedGraph(graphPath, graphFormat.nNodes_then_n1_n2_cost);
		
		//Here I sort the undirected graph according to the weights of the links. 		
		//g.mergeSort();
		
		UnionFind u = new UnionFind ( g.size );
		
		int i = 0;
		Integer [] link = new Integer [ 3 ];
		System.out.println ( "MR: A ver...");
		while ( u.getNumberOfClusters() >= 4 ){

			link = g.getLinkAndWeight(i);
			
			System.out.println ( link [ 0 ] + " " + link [ 1 ] + " " + link [ 2 ] );

			int j = i + 1;
			System.out.println ( "Iteration " + j );
			
//			if ( j == 128 ){
//				System.out.println ("Veamos");
//				
//			}

			u.union(link [ 0 ], link [ 1 ]);
			i++;
			
			//System.out.println ( "Y la separaci—n es "+ link [ 2 ]);
			//System.out.println ("Hola");
			//System.gc();
			u.printUnionFind ();
			System.out.println ("");
		}
		
		//We finally made to 4 clusters.
		//We need to iterate until we have 3 clusters.
		//The distance in that one will be the answer.
		
		u.printUnionFind ();
		
		link = g.getLinkAndWeight(i-1);
		System.out.println (link [ 2 ]);
		
	}

}

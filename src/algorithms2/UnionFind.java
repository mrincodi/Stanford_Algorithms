package algorithms2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * We are going to implement the Union-Find structure via a hash with this structure:
 * leaderValue -> < value1, value2,... >
 * The values are sorted numerically.
 * leaderValue is always equal to value1.
 * Finding a value means: looking for the value as a leaderValue. If not found, 
 * 	go over all the lists until you find the value. Return the leader value.
 * Union of two sets mean: Looking which one has the most elements. Then adding the elements
 * 	of the smaller set into the largest set. Leave the leader name as value1. 
 * 	The joining must leave an ordered set.
 * 
 * @author rincon
 *
 */
public class UnionFind {

	HashMap<Integer, ArrayList <Integer>> h= new HashMap <Integer, ArrayList <Integer>>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Create a basic UnionFind structure in which every element is its leader.
	 * @param n The number of elements.
	 */
	public UnionFind (int n){
		//TODO
		
		int i = 1;
		ArrayList <Integer> al = new ArrayList <Integer> ();
		while ( i <= n ){
			al = new ArrayList <Integer> ();
			al.add(i);
			h.put(i, al);
			i++;
		}
		
	}
	
	/**
	 * Returns the number of clusters (number of leader values).
	 * @return the number of clusters.
	 */
	public int getNumberOfClusters (){
		return this.h.size();
	}
	
	/**
	 * Union of two clusters
	 * @param value1
	 * @param value2
	 */
	public void union ( int value1, int value2){

		//Look for the cluster lower or equal to each key.
		
		int leader1 = find ( value1 );
		int leader2 = find ( value2 );
		
		
		if ( leader1 != leader2 ){
			//swap the values if one key2 is less than key1
			
			if ( leader2 < leader1 ){
				int tempy = leader1;
				leader1 = leader2;
				leader2 = tempy;
			}
			
			ArrayList <Integer > list1 = h.get(leader1);
			ArrayList <Integer > list2 = h.get(leader2);
			ArrayList <Integer > finalList = new ArrayList <Integer > ();
			
			//Set up the key value you are going to leave.
			
			if ( list1.size() < list2.size() ){
				list2.addAll(h.get(leader1));
				finalList = list2;
				//list1=null;
			}
			else {
				list1.addAll(h.get(leader2));
				finalList = list1;
				//list2=null;
			}
			
			h.remove(leader2);
			h.put(leader1, finalList);
			//System.gc();
		}
	}
	
	public int find ( int value ){
		
//		if ( value == 393 ){
//			System.out.println ("WHAAAT???");
//		}
		
		if ( h.containsKey(value)){
			return value;
		}
				
		//Iterate over keys.
		
		Object [] keys = h.keySet().toArray();
		
		Arrays.sort(keys);
		
		for ( int i = 0; i < keys.length; i++){
			int key = (Integer) keys [ i ];
	        //System.out.println(key + " = " + h.get(key));
	        
	    	ArrayList <Integer> currentArray = h.get(key);

	    	for ( int j = 0; j < currentArray.size(); j++){
	    		if ( currentArray.get(j) == value){
	    			return key;
	    		}
	    	}      
	        
		}
		
		return -1;
		
	}
	
	public void printUnionFind (){
		
		Object [] keys = h.keySet().toArray();
		
		Arrays.sort(keys);
		
		for ( int i = 0; i < keys.length; i++){
			int key = (Integer) keys [ i ];
	        System.out.println(key + " = " + h.get(key));
		}
		System.out.println ( "TENGO " + keys.length + " keys");
	}
	
}

package algorithms2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Knapsack {

	int W = 0; //Maximum weight of the knapsack.
	int N = 0; //Number of elements.
	int [] w; //Array of weights of the elements
	int [] v; //Array of values of the elements.
	
	public Knapsack ( String knapsackFilePath ) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(knapsackFilePath));
		String line = null;
		
		String [] valuesStringArray;
		line = reader.readLine();
		valuesStringArray = line.split(" ");

		this.W = Integer.parseInt(valuesStringArray [ 0 ]);
		this.N = Integer.parseInt(valuesStringArray [ 1 ]);

		this.w = new int [ N + 1 ];
		this.v = new int [ N + 1 ];
		
		this.w [ 0 ] = -1;
		this.v [ 0 ] = -1;
		
		int i = 1;
		while ((line = reader.readLine()) != null) {
			valuesStringArray = line.split(" ");
			
			this.v [ i ] = Integer.parseInt( valuesStringArray [ 0 ] );
			this.w [ i ] = Integer.parseInt( valuesStringArray [ 1 ] );
			i++;
		}

		
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		

	}

}

package algorithms2;

import java.io.IOException;


/**
 * 
 * Question 1
In this programming problem and the next you'll code up the knapsack algorithm from lecture. Let's start with a warm-up. Download the text file here. This file describes a knapsack instance, and it has the following format:
[knapsack_size][number_of_items]
[value_1] [weight_1]
[value_2] [weight_2]
...
For example, the third line of the file is "50074 659", indicating that the second item has value 50074 and size 659, respectively.

You can assume that all numbers are positive. You should assume that item weights and the knapsack capacity are integers.

In the box below, type in the value of the optimal solution.

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases. And then post them to the discussion forum!

 * Date due: 2013-09-29
 * 
 * @author rincon
 *
 */

public class KnapsackProblem {

//	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1Mini.txt";
	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1.txt";
//	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack_big.txt";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Read knapsackFile:
		Knapsack k = new Knapsack(knapsackFilePath);
		
		int [] [] A = new int [ k.N + 1 ][ k.W + 1 ];
		
		for ( int i = 1; i <= k.N; i++ ){
			for ( int x = 0; x <= k.W; x++ ){
				
				
				if ( x < k.w [i] ){
					A [ i ] [ x ] = A [ i - 1 ][ x ];
				}
				else{
					A [ i ] [ x ] = Math.max(
							A [ i - 1 ][ x ],
							A [ i - 1 ][ x - k.w [ i ] ] + k.v [ i ] );
				}				
			}
		}
		
		for (int i = 2; i == 2; i++){
			for ( int x = 0; x <= k.W; x++){
				System.out.print ( A [ i ] [ x ] + " ");
			}
			System.out.println ();
		}
		
		System.out.println ( A [ k.N ][k.W] );
		
		
	}
	
	
	
	

}

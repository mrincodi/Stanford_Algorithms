package algorithms2;

import java.io.IOException;
import java.util.ArrayList;

/**
 * NOTE: This program is not finished! Doesn't work! See KnapsackProblemBig instead.
 * 
 * This problem also asks you to solve a knapsack instance, but a much bigger one.

Download the text file here. This file describes a knapsack instance, and it has the following format:
[knapsack_size][number_of_items]
[value_1] [weight_1]
[value_2] [weight_2]
...
For example, the third line of the file is "50074 834558", indicating that the second item has value 50074 and size 834558, respectively. As before, you should assume that item weights and the knapsack capacity are integers.

This instance is so big that the straightforward iterative implemetation uses an infeasible amount of time and space. So you will have to be creative to compute an optimal solution. One idea is to go back to a recursive implementation, solving subproblems --- and, of course, caching the results to avoid redundant work --- only on an "as needed" basis. Also, be sure to think about appropriate data structures for storing and looking up solutions to subproblems.

In the box below, type in the value of the optimal solution.

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases. And then post them to the discussion forum!
 *
 * ENHANCEMENTS:	
 * Use only 2 arrays, not the whole matrix! We don't need to give the final list.
 * Before getting 
 * 
 *
 *
 * Date due: 2013-09-29
 * 
 * @author rincon
 *
 */
public class KnapsackProblemBigEnhanced {
	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1Mini.txt";
//	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1.txt";
//	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack_big.txt";

	static Knapsack k;
	
	static ArrayList < ArrayList < Integer [] >> A = new ArrayList < ArrayList < Integer [] >> ();
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Read knapsackFile:
		k = new Knapsack(knapsackFilePath);
		
		KnapsackIntelligentArray A = new KnapsackIntelligentArray(k.W);
		
		int alfa, beta, w, v;
		
		for ( int i = 1; i <= k.N; i++ ){
			
			alfa = k.W;
			w = k.w[i];
			v = k.v[i];
			
			while ( alfa > 0 ){
				beta = alfa - w;
				
				if ( beta < 0 ){
					alfa = -1; // So it leaves the cycle.
				}
				else {
					long PreviousValueInAlfa = A.getPosValue ( alfa );
					long PreviousValueInBeta = A.getPosValue ( beta );
					long PreviousValueInBetaPlusValue = PreviousValueInBeta + v;
					
					long newValue = Math.max ( PreviousValueInAlfa, PreviousValueInBetaPlusValue);
					
					A.assign ( alfa, newValue );
					
					int difference = A.findNextDifference ( alfa, beta );

					alfa=alfa-difference;
					
				}
			}
		}
				
			
			
//			
//			
//			System.out.println (i + " " + A.get(i -1).size());
//			for ( int x = 0; x <= k.W; x++ ){
//				
//				int value = -1;
//				if ( x < k.w [i] ){
//					value = get ( i - 1, x);
//					
//					assign ( i, x, value);
//				}
//				else{
//					
//					value = Math.max(
//					get ( i - 1, x ),
//					get ( i - 1, x - k.w [ i ]) + k.v [ i ] );
//					
//					assign ( i, x, value);
//					
////					A [ i ] [ x ] = Math.max(
////							A [ i - 1 ][ x ],
////							A [ i - 1 ][ x - k.w [ i ] ] + k.v [ i ] );
//				}				
//			}
//		}
		System.out.println ( get ( k.N, k.W  ));
//		
	}
	
	static int get ( int i, int x ){
		
		ArrayList < Integer [] > iArray = A.get(i); 
		
		int currentX = -1;
		int currentValue = -1;
		Integer currentPair [];
		for ( int index = iArray.size()-1; index >= 0; index-- ){
			currentPair =  iArray.get(index);
			currentX = currentPair [ 0 ];
			currentValue = currentPair [ 1 ];
			
			if ( x >= currentX){
				return currentValue;
			}
		}
			
		return 0;
		
	}
	
	
	static void assign ( int posI, int posX, int value ){
		//Go to the posIth position in the array.
		
		ArrayList < Integer [] > posIArray = A.get(posI); 
		Integer [] newPair = new Integer [ 2 ];
		newPair [ 1 ]= value;
		for ( int index = posIArray.size() - 1; index >= 0 ; index-- ){
			if ( posIArray.get(index) [ 0 ] < posX  ){ // If weight I'm analyzing is smaller than the one I have
				//Compare values.
				if ( posIArray.get(index) [ 1 ] == value ){
					return;
				}
				newPair [ 0 ] = posX;
				posIArray.add(index + 1, newPair);
				return;
			}
		}
	}
	
			
			
			
//			else {
//				int newValue = Math.max(
//						get ( posI - 1, posX ),
//						get ( posI - 1, posX - k.w [ posI ]) + k.v [ i ] );
//				A [ i ] [ x ] = Math.max(
//				A [ i - 1 ][ x ],
//				A [ i - 1 ][ x - k.w [ i ] ] + k.v [ i ] );
				
				

	
}

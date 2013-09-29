package algorithms2;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
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
 * Date due: 2013-09-29
 * 
 * @author rincon
 *
 */
public class KnapsackProblemBigNotSoEnhanced {
//	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1Mini.txt";
	static String knapsackFilePath = "/Users/rincon/Downloads/knapsack1.txt";
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

		long [] A = new long [ k.W + 1];
		long [] A_prime = new long [ k.W + 1];
		
		for ( int i = 1; i <= k.N; i++ ){
			int w = k.w[i];
			int v = k.v[i];
			
			A_prime = new long [ k.W + 1];

			for ( int x = k.W; x > w; x--){
				long valor_previo = A [ x ];
				long posible_valor_nuevo = A [ x - w] + v;
				long valor_nuevo = Math.max ( valor_previo, posible_valor_nuevo);
				
				A_prime [ x ] = valor_nuevo;
				
				//ÁA encontrar a d—nde nos tenemos que mover!
				
				x = encontrar_siguiente_x ( x, w, A );
				
			}
			
			// Ahora, hacer el nuevo arreglo.
			
			long valor = A_prime [ k.W ]; //Debe ser diferente de 0;
			for ( int x = k.W; x > w; x--){
				if ( A_prime [ x ] != 0){
					valor = A_prime [ x ];
				}
				
				A [ x ] = valor;
			}
			//El resto de A no cambia.
		}
		
		System.out.println ( A [ k.W ]);
	}


	private static int encontrar_siguiente_x(int x, int w, long[] A) {
		
		long valor = A [ x ];
		int i = 0;
		
		
		if ( x-w <= w ){
			valor = A [ x ];
			for ( i=x; i > w; i-- ){
				if ( valor != A [ i ]){
					break;
				}
			}
			return i;
		}
		
		//else
		
		int diff1 = 0, diff2 = 0;
		valor = A [ x ];
		for ( i=x; i > x-w; i-- ){
			if ( valor != A [ i ]){
				break;
			}
		}
		int x1 = i;
		diff1 = x - i;
		
		valor = A [ x - w ];
		for ( i = x -w; i > w; i--){
			if ( valor != A [ i ]){
				break;
			}
		}
		int x2 = i;
		diff2 = x -w - i;
		
		if ( diff1 > diff2) return x1;
		return x2;
		
		
//		
//		
//		for (; i < w; i--){
//			if ( A [ i ] != valor ){
//				break;
//			}
//		}
//		
//		diff1 = x - i;	 //Posiciones recorridas.
//		
//		

	}
		
//	static int encontrar_diferencia ( long [] A, int x ){
//		int index = x;
//		int diferencia = 0;
//		long valor = A [ index ];
//		
//		long nuevo_valor = valor;
//		while ( index > 0 ){
//			nuevo_valor = A [ index ];
//			if ( nuevo_valor != valor){
//				return diferencia;
//			}
//			diferencia++;
//			index--;
//		}
//		
//		return index;
//	}
//		
//		
//		
		
		
		
		
		
		
		
		
//		
//		
//		
//		
//		
//		
//		/* Now, we'll make a structure with this format:
//		 * 
//		 * 
//		 */
//		
//		//ArrayList < ArrayList < Integer >> A = new ArrayList < ArrayList < Integer >> ();
//			
//		Integer [] zeros = {0,0};
//		
//		for ( int i = 0; i <= k.N; i++ ){
//			ArrayList <Integer []> simple = new ArrayList <Integer []>();
//
//			simple.add(zeros);
//
//			A.add(simple);
//		}
//
//
//		for ( int i = 1; i <= k.N; i++ ){
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
//		System.out.println ( get ( k.N, k.W  ));
//		
//	}
//	
//	static int get ( int i, int x ){
//		
//		ArrayList < Integer [] > iArray = A.get(i); 
//		
//		int currentX = -1;
//		int currentValue = -1;
//		Integer currentPair [];
//		for ( int index = iArray.size()-1; index >= 0; index-- ){
//			currentPair =  iArray.get(index);
//			currentX = currentPair [ 0 ];
//			currentValue = currentPair [ 1 ];
//			
//			if ( x >= currentX){
//				return currentValue;
//			}
//		}
//			
//		return 0;
//		
//	}
//	
//	
//	static void assign ( int posI, int posX, int value ){
//		//Go to the posIth position in the array.
//		
//		ArrayList < Integer [] > posIArray = A.get(posI); 
//		Integer [] newPair = new Integer [ 2 ];
//		newPair [ 1 ]= value;
//		for ( int index = posIArray.size() - 1; index >= 0 ; index-- ){
//			if ( posIArray.get(index) [ 0 ] < posX  ){ // If weight I'm analyzing is smaller than the one I have
//				//Compare values.
//				if ( posIArray.get(index) [ 1 ] == value ){
//					return;
//				}
//				newPair [ 0 ] = posX;
//				posIArray.add(index + 1, newPair);
//				return;
//			}
//		}
//	}
//	
//			
//			
//			
//			else {
//				int newValue = Math.max(
//						get ( posI - 1, posX ),
//						get ( posI - 1, posX - k.w [ posI ]) + k.v [ i ] );
//				A [ i ] [ x ] = Math.max(
//				A [ i - 1 ][ x ],
//				A [ i - 1 ][ x - k.w [ i ] ] + k.v [ i ] );
				
				

	
}

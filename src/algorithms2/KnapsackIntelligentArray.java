package algorithms2;

import java.util.ArrayList;

/**
 * NOTE: This program is not finished! Doesn't work! See KnapsackProblemBig instead.
 * @author rincon
 *
 */
public class KnapsackIntelligentArray {

	ArrayList < Long [] > A = new ArrayList < Long [] > ();
	
	public KnapsackIntelligentArray(int n) {
		
		Long [] newPair = new Long [ 2 ];
		newPair [ 0 ] = (long) n;
		newPair [ 1 ] = (long) 0;
		
		this.A.add( newPair );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findNextDifference(int alfa, int beta) {
		// TODO Auto-generated method stub
		
		//Let's find the index value in the array.
		int alfadiff = -1, betadiff = -1;

		alfadiff = findDifference ( alfa );
		betadiff = findDifference ( beta );
		
		return Math.min(alfadiff, betadiff);
	}
	
	public int findDifference ( int alfa ){
		//Let's find the index value in the array.
		int alfadiff = -1;
		for ( int index = 0; index < this.A.size() -1 ; index++) {
			int currentPos = getPos ( index );
			if ( currentPos == index){
				alfadiff = getPos ( index ) - getPos ( index + 1 );
				return alfadiff;
			}
		}
		
		if ( alfadiff == -1 ){
			alfadiff = getPos ( this.A.size() -1 );
			return alfadiff; 
		}
		return alfadiff;
	}
	
	

	public void assign(int i, long v) {
		for ( int index = 0; index < this.A.size(); index++) {
			int currentPos = getPos ( index );
			if ( currentPos == i){
				//Change the array!
				setValue (index, v);
				return;
			}
			
			if  ( currentPos < i ) {
				insertNewValue (index, i, v);
				return;
			}
			
		}
	}

	private int getPos(int index) {
		return (int) (long) this.A.get(index) [ 0 ];
	}
	
	private Long getValue ( int index ){
		return this.A.get(index) [ 1 ];
	}
	
	public long getPosValue(int alfa) {

		for ( int index = 0; index < this.A.size(); index++) {
			int currentPos = getPos ( index );
			if ( currentPos == alfa){
				return getValue ( index );
			}
			if ( currentPos < alfa){
				return getValue ( index - 1 );
			}
		}

		return 0;
	}
	
	private void setValue (int index, long value){
		this.A.get(index)[1]=value;
		
		//If the upper value is the same as this one.
		if ( index > 0 ){
			if ( getValue ( index - 1 ) == value ){
				this.A.remove(index);
			}
		}
	}
	
	private void insertNewValue ( int index, int i, long v ){
		
		Long [] newPair = new Long [ 2 ];
		newPair [ 0 ] = (long) i;
		newPair [ 1 ] = (long) v;
		
		this.A.add( index, newPair );

		//If the upper value is the same as this one.
		if ( index > 0 ){
			if ( getValue ( index - 1 ) == v ){
				this.A.remove(index);
			}
		}
		
	}

	


}

package algorithms;

import java.util.ArrayList;

public class Heap {
	boolean MIN = true;	//MIN = true means it supports Extract MIN; false means it supports extract MAX.
	ArrayList < Long > h ;
	static Long NO_VALUE = new Long ( -1 );	//To indicate that we are out of bounds (parent of an empty heap, for example).
	static int OUT_OF_BOUNDS = -1;
	
	public Heap (boolean b){
		this.MIN=b;
		this.h = new ArrayList < Long > ();
		h.add(new Long (0));	//We don't use this value. It just eases our calculations to have it.
	}
	
	/**
	 * 
	 * @return How many elements are in the heap.
	 */
	public int size (){
		return h.size() - 1;
	}
	
	/**
	 * Just returns the top value. Does not extract it.
	 * @return
	 */
	public Long getTop (){
		if ( size() > 0)
			return this.h.get(1);
		
			return NO_VALUE ;
	}
	
	private int getPositionOfParent ( int pos ){
		if ( pos == 1 ) return OUT_OF_BOUNDS; 
		return ( pos / 2 );
	}
	
	private Long getValueOfParent ( int pos ) {
		if ( pos == 1 ) return NO_VALUE;
		return this.h.get(this.getPositionOfParent(pos));
	}
	
	/**
	 * If this method returns true, and parent and son and entered, 
	 * there is a need to accommodate (swap values when bubbling up/down).
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean compare ( Long a, Long b ){
		if ( this.MIN ){
			if ( a > b ) return true;
			return false;
		}
		else {
			if ( a < b ) return true;
			return false;
		}
	}
	
	private void swap ( int pos1, int pos2 ){
		Long val1 = h.get(pos1);
		long val2 = h.get(pos2);
		h.remove(pos1);
		h.add(pos1, val2);
		h.remove(pos2);
		h.add(pos2,val1);
	}
	
	
	/**
	 * Just add to the end and bubble up.
	 * @param x
	 */
	public void insert ( Long x ){
		//Always, insert in the bottom and "bubble up" if necessary.
		
		h.add( x );
		
		bubbleUp( size () );

	}
		
	/**
	 * Now I realize I won't be needing this one for Medians, but anyways.
	 * @param pos
	 */
	public void bubbleUp (int pos){
		
		if ( pos > 1 ){
			
			int parentPos = getPositionOfParent(pos);
			if ( compare ( getValueOfParent(pos), h.get(pos) )){
				swap ( pos, parentPos );
				bubbleUp ( parentPos );
			}
		}
	}
	
	/**
	 * This should be used extensively.
	 * @return
	 * @throws Exception 
	 */
	public Long extractTop () throws Exception{
		Long valueToReturn = getTop();
		
		if ( valueToReturn == NO_VALUE ) return valueToReturn;
		
		h.remove(1);

		if ( size() > 1){
			
			Long valueOfLastLeaf = h.get(size());
			h.remove(size());
			h.add(1, valueOfLastLeaf);
			bubbleDown ( 1 );
		}
		
		return valueToReturn;
	}
	
	/** 
	 * This will be challenging.
	 * 
	 * @param pos
	 * @throws Exception 
	 */
	public void bubbleDown ( int pos ) throws Exception {
		int size = size();
		if ( pos > size ) throw new Exception ("Value out of range!");
		
		Long value = h.get(pos);
		
		int posChild1 = 2 * pos;
		int posChild2 = posChild1 + 1;
		
		if (size < posChild1 ) return; //We are a leaf.
		if (size == posChild1 ) {
			Long valueChild1 = h.get(posChild1);
			//Compare against the only leaf.
			if ( compare ( value, valueChild1 )) swap ( pos, posChild1 );
		}
		else {
			//From the two children, select the smaller (or greater)
			Long valueChild1 = h.get(posChild1);
			Long valueChild2 = h.get(posChild2);

			int posToCompare;
			Long valueToCompare;
			
			//MR: Ojo: Creo que esto est‡ mal.
			if ( compare (valueChild1, valueChild2 )){
				posToCompare = posChild2;
				valueToCompare = valueChild2;
			}
			else{
				posToCompare = posChild1;
				valueToCompare = valueChild1;
			}
				
			//Compare against valueToCompare.
			if ( compare (value, valueToCompare)){
				swap (pos, posToCompare);
				bubbleDown (posToCompare);
			}
		}
	}
	
	public void displayHeapArray (){
		for ( int i = 0 ; i <= size(); i++ ){
			System.out.print(h.get(i) + " ");
		}
		System.out.println ("");
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		long [] array = { 7,5,9,6,4,8,2,1,3 };
		//long [] array = { 7,5,9,6,4 };
		
		Heap hh = new Heap (true);
		for ( int i = 0; i < array.length; i++ ){
			hh.insert(array [ i ]);
			hh.displayHeapArray ();
		}

		hh.displayHeapArray ();
	
		for ( int i = array.length - 1; i >= 0 ; i-- ){
			Long qq = hh.extractTop();
			System.out.println ( "Extracted " + qq);
			hh.displayHeapArray ();
		}

		
		
	}

}

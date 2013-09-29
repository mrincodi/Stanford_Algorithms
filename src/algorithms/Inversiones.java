package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Inversiones {
	
	class listaYNœmeroDeInversiones {
		long [] lista;
		long nœmeroDeInversiones;
		
		listaYNœmeroDeInversiones (long [] l, long ndi ){
			this.lista=l;
			this.nœmeroDeInversiones=ndi;
		}
		
		
	}
	public static void main ( String args [] ) throws IOException{
		
		Inversiones m = new Inversiones ();
		String pathArchivo =  "/Users/rincon/Downloads/IntegerArray.txt";

		long [] array = m.leerArchivo ( pathArchivo );
		//long [] array = { 5,4,3,2,1,6};
		listaYNœmeroDeInversiones lyndi =  m.merge ( m.new listaYNœmeroDeInversiones ( array, 0) );
		
		long [] array2 = lyndi.lista;
		for ( int i = 0; i < array2.length; i++){
			System.out.print ( array2 [ i ] + " ");
		}
		System.out.println ( "\n" + lyndi.nœmeroDeInversiones );
	}

	public listaYNœmeroDeInversiones merge ( listaYNœmeroDeInversiones lyndi ){
		//divide in 2

		long [] array = lyndi.lista;
		listaYNœmeroDeInversiones result = lyndi;
		if ( array.length > 1 ){ 
			int middle = array.length / 2;
			long [] lowerArray = Arrays.copyOfRange(array, 0, middle);
			long [] upperArray = Arrays.copyOfRange(array, middle, array.length);
			
			listaYNœmeroDeInversiones lowerLyndi = new listaYNœmeroDeInversiones ( lowerArray, 0 );
			listaYNœmeroDeInversiones upperLyndi = new listaYNœmeroDeInversiones ( upperArray, 0 );

			listaYNœmeroDeInversiones sortedLowerLyndi = merge ( lowerLyndi );
			listaYNœmeroDeInversiones sortedUpperLyndi = merge ( upperLyndi );
			
			result = sortArraysAndCountInversions ( sortedLowerLyndi, sortedUpperLyndi);
			
		}
		return result;
		
	}
	
	listaYNœmeroDeInversiones sortArraysAndCountInversions ( listaYNœmeroDeInversiones sortedLowerLyndi, listaYNœmeroDeInversiones sortedUpperLyndi ){
		
		long [] sortedLower = sortedLowerLyndi.lista;
		long [] sortedUpper = sortedUpperLyndi.lista;
		long [] sortedArray = new long [ sortedLower.length + sortedUpper.length ];

		int i = 0, j = 0, k = 0;
		long inversiones = 0;
		for ( ; k < sortedLower.length + sortedUpper.length;){
			if ( sortedLower [ i ] <= sortedUpper [ j ] ){
				sortedArray [ k ] = sortedLower [ i ];
				i++;
			}
			else {
				sortedArray [ k ] = sortedUpper [ j ];
				j++;
				inversiones += sortedLower.length - i;
			}
			
			k++;
			
			if ( i == sortedLower.length ){
				for ( ; j < sortedUpper.length; j++ ){
					sortedArray [ k ] = sortedUpper [ j ];
					k++;
				}
			}
			else if ( j == sortedUpper.length ){
				for ( ; i < sortedLower.length; i++ ){
					sortedArray [ k ] = sortedLower [ i ];
					k++;
				}
			}
		}
		
		inversiones += sortedLowerLyndi.nœmeroDeInversiones + sortedUpperLyndi.nœmeroDeInversiones;
		listaYNœmeroDeInversiones lyndi = new listaYNœmeroDeInversiones ( sortedArray, inversiones);
		return lyndi;
	}
	
	long [] leerArchivo ( String pathArchivo ) throws IOException{
		long [] nœmeros = new long [ 100000 ];
		BufferedReader reader = new BufferedReader(new FileReader(pathArchivo));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			nœmeros [ i ] = Integer.parseInt(line);
			i++;
		}
		
		return nœmeros;

		
	}
}

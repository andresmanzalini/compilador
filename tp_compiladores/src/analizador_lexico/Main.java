package analizador_lexico;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.File;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub    
	    
	    AnalizadorLexico a = new AnalizadorLexico();
		
	    a.abrirCargarArchivo(); //primero intenta abrir archivo. Si lo abre correctamente, lo lee

	    System.out.println("\n COMIENZA A LEER TOKENS \n");
	    
	    
	    while (a.quedanTokens()) {
	    	//a.yylex();
	    	Token yylex = a.yylex();
	    	if (yylex != null) {
	    		System.out.println("\n TOKEN -> " + yylex.getLexema() + " , " + yylex.getTipo());
	    	} else {
	    		System.out.println("NO HAY MAS TOKENS, MANIJA! \n");
	    	}
	    }
	   	
	    System.out.println();   
	    a.mostrarTablaTokens();
	    a.mostrarTablaSimbolos();
	}

}
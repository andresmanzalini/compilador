package analizador_lexico;

import tabla_simbolos.TablaDeSimbolos;

public class EntregarTokenYReiniciar extends AccionSemantica {

	private TablaDeSimbolos tds;
	
	
	private Token t;
	//private int sgte_id = 300;
	private int linea_actual;
	
	
	//Accion Semantica Final 
	public EntregarTokenYReiniciar(TablaDeSimbolos tds){
		this.tds = tds;
		this.t = null;
	}
	
	
	
	public void ejecutar(char c, int nro_linea) { 
		this.linea_actual = nro_linea;
		//defino los tipos de token simples que puede entregar al sintactico
		if (c == '=') {  super.tipo_token = "IGUAL"; };
		if ((c == ';') | (c == ':') | (c == ',')) { super.tipo_token = "PUNT"; };
		if ((c == '+') || (c == '-') || (c == '*') || (c == '/')) { super.tipo_token = "OP"; }
		
		
		//PALABRAS RESERVADAS SAPE
		if (super.buffer.equals("INTEGER") | super.buffer.equals("FLOAT")) {
			super.tipo_token="PALABRA_RESERVADA" ;
		}
		
		if (super.buffer.equals("IF") | super.buffer.equals("ELSE") | super.buffer.equals("END_IF") | super.buffer.equals("PROC") | super.buffer.equals("NI") | super.buffer.equals("REF")) {
			super.tipo_token="PALABRA_RESERVADA" ;
		}
		
		if (super.buffer.equals("LOOP") | super.buffer.equals("UNTIL")) {
			super.tipo_token="PALABRA_RESERVADA" ;
		}
		
		//int id_tipo = this.tt.getIdTipo(super.tipo_buffer); 
		//System.out.println("ID TIPO TOKEN -> "+id_tipo);
		
		//if (super.token_valido) {
		this.t = new Token(super.buffer, super.tipo_token, nro_linea, super.tipo_variable);//, id_tipo);
		//}
		//la sacudo, en la tabla de simbolos decide si la guarda o no
		//this.tds.addToken(t);
		
	
		super.tipo_token="";
		super.buffer = ""; //despues de entregar token lo limpio
		//super.token_valido=false;
	}
	
	
	public Token getToken() {
		//super.buffer = ""; //y despues de entregar token lo limpio?
		return t;
		}
}

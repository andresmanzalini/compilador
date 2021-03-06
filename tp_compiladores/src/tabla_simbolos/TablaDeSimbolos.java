package tabla_simbolos;

import java.util.Enumeration;
import java.util.Hashtable;

import analizador_lexico.Token;


public class TablaDeSimbolos {

	//de entrada guardo tokens. si surge alguna mejora podemos hacer una clase envolvente que reciba u token y lo extienda
	private TablaDeTipos tdt;
	
	private Hashtable<String, Token> TSimbolos;
	
	
	
	public TablaDeSimbolos() throws IllegalArgumentException, IllegalAccessException {
		this.tdt = new TablaDeTipos();
		this.TSimbolos = new Hashtable<String, Token> ();
		
		//this.tdt.cargarTablaConMacros();
	}
	
	
	public void addToken(Token t) {
		
		if (tdt.existe(t.getLexema())){
			//System.out.println("Entro aca???");
			t.setIdTipo(tdt.getIdTipo(t.getLexema()));
			//System.out.println("que onda? "+t.getLexema()+" , "+t.getIdTipo());

		}
		//if t.getLexema() existe en tabla de palabra reservada 
		//    no agrego el token y seteo el id con la palabra reservada
		
		
		//para los tokens simples asigno su identificador ASCII
		if (t.getTipo().equals("PUNT") | t.getTipo().equals("IGUAL") | t.getTipo().equals("OP") | t.getTipo().equals("PARENTESIS") | t.getTipo().equals("LLAVES") | t.getTipo().equals("COMP")) {
			int ascii = (int)t.getLexema().charAt(0);
			t.setIdTipo(ascii);
		}
		
		
		
		if (t.getTipo().equals("ID")){
			int id_tipo = this.tdt.getIdTipo(t.getTipo());
			t.setIdTipo(id_tipo);
			this.TSimbolos.put(t.getLexema(), t);
			//System.out.println(" Tabla -> APARECE UN ID  "+ t.getLexema() + " , " + t.getTipo() + " a la Tabla de Simbolos \n");
		}
		if (t.getTipo().equals("CTE")){
			int id_tipo = this.tdt.getIdTipo(t.getTipo());
			t.setIdTipo(id_tipo);
			//agrego identificador de tipo y lo guardp
			this.TSimbolos.put(t.getLexema(), t);
			//System.out.println(" Tabla de Simbolos -> APARECE UNA CTE "+ t.getLexema() + " , " +t.getTipo() + " \n");
		}
		if (t.getTipo().equals("CADENA")){
			int id_tipo = this.tdt.getIdTipo(t.getTipo());
			t.setIdTipo(id_tipo);
			//agrego identificador de tipo y lo guardo
			this.TSimbolos.put(t.getLexema(), t);
			System.out.println("APARECE UNA CADENA -> la agrego "+ t.getTipo() + " a la Tabla de Simbolos \n");
		}
				
	}
	
	
	public boolean existe(String b) {
		//System.out.println("Existe " + b + " en la Hash???? "+this.id_tipo.contains(b));
		return this.TSimbolos.containsKey(b);
	}
	
	
	public boolean correctamenteDefinido(Token t) {
		if (this.existe(t.getLexema())) {
			//si el lexema tiene un @ -> return correcto
			if (t.getLexema().contains("@")) {
				System.out.println("\n\n Simbolo  "+t.getLexema() +"  correctamente definido en TSym \n");
				return true;
			} else {
				System.out.println("\n ERROR -> "+t.getLexema()+" NO tiene ambito definido -> no esta inicializada correctamente");
				return false;
			}
		}
		return false;
	}
	
	
	public Token getSimbolo(String key) {
		return this.TSimbolos.get(key);
	}
	
	public void eliminarSimbolo(String key) {
		this.TSimbolos.remove(key);
	}
	
	
	public void removerTokensInvalidos() {
		Hashtable<String, Token> t_sym = new Hashtable<String, Token> ();
		
		Enumeration enumeration_keys = this.TSimbolos.keys();
		Enumeration enumeration = this.TSimbolos.elements();
		
		while (enumeration.hasMoreElements()) {
			Token t = (Token)enumeration.nextElement();
			String llave = enumeration_keys.nextElement().toString();
			
			//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());			
			if (t.getTipo().equals("CTE")) {
				//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());			
				t_sym.put(llave, t);
			}
			
			if ((t.getTipo().equals("ID") | t.getUso().equals("procedimiento")) && tieneNameMangling(llave)) {
				//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());			
				t_sym.put(llave, t);
			}
			
			//if (t.getUso().equals("procedimiento")) {
				//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());			
				//t_sym.put(llave, t);
			//}
			
		}
		
		this.TSimbolos=t_sym;
		
		System.out.println("\n");
	
	}
	
	
	public boolean tieneNameMangling(String k) {
		for (int i=0; i<k.length(); i++) {
			if (k.charAt(i) == '@') {
				return true;
			}
		}
		return false;
	}
	
	
	public String getTablaSimbolosString() {
		String aux = "";
		
		Enumeration enumeration_keys = this.TSimbolos.keys();
		Enumeration enumeration = this.TSimbolos.elements();
		
		while (enumeration.hasMoreElements()) {
			Token t = (Token)enumeration.nextElement();
			String llave = enumeration_keys.nextElement().toString();
			
			if (llave.length() > 8) {
				
					if (llave.length() > 16) {
						aux += llave +" , "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso()+"\n";
						//System.out.println(llave +" , "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
					} else {
						aux += llave +" 	, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso()+"\n";
						//System.out.println(llave +" 	, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
					}

			} else {
				aux += llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso()+"\n";
				//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
			}
			
		}	
		
		return aux;
	}
	
	
	public void mostrarSimbolos() {
		System.out.println("\n\n Tabla de Simbolos \n");
		String tab1 = "	";
		String tab2 = "		";
		
		System.out.println("Lexema	, Token	");
		Enumeration enumeration_keys = this.TSimbolos.keys();
		
		Enumeration enumeration = this.TSimbolos.elements();
		while (enumeration.hasMoreElements()) {
			Token t = (Token)enumeration.nextElement();
			String llave = enumeration_keys.nextElement().toString();
			
			//System.out.println(enumeration_keys.nextElement() +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
			//System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
			
			if (llave.length() > 8) {
				
					if (llave.length() > 16) {
						System.out.println(llave +" , "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
					} else {
						System.out.println(llave +" 	, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
					}

			} else {
				
				System.out.println(llave +" 		, "+t.getTipo()+ " 	, "+t.getTipoVar()+"	,  "+t.getUso());
			}
			
		}
		System.out.println("\n");
	}

}

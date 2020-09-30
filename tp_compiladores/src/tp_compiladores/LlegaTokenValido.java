package tp_compiladores;

public class LlegaTokenValido extends AccionSemantica {
	private TablaTokens ttok;
	private TablaSimbolos tsym;
	
	//private 
	
	private int id = 20;
	
	
	public LlegaTokenValido(TablaTokens tt, TablaSimbolos ts) {
		this.ttok = tt;
		this.tsym = ts;
	}
	
	
	public void ejecutar(char c, int nro_linea) {
		System.out.println("AS3 -> Voy a Ef con un Token Valido "+ super.buffer);
		
		super.buffer += c; 
	}
	
	
	public Token getToken() {
		return null;
	}
}

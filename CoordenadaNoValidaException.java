
public class CoordenadaNoValidaException extends Exception {
	public CoordenadaNoValidaException(int f, int c){
		super("("+f+","+c+")"); 
	}
}

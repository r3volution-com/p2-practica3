
public class CoordenadaOcupadaException extends Exception {
	public CoordenadaOcupadaException(int f, int c){
		super("("+f+","+c+")"); 
	}
}

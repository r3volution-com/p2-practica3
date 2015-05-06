import java.io.FileReader;
import java.io.IOException;


public class Juego {
	public static void main (String args[]){
		FileReader dfe=null;
		try{
			dfe=new FileReader("entrada.txt");
		}catch(IOException e){System.err.println(e);}
	}
}

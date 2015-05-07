import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Juego {
	public static void main (String args[]){
		if (args.length > 2){
			FileReader entrada;
			BufferedReader mibuf;
			String primerjugador, segundojugador, tipo;
			try{
				entrada=new FileReader(args[0]);
				mibuf=new BufferedReader(entrada);
				primerjugador=mibuf.readLine();
				segundojugador=mibuf.readLine();
				if (mibuf.readLine().equals("barcos")){
					while (!mibuf.readLine().equals("barcos")){
						//Crear Barcos J1
					}
					while (!mibuf.readLine().equals("partida")){
						//Crear Barcos J2
					}
				} else {/* No esta correcto*/}
				/*while(cadena!=null){
					System.out.println("---"+cadena+"----");
					cadena=mibuf.readLine();
				}*/
			}catch(IOException ex){
				ex.printStackTrace();
			}
			/*finally{
				if (mibuf!=null){
					try{
						mibuf.close(); 
					}catch(IOException ex){}
				}
				ex.printStackTrace();
			}
			if (entrada!=null){
				try{ 
					entrada.close();
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}*/
		}
	}
}

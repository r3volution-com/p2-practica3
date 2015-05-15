import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Juego {
	public static void main (String args[]){
		Coordenada coors[] = new Coordenada[3];
		coors[0]= new Coordenada(0, 0);
		coors[1] = new Coordenada(3, 3);
		coors[2] = new Coordenada(11, 11);
		for (int i = 0; i < coors.length; i++) {
			System.out.println(coors[i].getX()); //0 / 3 / 11
			System.out.println(coors[i].getY()); //0 / 3 / 11
			System.out.println(coors[i].cambiaEstado("agua")); //false
			System.out.println(coors[i].cambiaEstado("cosa")); //false
			System.out.println(coors[i].cambiaEstado("ocupado")); //true
			System.out.println(coors[i].equals(coors[0])); //true para la primera, false para las demas
			System.out.println(coors[i].getEstado()); //ocupado
			System.out.println("\n");
		}
		Barco bar[] = new Barco[3];
		bar[0]= new Barco(0);
		bar[1] = new Barco(1);
		bar[2] = new Barco(6);
		for (int i = 0; i < coors.length; i++) {
			System.out.println(bar[i].setPosicion(coors[1], 0)); //true
			System.out.println(bar[i].setPosicion(coors[0], 3)); //false (fuera de limites)
			System.out.println(bar[i].setPosicion(coors[0], 3)); //false (no cabe)
			System.out.println(bar[i].setPosicion(coors[1], 0)); //false (repe)
			System.out.println(bar[i].tocado(1, 1)); //false (no existe)
			System.out.println(bar[i].tocado(3, 3)); //true
			System.out.println(bar[i].tocado(20, 20)); //false (fuera de limites)
			System.out.println(bar[i].getTipo()); //lancha
			System.out.println(bar[i].getEstado()); //hundido
			System.out.println("\n");
		}
		Jugador jug[] = new Jugador[2];
		jug[0]= new Jugador("Mario");
		jug[1] = new Jugador(null);
		for (int i = 0; i < jug.length; i++) {
			jug[i].muestraTablero();
			System.out.println("\n");
		}
	}
	/*public static void main (String args[]){
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
				} else { 
					//No esta correcto
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}*/
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Juego {
	/*public static void main (String args[]){
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
	}*/
	private static Coordenada[] getCoordenadas(String linea) {
		// indicamos el separador de campos : un espacio en blanco
		String separador = "[ ]";
		// segmentam o s
		String[] elems = linea.split(separador);
		// convertim o s a entero cada cadena
		// contenida en elems
		int f1 = Integer.parseInt(elems[0]);
		int c1 = Integer.parseInt(elems[1]);
		int f2 = Integer.parseInt(elems[2]);
		int c2 = Integer.parseInt(elems[3]);
		// con estos datos ya se pueden construir coordenad a s
		Coordenada[] coors = new Coordenada[2];
		coors[0] = new Coordenada(f1, c1);
		coors[1] = new Coordenada(f2, c2);
		return coors;
	}
	private static Coordenada getAtaque(String linea) {
		// indicamos el separador de campos : un espacio en blanco
		String separador = "[ ]";
		// segmentam o s
		String[] elems = linea.split(separador);
		// convertim o s a entero cada cadena
		// contenida en elems
		int f1 = Integer.parseInt(elems[0]);
		int c1 = Integer.parseInt(elems[1]);
		// con estos datos ya se pueden construir coordenad a s
		Coordenada coors = new Coordenada(f1, c1);
		return coors;
	}
	public static void main (String args[]){
		if (args.length == 2){
			FileReader entrada;
			FileWriter salida;
			BufferedReader ebuf;
			BufferedWriter sbuf;
			String primerjugador, segundojugador, linea;
			Jugador[] jugadores = new Jugador[2];
			Coordenada[] coors;
			Coordenada coor;
			int jugadoractual = 0;
			boolean jugando = true;
			try{
				entrada=new FileReader(args[0]);
				salida=new FileWriter(args[1]);
				ebuf=new BufferedReader(entrada);
				sbuf=new BufferedWriter(salida);
				primerjugador=ebuf.readLine();
				jugadores[0] = new Jugador(primerjugador);
				segundojugador=ebuf.readLine();
				jugadores[1] = new Jugador(segundojugador);
				if (ebuf.readLine().equals("barcos")){
					do {
						linea = ebuf.readLine();
						if (!linea.equals("barcos")){
							coors = getCoordenadas(linea);
							try {
								jugadores[0].situaBarco(coors[0], coors[1]);
							} catch (CoordenadaNoValidaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							} catch (DotacionCompletaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							} catch (CoordenadaOcupadaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							}
						}
					} while (!linea.equals("barcos"));
					do {
						linea = ebuf.readLine();
						if (!linea.equals("partida")){
							coors = getCoordenadas(linea);
							try {
								jugadores[1].situaBarco(coors[0], coors[1]);
							} catch (CoordenadaNoValidaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							} catch (DotacionCompletaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							} catch (CoordenadaOcupadaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
							}
						}
					} while (!linea.equals("partida"));
					do {
						linea = ebuf.readLine();
						if (linea != null){
							coor = getAtaque(linea);
							try {
								if (!jugadores[jugadoractual].esAtacado(coor)){
									if (jugadoractual == 0) jugadoractual = 1;
									else jugadoractual = 0;
								}
							} catch (CoordenadaNoValidaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n");
								e.printStackTrace();
								//if (jugadoractual == 0) jugadoractual = 1;
								//else jugadoractual = 0;
							} catch (DotacionIncompletaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n"+jugadores[0].muestraTablero2()+"\n"+jugadores[1].muestraTablero2()+"\n");
								e.printStackTrace();
								jugando = false;
								if (ebuf != null) ebuf.close();
								if (sbuf != null) sbuf.close();
								if (entrada != null) entrada.close();
								if (salida != null) salida.close();
							} catch (PartidaPerdidaException e){
								sbuf.write(e.getClass().getName()+": "+e.getMessage()+"\n"+jugadores[0].muestraTablero2()+"\n"+jugadores[1].muestraTablero2()+"\n");
								e.printStackTrace();
								jugando = false;
								if (ebuf != null) ebuf.close();
								if (sbuf != null) sbuf.close();
								if (entrada != null) entrada.close();
								if (salida != null) salida.close();
							}
						}
					} while (linea != null && jugando == true);
					if (jugando == true) {
						sbuf.write(jugadores[0].muestraTablero2()+"\n"+jugadores[1].muestraTablero2());
						if (ebuf != null) ebuf.close();
						if (sbuf != null) sbuf.close();
						if (entrada != null) entrada.close();
						if (salida != null) salida.close();
					}
				}
			}catch(IOException ex){
				ex.printStackTrace();
			} 
		} else System.out.println("Debes pasarle 2 parametros");
	}
}
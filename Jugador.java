
public class Jugador {
	private Coordenada[][] tablero = new Coordenada[10][10];
	private Barco[] naves = new Barco[10];
	private String nombre;
	public Jugador(String nombre){
		if (nombre != null){
			this.nombre = nombre;
			for (int i = 0; i < tablero.length; i++){
				for (int j = 0; j <tablero[i].length;j++){
					tablero[i][j] = new Coordenada(i+1, j+1);
				}
			}
		}
	}
	public boolean situaBarco(Coordenada i, Coordenada f) throws CoordenadaNoValidaException, DotacionCompletaException, CoordenadaOcupadaException {
		if (i!=null && f!= null){
			int coordenadasX, coordenadasY, c_lanchas = 0, c_destructores = 0, c_cruceros = 0, c_acorazado = 0, primeralibre = -1;
			//Compruebo que tanto la final como la inicial esten dentro de los limites (1 a 10)
			if ((i.getX() >= 1 && i.getX() <= 10) && (i.getY() >= 1 && i.getY() <= 10)){
				if ((f.getX() >= 1 && f.getX() <= 10) && (f.getY() >= 1 && f.getY() <= 10)){
					//Restamos coordenadas
					coordenadasX = f.getX()-i.getX();
					coordenadasY = f.getY()-i.getY();
					//Recorremos el array de naves y creamos contadores de cada tipo de barco
					for (int j=0;j<naves.length;j++){
						if (naves[j] != null){
							if (naves[j].getTipo().equalsIgnoreCase("lancha")) c_lanchas++;
							else if (naves[j].getTipo().equalsIgnoreCase("destructor")) c_destructores++;
							else if (naves[j].getTipo().equalsIgnoreCase("crucero")) c_cruceros++;
							else if (naves[j].getTipo().equalsIgnoreCase("acorazado")) c_acorazado++;
						} else {
							//Si es nula guardamos la primera posicion libre
							if (primeralibre == -1) {
								primeralibre = j;
							}
						}
					}
					//Comprobamos que ocupa entre 1 y 4 posiciones
					if ((coordenadasX >= 0 && coordenadasX <= 3) && (coordenadasY >= 0 && coordenadasY <= 3)){
						//Comprobamos que vaya en horizontal (el final de X sea mayor que el inicial y el mayor de Y sea IGUAL al horizontal)
						if (f.getX() >= i.getX() && f.getY() == i.getY()){
							for (int j = 0; j<tablero.length;j++){
								for (int k = 0; k < tablero[j].length; k++){
									//Recorremos el tablero, si encontramos una posicion que pertenezca al barco y su estado es distinto de agua devuelve un error
									if (tablero[j][k].getY() == i.getY() && (tablero[j][k].getX() >= i.getX() && tablero[j][k].getX() <= f.getX()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == false){
										throw new CoordenadaOcupadaException(tablero[j][k].getX(), tablero[j][k].getY());
									}
								}
							}
							//Comprobamos que tipo de barco es, y si aun es posible crear ese tipo de barco lo crea, en caso contrario devuelve una excepcion
							if (coordenadasX == 0){
								if (c_lanchas < 4) {
									naves[primeralibre] = new Barco(coordenadasX+1);
								} else throw new DotacionCompletaException("lancha");
							} else if (coordenadasX == 1){
								if (c_destructores < 3) {
									naves[primeralibre] = new Barco(coordenadasX+1);
								} else throw new DotacionCompletaException("destructor");
							} else if (coordenadasX == 2){
								if (c_cruceros < 2) {
									naves[primeralibre] = new Barco(coordenadasX+1);
								} else throw new DotacionCompletaException("crucero");
							} else if (coordenadasX == 3){
								if (c_acorazado < 1) {
									naves[primeralibre] = new Barco(coordenadasX+1);
								} else throw new DotacionCompletaException("acorazado");
							} else return false;
							int l = 0;
							//System.out.println(nombre+" esta intentando colocar en "+i.getX()+" "+i.getY()+" - "+f.getX()+" "+f.getY());
							for (int j = 0; j<tablero.length;j++){
								for (int k = 0; k < tablero[j].length; k++){
									//Comprobamos que la posicion pertenece al barco y su estado es agua, si es asi cambia esa coordenada a ocupado.
									//System.out.println(tablero[j][k].getY()+" == "+i.getY()+" "+tablero[j][k].getX()+" >= "+i.getX()+" && "+tablero[j][k].getX()+" <= "+f.getX());
									if (tablero[j][k].getY() == i.getY() && (tablero[j][k].getX() >= i.getX() && tablero[j][k].getX() <= f.getX()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == true){
										//tablero[j][k].cambiaEstado("ocupado");
										//System.out.println(nombre+" "+tablero[j][k].getEstado());
										naves[primeralibre].setPosicion(tablero[j][k], l);
										l++;
									}
								}
							}
							//System.out.println("\n");
							return true;
						//Comprobamos que vaya en vertical (el final de Y sea mayor que el inicial y el mayor de X sea IGUAL al horizontal)
						} else if ((f.getY() >= i.getY() && f.getX() == i.getX())){
							for (int j = 0; j<tablero.length;j++){
								for (int k = 0; k < tablero[j].length; k++){
									//Recorremos el tablero, si encontramos una posicion que pertenezca al barco y su estado es distinto de agua devuelve un error
									if (tablero[j][k].getX() == i.getX() && (tablero[j][k].getY() >= i.getY() && tablero[j][k].getY() <= f.getY()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == false){
										throw new CoordenadaOcupadaException(tablero[j][k].getX(), tablero[j][k].getY());
									}
								}
							}
							//Comprobamos que tipo de barco es, y si aun es posible crear ese tipo de barco lo crea, en caso contrario devuelve una excepcion
							if (coordenadasY == 0){
								if (c_lanchas < 4) {
									naves[primeralibre] = new Barco(coordenadasY+1);
								} else throw new DotacionCompletaException("lancha");
							} else if (coordenadasY == 1){
								if (c_destructores < 3) {
									naves[primeralibre] = new Barco(coordenadasY+1);
								} else throw new DotacionCompletaException("destructor");
							} else if (coordenadasY == 2){
								if (c_cruceros < 2) {
									naves[primeralibre] = new Barco(coordenadasY+1);
								} else throw new DotacionCompletaException("crucero");
							} else if (coordenadasY == 3){
								if (c_acorazado < 1) {
									naves[primeralibre] = new Barco(coordenadasY+1);
								} else throw new DotacionCompletaException("acorazado");
							} else return false;
							int l = 0;
							for (int j = 0; j<tablero.length;j++){
								for (int k = 0; k < tablero[j].length; k++){
									//Comprobamos que la posicion pertenece al barco y su estado es agua, si es asi cambia esa coordenada a ocupado.
									if (tablero[j][k].getX() == i.getX() && (tablero[j][k].getY() >= i.getY() && tablero[j][k].getY() <= f.getY()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == true){
										//tablero[j][k].cambiaEstado("ocupado");
										//System.out.println(nombre+" "+tablero[j][k].getEstado());
										naves[primeralibre].setPosicion(tablero[j][k], l);
										l++;
									}
								}
							}
							//System.out.println("\n");
							return true;
						} else return false;
					} return false;
				}else throw new CoordenadaNoValidaException(f.getX(), f.getY());
			}else throw new CoordenadaNoValidaException(i.getX(), i.getY());
		}
		return false;
	}
	public boolean esAtacado(Coordenada c) throws CoordenadaNoValidaException, DotacionIncompletaException, PartidaPerdidaException {
		if (c != null){
			boolean tdshundidos = true;
			/*for (int i = 0; i< naves.length; i++){
				System.out.println(naves[i].getTipo());
			}*/
			//Comprobamos i la coordenada esta dentro de los limitess
			if ((c.getX() >= 1 && c.getX() <= 10) && (c.getY() >= 1 && c.getY() <= 10)){
				if (naves[naves.length-1] != null){
					for (int l=0;l<naves.length;l++){
						//Recorremos el array de naves y comprobamos si la posicion dada aparece en el barco actual
						if (naves[l].compruebaCoordenada(c.getX(), c.getY())){
							//Llamamos a la funcion tocado
							if (naves[l].tocado(c.getX(), c.getY())){ 
								tdshundidos = true;
								//Comprobamos si todas las posiciones son tocado y cambia el estado a hundido (esto ya lo hace tocado pero como pide el throw... nuse)
								for (int m=0;m<naves.length;m++){
									if (naves[m].getEstado().compareToIgnoreCase("hundido")!=0){
										tdshundidos = false;
									}
								}
								if (tdshundidos == true){
									throw new PartidaPerdidaException(this.nombre);
								}
								return true;
							}
						}
					}
				} else throw new DotacionIncompletaException(nombre);
			}else throw new CoordenadaNoValidaException(c.getX(), c.getY());
		}
		return false;
	}
	public void muestraTablero(){
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++){
				if (tablero[i][j] != null && tablero[i][j].getEstado() != null){
					if (tablero[i][j].getEstado().compareToIgnoreCase("agua") == 0) System.out.print("a ");
					if (tablero[i][j].getEstado().compareToIgnoreCase("ocupado") == 0) System.out.print("o ");
					if (tablero[i][j].getEstado().compareToIgnoreCase("tocado") == 0) System.out.print("t ");
				}
			}
			System.out.print("\n");
		}
	}
	public String muestraTablero2(){
		String cadena = "";
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++){
				if (tablero[i][j] != null && tablero[i][j].getEstado() != null){
					if (tablero[i][j].getEstado().compareToIgnoreCase("agua") == 0) cadena += "a ";
					if (tablero[i][j].getEstado().compareToIgnoreCase("ocupado") == 0) cadena += "o ";
					if (tablero[i][j].getEstado().compareToIgnoreCase("tocado") == 0) cadena += "t ";
				}
			}
			cadena+="\n";
		}
		return cadena;
	}
	public Barco[] getNaves(){
		return naves;
	}
}

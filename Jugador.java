
public class Jugador {
	private Coordenada[][] tablero = new Coordenada[9][9];
	private Barco[] naves = new Barco[10];
	private String nombre;
	public Jugador(String nombre){
		if (nombre != null){
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
			if (((i.getX() >= 1 && i.getX() <= 9) && (i.getY() >= 1 && i.getY() <= 9)) && ((f.getX() >= 1 && f.getX() <= 9) && (f.getY() >= 1 && f.getY() <= 9))){
				coordenadasX = f.getX()-i.getX();
				coordenadasY = f.getY()-i.getY();
				for (int j=0;j<naves.length;j++){
					if (naves[j] != null){
						if (naves[j].getTipo().equalsIgnoreCase("lancha")) c_lanchas++;
						else if (naves[j].getTipo().equalsIgnoreCase("destructor")) c_destructores++;
						else if (naves[j].getTipo().equalsIgnoreCase("crucero")) c_cruceros++;
						else if (naves[j].getTipo().equalsIgnoreCase("acorazado")) c_acorazado++;
					} else {
						if (primeralibre == -1) {
							primeralibre = j;
						}
					}
				}
				if ((f.getX() >= i.getX() && f.getY() == i.getY())){
					if ((coordenadasX >= 0 && coordenadasX <= 3) && (coordenadasY >= 0 && coordenadasY <= 3)){
						for (int j = 0; j<tablero.length;j++){
							for (int k = 0; k < tablero[j].length; k++){
								if (tablero[j][k].getY() == i.getY() && (tablero[j][k].getX() >= i.getX() && tablero[j][k].getX() <= f.getX()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == false){
									throw new CoordenadaOcupadaException(tablero[j][k].getX(), tablero[j][k].getY());
								}
							}
						}
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
						for (int j = 0; j<tablero.length;j++){
							for (int k = 0; k < tablero[j].length; k++){
								if (tablero[j][k].getY() == i.getY() && (tablero[j][k].getX() >= i.getX() && tablero[j][k].getX() <= f.getX()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == true){
									naves[primeralibre].setPosicion(tablero[j][k], l);
									l++;
								}
							}
						}
						return true;
					} else return false;
				} else if ((f.getY() >= i.getY() && f.getX() == i.getX())){
					if ((coordenadasX >= 0 && coordenadasX <= 3) && (coordenadasY >= 0 && coordenadasY <= 3)){
						for (int j = 0; j<tablero.length;j++){
							for (int k = 0; k < tablero[j].length; k++){
								if (tablero[j][k].getX() == i.getX() && (tablero[j][k].getY() >= i.getY() && tablero[j][k].getY() <= f.getY()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == false){
									throw new CoordenadaOcupadaException(tablero[j][k].getX(), tablero[j][k].getY());
								}
							}
						}
						if (coordenadasY == 0){
							if (c_lanchas < 4) {
								naves[primeralibre] = new Barco(coordenadasY+1);
							} else throw new DotacionCompletaException("lancha");
						} else if (coordenadasY == 1){
							if (c_lanchas < 3) {
								naves[primeralibre] = new Barco(coordenadasY+1);
							} else throw new DotacionCompletaException("destructor");
						} else if (coordenadasX == 2){
							if (c_lanchas < 2) {
								naves[primeralibre] = new Barco(coordenadasY+1);
							} else throw new DotacionCompletaException("crucero");
						} else if (coordenadasY == 3){
							if (c_lanchas < 1) {
								naves[primeralibre] = new Barco(coordenadasY+1);
							} else throw new DotacionCompletaException("acorazado");
						} else return false;
						int l = 0;
						for (int j = 0; j<tablero.length;j++){
							for (int k = 0; k < tablero[j].length; k++){
								if (tablero[j][k].getX() == i.getX() && (tablero[j][k].getY() >= i.getY() && tablero[j][k].getY() <= f.getY()) && tablero[j][k].getEstado().equalsIgnoreCase("agua") == true){
									//tablero[j][k].cambiaEstado("ocupado");
									naves[primeralibre].setPosicion(tablero[j][k], l);
									l++;
								}
							}
						}
						return true;
					} else return false;
				} return false;
			}else throw new CoordenadaNoValidaException(i.getX(), i.getY());
		}
		return false;
	}
	public boolean esAtacado(Coordenada c) throws CoordenadaNoValidaException, DotacionIncompletaException, PartidaPerdidaException {
		if (c != null){
			boolean tdshundidos = true;
			if ((c.getX() >= 1 && c.getX() <= 9) && (c.getY() >= 1 && c.getY() <= 9)){
				if (naves[naves.length-1] != null){
					for (int j = 0; j<tablero.length;j++){
						for (int k = 0; k < tablero[j].length; k++){
							for (int l=0;l<naves.length;l++){
								if (naves[l].tocado(j+1, k+1)){ 
									tdshundidos = true;
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
								
								/*if (naves[l].compruebaCoordenada(j+1, k+1)){
									naves[l].tocado(j+1, k+1);
									tablero[j][k].cambiaEstado("tocado");
								}*/
							}
							//Como se que barco hay aqui? tocado (int x, int y)
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
				if (tablero[i][j].getEstado() != null){
					if (tablero[i][j].getEstado().compareToIgnoreCase("agua") == 0) System.out.print("a ");
					if (tablero[i][j].getEstado().compareToIgnoreCase("ocupado") == 0) System.out.print("o ");
					if (tablero[i][j].getEstado().compareToIgnoreCase("tocado") == 0) System.out.print("t ");
				}
			}
			System.out.print("\n");
		}
	}
	public Barco[] getNaves(){
		return naves;
	}
}

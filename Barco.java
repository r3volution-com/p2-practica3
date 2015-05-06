
public class Barco {
	private Coordenada[] posiciones;
	private String estado;
	private String tipo;
	public Barco(int nCoord){
		if (nCoord < 1 || nCoord > 4) nCoord = 1;
		posiciones = new Coordenada[nCoord];
		estado = "flotado";
		switch(nCoord){
			case 1: 
				tipo = "lancha";
			break;
			case 2:
				tipo = "destructor";
			break;
			case 3: 
				tipo = "crucero";
			break;
			case 4:
				tipo = "acorazado";
			break;
			default:
				tipo = "lancha";
			break;
		}
	}
	public boolean setPosicion(Coordenada c, int i){
		if (c != null && i > 0 && i < posiciones.length && posiciones[i] == null){
			posiciones[i] = c;
			if (posiciones[i].getEstado().compareToIgnoreCase("ocupado") != 0){
				//REvisar
				if (posiciones[i].cambiaEstado("ocupado")) return true;
				else return false;
			} else return true;
		}
		return false;
	}
	public boolean tocado (int x, int y){
		boolean tocado = true;
		if ((x >= 1 && x <= 10) && (y >= 1 && x <= 10)){
			for (int i = 0; i < posiciones.length; i++){
				if (posiciones[i] != null){
					if (posiciones[i].getX() == x && posiciones[i].getY() == y){
						posiciones[i].cambiaEstado("tocado");
						for (int j = 0; j < posiciones.length; j++){
							if (posiciones[j] != null){
								 if (posiciones[j].getEstado().compareToIgnoreCase("tocado") != 0) tocado = false;
							} else tocado = false;
						}
						if (tocado){
							this.estado = "hundido";
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean compruebaCoordenada(int x, int y){
		if ((x >= 0  && x >= 0)&&(x<=9 && y <=9)) {
			for (int i=0;i<posiciones.length;i++){
				if (posiciones[i]!=null){
					if (posiciones[i].getX() == x && posiciones[i].getY() == y){
						return true;
					}
				}
			}
		}
		return false;
	}
	public String getEstado(){
		return estado;
	}
	public String getTipo(){
		return tipo;
	}
}

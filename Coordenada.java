
public class Coordenada {
	private int x;
	private int y;
	private String estado;
	public Coordenada(int x, int y){
		if (x<0) x=0;
		if (y<0) y=0;
		this.x=x;
		this.y=y;
		this.estado="agua";
	}
	public boolean cambiaEstado(String nuevoEstado) {
		if (nuevoEstado != null && (nuevoEstado.compareToIgnoreCase("agua") == 0 || nuevoEstado.compareToIgnoreCase("ocupado") == 0 || nuevoEstado.compareToIgnoreCase("tocado") == 0) && nuevoEstado.compareToIgnoreCase(this.estado) != 0){
			this.estado = nuevoEstado;
			return true;
		}
		return false;
	}
	public boolean equals(int x, int y, String estado){
		if (this.x == x && this.y == y && this.estado.compareToIgnoreCase(estado) == 0){
			return true;
		}
		return false;
	}
	public String getEstado() {
		return estado;
	}
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}
}

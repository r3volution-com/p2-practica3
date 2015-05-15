
public class Coordenada {
	private int x;
	private int y;
	private String estado;
	public Coordenada(int x, int y){
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
	public boolean equals(Coordenada c){
		if (this.x == c.getX() && this.y == c.getY() && this.estado.compareToIgnoreCase(c.getEstado()) == 0){
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

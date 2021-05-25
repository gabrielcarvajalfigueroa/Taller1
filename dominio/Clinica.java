package Taller1.dominio;

public class Clinica {
	private String ciudad;
	private int ganancias;
	private int estado;
	
	public Clinica(String ciudad,int ganancias,int estado) {
		this.ciudad = ciudad;
		this.ganancias = ganancias;
		this.estado = estado;
	}

	
	public String toString() {
		return "Clinica [ciudad=" + ciudad + ", ganancias=" + ganancias + ", estado=" + estado + "]";
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getGanancias() {
		return ganancias;
	}

	public void setGanancias(int ganancias) {
		this.ganancias = ganancias;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}

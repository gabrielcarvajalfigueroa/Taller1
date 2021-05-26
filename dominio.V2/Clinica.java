package Taller1.dominio;

public class Clinica {
	private String ciudad;
	private int ganancias;
	private String estado;
	private Dentista[] lista;
	private int cantDentistas;
	
	public Clinica(String ciudad,int ganancias,String estado) {
		this.ciudad = ciudad;
		this.ganancias = ganancias;
		this.estado = estado;
		lista = new Dentista[10];
		cantDentistas = 0;																					
	}

	
	public String toString() {
		String d = "";
		for (int i = 0; i < cantDentistas; i++) {
			d = d + lista[i].getNombre() + " ";
		}
		return "Clinica [ciudad=" + ciudad + ", ganancias=" + ganancias + ", estado=" + estado + ", dentistas=" + d + "]";
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Dentista getDentistaI(int i) {
		if(i >= 0 && i < cantDentistas) {
			return lista[i];
		}
		return null;
	}
	
	public boolean ingresarDentista(Dentista dentista) {
		if (cantDentistas < 10) {
			lista[cantDentistas] = dentista;
			cantDentistas++;
			return true;
		}
		return false;
	}																														
	
}

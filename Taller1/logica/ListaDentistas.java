package Taller1.logica;

import Taller1.dominio.*;

public class ListaDentistas {
	private Dentista[] lista;
	private int cantDentistas;
	private int max;
	
	public ListaDentistas(int max) {
		lista = new Dentista[max];
		cantDentistas = 0;
		this.max = max;
	}
	
	public int getCantDentistas() {
		return cantDentistas;
	}
	
	public Dentista getDentistaI(int i) {
		if(i >= 0 && i < cantDentistas) {
			return lista[i];
		}
		return null;
	}
	
	public boolean ingresarDentista(Dentista dentista) {
		if (cantDentistas < max) {
			lista[cantDentistas] = dentista;
			cantDentistas++;
			return true;
		}
		return false;
	}

	public Dentista buscarDentista(String rut) {
		int i;
		for(i = 0; i < cantDentistas; i++) {
			if (lista[i].getRut().equals(rut)) {
				break;
			}
		}
		if (i == cantDentistas) {
			return null;
		}
		return lista[i];
	}

	public String toString() {
		String r = "";
		for(int i = 0;i < cantDentistas; i ++) {
			r = r + lista[i].toString() + "\n";
		}
		return r;
	}

}

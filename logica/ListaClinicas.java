package Taller1.logica;

import Taller1.dominio.*;

public class ListaClinicas {
	private Clinica[] lista;
	private int cantClinicas;
	private int max;
	
	public ListaClinicas(int max) {
		lista = new Clinica[max];
		cantClinicas = 0;
		this.max = max;
	}
	
	public int getCantClinicas() {
		return cantClinicas;
	}
	
	public Clinica getClinicaI(int i) {
		if(i >= 0 && i < cantClinicas) {
			return lista[i];
		}
		return null;
	}
	
	public boolean ingresarClinicas(Clinica clinica) {
		if (cantClinicas < max) {
			lista[cantClinicas] = clinica;
			cantClinicas++;
			return true;
		}
		return false;
	}

	public Clinica buscarClinica(String ciudad) {
		int i;
		for(i = 0; i < cantClinicas; i++) {
			if (lista[i].getCiudad().equals(ciudad)) {
				break;
			}
		}
		if (i == cantClinicas) {
			return null;
		}
		return lista[i];
	}

	public String toString() {
		String r = "";
		for(int i = 0;i < cantClinicas; i ++) {
			r = r + lista[i].toString() + "\n";
		}
		return r;
	}

}

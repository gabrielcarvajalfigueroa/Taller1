package Taller1.logica;

import Taller1.dominio.*;

public class ListaCitas {
	private Cita[] lista;
	private int cantCitas;
	private int max;
	
	public ListaCitas(int max) {
		lista = new Cita[max];
		cantCitas = 0;
		this.max = max;
	}
	
	public int getCantCitas() {
		return cantCitas;
	}
	
	public Cita getCitaI(int i) {
		if(i >= 0 && i < cantCitas) {
			return lista[i];
		}
		return null;
	}
	
	public boolean ingresarCita(Cita cita) {
		if (cantCitas < max) {
			lista[cantCitas] = cita;
			cantCitas++;
			return true;
		}
		return false;
	}

	// faltan buscarpor cliente buscar por dentista y los eliminar cliente y dentista

	public String toString() {
		String r = "";
		for(int i = 0;i < cantCitas; i ++) {
			r = r + lista[i].toString() + "\n";
		}
		return r;
	}

}

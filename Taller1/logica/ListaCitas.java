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
	
	public boolean eliminarCita(String codigo) {
		// elimina la cita
		int j;
		for(j=0; j< cantCitas; j++){
			if(lista[j].getCodigo().equals(codigo)) {
				break; //sale del for
			}
		}
		if(j== cantCitas) { // no lo encontró
			return false;
		}
		else { // lo encontró
			for(int k=j;k<cantCitas-1;k++){//corrimiento
				lista[k] = lista[k+1];
			}
			cantCitas --; // decrece el tamaño
			return true;
		} //end else
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

package Taller1.logica;

import Taller1.dominio.*;

public class ListaClientes {
	private Cliente[] lista;
	private int cantClientes;
	private int max;
	
	public ListaClientes(int max) {
		lista = new Cliente[max];
		cantClientes = 0;
		this.max = max;
	}
	
	public int getCantClientes() {
		return cantClientes;
	}
	
	public Cliente getClienteI(int i) {
		if(i >= 0 && i < cantClientes) {
			return lista[i];
		}
		return null;
	}
	
	public boolean ingresarCliente(Cliente cliente) {
		if (cantClientes < max) {
			lista[cantClientes] = cliente;
			cantClientes++;
			return true;
		}
		return false;
	}

	public Cliente buscarCliente(String rut) {
		int i;
		for(i = 0; i < cantClientes; i++) {
			if (lista[i].getRut().equals(rut)) {
				break;
			}
		}
		if (i == cantClientes) {
			return null;
		}
		return lista[i];
	}

	public String toString() {
		String r = "";
		for(int i = 0;i < cantClientes; i ++) {
			r = r + lista[i].toString() + "\n";
		}
		return r;
	}


}
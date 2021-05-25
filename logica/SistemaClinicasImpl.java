package Taller1.logica;

import Taller1.dominio.*;

public class SistemaClinicasImpl implements SistemaClinicas {
	
	private ListaCitas listaCitas;
	private ListaClientes listaClientes;
	private ListaClinicas listaClinicas;
	private ListaDentistas listaDentistas;
	
	public SistemaClinicasImpl() {		// por ahora solo se inicia con 1000 elementos para inicializar los contenedores
		listaCitas = new ListaCitas(1000);
		listaClientes = new ListaClientes(1000);
		listaClinicas = new ListaClinicas(1000);
		listaDentistas = new ListaDentistas(1000);
	}
	
	public String CitasAgendadas(String rut) { //no listo
		return rut;
	}

	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad) {
		Cliente cliente = new Cliente(nombre,rut,contrasena,prevision,ciudad);
		
		boolean ingreso = listaClientes.ingresarCliente(cliente);
		
		return ingreso;
	}
	
	public String obtenerClientes() {
		String r = "";
		
		for (int i  = 0; i < listaClientes.getCantClientes(); i++) {
			r = r + listaClientes.getClienteI(i).toString() + "\n";
		}
		
		return r;
	}

}

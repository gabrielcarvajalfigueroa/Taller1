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

	public boolean ingresarDentista(String nombre,String rut,String contrasena,int sueldo,int comisiones,String ciudad,int experiencia) {
		Dentista dentista = new Dentista(nombre,rut,contrasena,sueldo,comisiones,ciudad,experiencia);
		
		boolean ingreso = listaDentistas.ingresarDentista(dentista);
		
		for(int i = 0; i < listaClinicas.getCantClinicas(); i++) {
			if(dentista.getCiudad().equals(listaClinicas.getClinicaI(i).getCiudad())) {
				listaClinicas.getClinicaI(i).ingresarDentista(dentista);
			}
		}
		
		
		return ingreso;
	}
	
	public boolean ingresarClinica(String ciudad,int ganancias,String estado) {
		Clinica clinica = new Clinica(ciudad,ganancias,estado);
		
		boolean ingreso = listaClinicas.ingresarClinicas(clinica);
		
		return ingreso;
	}
	
	public boolean ingresarCita(String rutPaciente,String operacion,String ciudad,String rutDentista,String fecha,String codigo) {
		
		Cliente cliente = null;
		for(int j = 0; j < listaClientes.getCantClientes(); j++) { //Conseguir cliente para pasarlo como parametro al constructor cita
			if (rutPaciente.equals(listaClientes.getClienteI(j).getRut())) {
				cliente = listaClientes.getClienteI(j);
				break;
			}
		}	
		
		Dentista dentista = null;
		for(int z = 0; z < listaDentistas.getCantDentistas(); z++) {
			if(rutDentista.equals(listaDentistas.getDentistaI(z).getRut())){
				dentista = listaDentistas.getDentistaI(z);
				break;
			}
		}
		Cita cita = new Cita(cliente,operacion,ciudad,dentista,fecha,codigo);
		
		boolean ingreso = listaCitas.ingresarCita(cita);
		return ingreso;
	}
	
	public String obtenerClinicas() {
		String r = "";
		for(int i = 0; i < listaClinicas.getCantClinicas(); i ++) {
			r = r + listaClinicas.getClinicaI(i).toString() + "\n";
		}
		return r;
	}
	
	
	
	public String obtenerDentistas() {
		String r = "";
		for(int i = 0; i < listaDentistas.getCantDentistas(); i++) {
			r = r + listaDentistas.getDentistaI(i).toString() + "\n";
		}	
		return r;
	}
}

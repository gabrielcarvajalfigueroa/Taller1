package Taller1.logica;

import java.util.Random;

import Taller1.dominio.*;
import ucn.*;

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
		String texto = "";
		int cont = 1;
		for(int i = 0; i<listaCitas.getCantCitas();i++) {
			
			if(rut.equals(listaCitas.getCitaI(i).getCliente().getRut())) {
				texto = texto + cont + ")" + listaCitas.getCitaI(i).toString() + "\n";
				cont++;
			}
		}
		return texto;
	}

	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad) {
		Cliente cliente = new Cliente(nombre,rut,contrasena,prevision,ciudad);
		
		boolean ingreso = listaClientes.ingresarCliente(cliente);
		
		return ingreso;
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
	
	public boolean VerificadorC(String rut, String contrasena) {
		for(int i = 0; i<listaClientes.getCantClientes();i++) {
			if(rut.equals(listaClientes.getClienteI(i).getRut())
					&& contrasena.equals(listaClientes.getClienteI(i).getContrasena())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean VerificadorD(String rut, String contrasena) {
		for(int j = 0; j<listaDentistas.getCantDentistas();j++) {
			if(rut.equals(listaDentistas.getDentistaI(j).getRut())
					&& contrasena.equals(listaDentistas.getDentistaI(j).getContrasena())) {
				return true;
			}
		}
		return false;
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
	
	public String agendarCita(String rut_cliente) {
		//verificar que la ciudad q vive el cliente exista clinica higienizada
		Cliente c = null;
		Dentista d = null;
		
		for(int i = 0; i < listaClientes.getCantClientes(); i++) {
			if(rut_cliente.equals(listaClientes.getClienteI(i).getRut())){
				c = listaClientes.getClienteI(i);
			}
		}
		//si no existe  clinica o no existe clinica higienizada se despliegue mensaje exacto
		int j;
		for(j = 0; j <listaClinicas.getCantClinicas(); j++) {
			if (c.getCiudad().equals(listaClinicas.getClinicaI(j).getCiudad())) {
				d = listaClinicas.getClinicaI(j).getDentistaI(0);
				if(listaClinicas.getClinicaI(j).getEstado().equals("Insalubre")) {
					return "La clinica no esta Higienizada.";
				}
				break;
			}
		}
		if(j == listaClinicas.getCantClinicas()) {
			return "No existe clinica en la ciudad del cliente";
		}
		//caso contrario se pregunta por fecha  y la operacion 
		System.out.println("Ingrese la fecha de la cita: ");
		String fecha = StdIn.readString();
		System.out.println("Ingrese el tipo de operacion");
		String operacion = StdIn.readString();
		//se le asigna un codigo al azar a la cita
		String codigo = crearCodigo();
		//se verifica que el codigo no este repetido
		for(int k = 0; k < listaCitas.getCantCitas(); k++) {
			if (codigo.equals(listaCitas.getCitaI(k).getCodigo())) {
				codigo = crearCodigo();
				break;
			}
		}
		//se consigue dentista
		// -> se consigue el primer dentista en el for que verifica las clinicas
		//se ingresa la cita al sistema
		Cita cita = new Cita(c,operacion,c.getCiudad(),d,fecha,codigo);
		ingresarCita(c.getRut(),operacion,c.getCiudad(),d.getRut(),fecha,codigo);
		return "Cita ingresada al sistema";
	}
	public String crearCodigo() {
		// crear un codigo de 10 numeros al azar que sea String
		Random rand = new Random(); 
		int upperbound = 9; // Genera numeros desde el 0-9
		int int_random;
		String codigo = "";
		for(int i = 0; i <10; i ++) {
			int_random = rand.nextInt(upperbound);
			codigo = codigo + Integer.toString(int_random);
		}

		return codigo;
	}
	public String obtenerCitas() {
		String r = "";
		for(int i = 0; i < listaCitas.getCantCitas(); i++) {
			r = r + listaCitas.getCitaI(i).toString() + "\n";
		}
		return r;
	}

	public String completarCita(String rut_cliente) {
		//desplegar por pantalla todas las citas del cliente
		String r = CitasAgendadas(rut_cliente);
		//si no tiene citas desplegar mensaje correspondiente
		if (r.equals("")) {
			return "El cliente no tiene citas agendadas";
		}
		System.out.println(r);
		//el cliente debe seleccionar cual quiere pagar
		System.out.println("Ingrese el codigo de la cita que desea pagar: ");
		String citaAPagar = StdIn.readString();
		//declarar precios operaciones
		int blanqueamiento_laser = 136000;
		int exodoncia = 35000;
		int limpieza = 63000;
		int radiografia = 15000;
		int fluoracion = 55000;
		//realizar pago con formula dependiendo de prevision
		double prevision = 0;
		String prevision_nombre = null;
		for(int i = 0; i < listaClientes.getCantClientes(); i++) {
			if(rut_cliente.equals(listaClientes.getClienteI(i).getRut())){
				prevision_nombre = listaClientes.getClienteI(i).getPrevision();
				if(prevision_nombre.equals("Fonasa")) {
					prevision = 0.8;
				}
				else {
					prevision = 0.7;
				}
				break;
			}
		}
		Cita cita = null;
		double pago = 0;
		
		for(int j = 0; j<listaCitas.getCantCitas(); j++) {
			if(citaAPagar.equals(listaCitas.getCitaI(j).getCodigo())) {
				cita = listaCitas.getCitaI(j);
				break;
			}
		}
		if(cita.getOperacion().equals("Exodoncia")){
			pago = prevision * exodoncia;
		}
		else if(cita.getOperacion().equals("Limpieza")){
			pago = prevision * limpieza;
		}
		else if(cita.getOperacion().equals("Radiografia")){
			pago = prevision * radiografia;
		}
		else if(cita.getOperacion().equals("Fluoracion")){
			pago = prevision * fluoracion;
		}
		else if(cita.getOperacion().equals("Blanqueamiento laser")){
			pago = prevision * blanqueamiento_laser;
		}
		
		//el dentista recibe 10%  - clinica el resto
		cita.getDentista().setComisiones(cita.getDentista().getComisiones() + (pago * 0.1));
		for(int y = 0; y < listaClinicas.getCantClinicas(); y++) {
			if(cita.getCiudad().equals(listaClinicas.getClinicaI(y).getCiudad())){
				listaClinicas.getClinicaI(y).setGanancias(listaClinicas.getClinicaI(y).getGanancias() + (pago - (pago * 0.1)));
			}
		}
		//al estar pagada se debe borrar la cita del sistema
		listaCitas.eliminarCita(citaAPagar);
	
		return "cita pagada";
	}
	
	public boolean cancelarCitaCliente(String rut_cliente) {
		//Despliega por pantalla todas las citas del cliente
		String r = CitasAgendadas(rut_cliente);
		//en caso de no tener desplegar mensaje
		if(r.equals("")) {
			System.out.println("El cliente no tiene citas agendadas");
			return false;
		}
		System.out.println(r);
		//cliente selecciona cita por su codigo 
		System.out.println("Seleccione la cita a eliminar por su codigo: ");
		String codigo = StdIn.readString();
		//esta se borra del sistema
		listaCitas.eliminarCita(codigo);
		return true;
	}
}
	

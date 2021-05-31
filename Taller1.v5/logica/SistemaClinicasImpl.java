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
	
	

	public boolean ingresarDentista(String nombre,String rut,String contrasena,int sueldo,double comisiones,String ciudad,int experiencia) {
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

	public void clienteCovidDetectado() {
		// se deben desplegar las clinicas que esten higienizadas
		String r = "";
		for(int i = 0; i < listaClinicas.getCantClinicas(); i++) {
			if(listaClinicas.getClinicaI(i).getEstado().equals("Higienizada")) {
				r = r + listaClinicas.getClinicaI(i).getCiudad() + "\n";
			}
		}
		System.out.println(r);
		//se pregunta en cual se detecto el caso covid
		System.out.println("Ingrese la ciudad de la clinica donde se detecto el caso covid: ");
		String clinicaCovid = StdIn.readString(); 
		//se borran todas las citas de esa clinica
		for(int j = 0; j < listaCitas.getCantCitas(); j++) {
			if(listaCitas.getCitaI(j).getCiudad().equals(clinicaCovid)) {
				listaCitas.eliminarCita(listaCitas.getCitaI(j).getCodigo());
			}
		}
		//se cambia el estado de la clinica a insalubre
		Clinica c = null;
		for(int z = 0; z < listaClinicas.getCantClinicas(); z++) {
			if(listaClinicas.getClinicaI(z).getCiudad().equals(clinicaCovid)) {
				c = listaClinicas.getClinicaI(z);
				break;
			}
		}
		c.setEstado("Insalubre");
		System.out.println("Clinica declarada insalubre y todas sus citas fueron canceladas" + "\n");
	}
	
	public void obtenerSueldosDentistas() {
		//desplegar todos los dentistas con su informacion y sueldo final
		//sueldofinal=(sueldobase*(añosexp*0.1)+comision)
		int i = 0;
		int j = 0;
		int sueldo = 0;
		int experiencia = 0;
		double comisiones = 0;
		double sf = 0;
		for(i = 0; i < listaClinicas.getCantClinicas(); i++){
			for(j = 0; j < listaClinicas.getClinicaI(i).getCantDentistas(); j++) {
				System.out.println("Informacion del dentista:");
				System.out.println(listaClinicas.getClinicaI(i).getLista()[j].toString());
				// sueldo final
				sueldo = listaClinicas.getClinicaI(i).getLista()[j].getSueldo();
				experiencia = listaClinicas.getClinicaI(i).getLista()[j].getExperiencia();
				comisiones = listaClinicas.getClinicaI(i).getLista()[j].getComisiones();
				sf = (sueldo*(experiencia*0.1) + comisiones);				
				System.out.println("Sueldo final" + sf);
			}
		}
	}

	public String obtenerInfoGanancias() {
		//desplegar todas las clinicas y sus ganancias
		String r = "";
		for(int i = 0; i < listaClinicas.getCantClinicas(); i++) {
			r = r + "Nombre Clinica: " + listaClinicas.getClinicaI(i).getCiudad() + ", Ganancias: " + listaClinicas.getClinicaI(i).getGanancias()+"\n";
		}
		return r;
	}
	
	public void higienizarLocal() {
		//Desplegar ciudades con clinicas insalubre;
		System.out.println("Ciudades con clinicas insalubres: ");
		for(int i = 0; i < listaClinicas.getCantClinicas(); i++) {
			if(listaClinicas.getClinicaI(i).getEstado().equals("Insalubre")) {
				System.out.println(listaClinicas.getClinicaI(i).getCiudad());
			}
		}
		//preguntar cual se desea higienizar
		System.out.println("Ingrese la ciudad de la clinica que desea higienizar: ");
		String higienizar = StdIn.readString();
		//cambiar el estado de la clinica
		Clinica c = null;
		for(int j = 0; j < listaClinicas.getCantClinicas(); j++) {
			if(higienizar.equals(listaClinicas.getClinicaI(j).getCiudad())) {
				c = listaClinicas.getClinicaI(j);
				break;
			}
		}
		c.setEstado("Higienizada");
	}
	
	public void contratarDentista() {
		//1)preguntar por los datos del nuevo dentista
		//  nombre,rut,contrasena,sueldo,comisiones,ciudad,experiencia
		System.out.println("Ingrese el primer nombre del dentista: ");
		String nombre = StdIn.readString();
		System.out.println("Ingrese el apellido del dentista ");
		String apellido = StdIn.readString();
		String nombre_completo = nombre + " " + apellido;
		System.out.println("Ingrese el rut");
		String rut = StdIn.readString();
		System.out.println("Ingrese la contrasena:");
		String contrasena = StdIn.readString();
		System.out.println("Ingrese el sueldo:");
		int sueldo = StdIn.readInt(); 
		System.out.println("Ingrese las comisiones: ");
		double comisiones = StdIn.readDouble();
		System.out.println("Ingrese la ciudad donde vive:");
		String ciudad = StdIn.readString();
		System.out.println("Ingrese la experiencia:");
		int experiencia = StdIn.readInt();
		//2)para contratar dentista debe vivir en ciudad donde exista clinica
		//  esta clinica no debe tener mas de 10 dentistas
		boolean sePuedeContratar = false;
		int i;
		for(i = 0; i < listaClinicas.getCantClinicas(); i++) {
			if(listaClinicas.getClinicaI(i).getCiudad().equals(ciudad)) {
				if(listaClinicas.getClinicaI(i).getCantDentistas() >= 10) {
					System.out.println("La clinica tiene el maximo de dentista");
					sePuedeContratar = false;
					break;
				}
				sePuedeContratar = true;
			}
		}
		
		//3)registrarlo en el sistema
		if(sePuedeContratar == true) {
			boolean ingreso = ingresarDentista(nombre_completo,rut,contrasena,sueldo,comisiones,ciudad,experiencia);
			System.out.println("Dentista ingresado con exito");
		}
	}
}
	

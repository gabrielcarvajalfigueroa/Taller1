package Taller1.logica;

import java.io.IOException;
import java.util.Random;

import Taller1.dominio.*;
import ucn.*;
/**
 * Class that implements "SistemaClinicas"
 * @author Pablo S. Rodriguez
 *
 */
public class SistemaClinicasImpl implements SistemaClinicas {
	
	private ListaCitas listaCitas;
	private ListaClientes listaClientes;
	private ListaClinicas listaClinicas;
	private ListaDentistas listaDentistas;
	
	public SistemaClinicasImpl() {		
		listaCitas = new ListaCitas(1000);
		listaClientes = new ListaClientes(1000);
		listaClinicas = new ListaClinicas(1000);
		listaDentistas = new ListaDentistas(1000);
	}
	
	public String CitasAgendadas(String rut) {
		String texto = "";
		int cont = 1;
		for(int i = 0; i<listaCitas.getCantCitas();i++) {
			if(rut.equals(listaCitas.getCitaI(i).getCliente().getRut())) {
				texto = texto + cont + ")" + listaCitas.getCitaI(i).toString() + "\n";
				cont++;
			}
		}
		if(texto.equals("")) {
			return "No tiene citas agendadas";
		}
		return texto;
	}

	public String CitasAgendadasD(String rut) {
		String texto = "";
		int cont = 1;
		for(int i = 0; i<listaCitas.getCantCitas();i++) {
			if(rut.equals(listaCitas.getCitaI(i).getDentista().getRut())) {
				texto = texto + cont + ")" + listaCitas.getCitaI(i).toString() + "\n";
				cont++;
			}
		}
		if(texto.equals("")) {
			return "No tiene citas agendadas";
		}
		return texto;
	}
	
	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad) {
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");
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
	
	public boolean ingresarClinica(String ciudad,double ganancias,String estado) {
		Clinica clinica = new Clinica(ciudad,ganancias,estado);
		
		boolean ingreso = listaClinicas.ingresarClinicas(clinica);
		
		return ingreso;
	}
	
	public boolean ingresarCita(String rutPaciente,String operacion,String ciudad,String rutDentista,String fecha,String codigo) {
		
		Cliente cliente = null;
		for(int j = 0; j < listaClientes.getCantClientes(); j++) { 
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
		if(cliente == null || dentista == null) {
			throw new NullPointerException("Cliente y/o dentista no existe");
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
		
	public void agendarCita(String rut_cliente, String fecha, String operacion) {
		Cliente c = null;
		Dentista d = null;
		
		for(int i = 0; i < listaClientes.getCantClientes(); i++) {
			if(rut_cliente.equals(listaClientes.getClienteI(i).getRut())){
				c = listaClientes.getClienteI(i);	//Search the client by rut
				break;
			}
		}
		int j;
		for(j = 0; j <listaClinicas.getCantClinicas(); j++) {
			if (c.getCiudad().equals(listaClinicas.getClinicaI(j).getCiudad())) {
				d = listaClinicas.getClinicaI(j).getDentistaI(0);
				if(d == null) {
					throw new NullPointerException("La clinica no tiene dentistas");
				}
				if(listaClinicas.getClinicaI(j).getEstado().equals("Insalubre")) {
					throw new NullPointerException("La clinica es insalubre");
				}
				break;
			}
		}
		if(j == listaClinicas.getCantClinicas()) {
			throw new NullPointerException("No existe clinica en su ciudad");
		}
		if(operacion.equals("Exodoncia") || operacion.equals("Blanqueamiento laser") || operacion.equals("Limpieza")
				|| operacion.equals("Radiografia") || operacion.equals("Fluoracion")) {
		}
		else {
			throw new NullPointerException("La operacion ingresada no existe");
		}
		String codigo = crearCodigo();
		for(int k = 0; k < listaCitas.getCantCitas(); k++) {
			if (codigo.equals(listaCitas.getCitaI(k).getCodigo())) {
				codigo = crearCodigo();
				break;
			}
		}
		ingresarCita(c.getRut(),operacion,c.getCiudad(),d.getRut(),fecha,codigo);
	}
	/**
	 * This function creates a code of 10 numbers randomly
	 * @return a code of 10 numbers as a String
	 */
	public String crearCodigo() {
		Random rand = new Random(); 
		int upperbound = 9; // Generates numbers from 0 to 9
		int int_random;
		String codigo = "";
		for(int i = 0; i <10; i ++) {
			int_random = rand.nextInt(upperbound);
			codigo = codigo + Integer.toString(int_random);
		}

		return codigo;
	}
	
	public void completarCita(String rut_cliente, String codigo) {
		Cita cita = null;
		double pago = 0;
		int j;
		for(j = 0; j<listaCitas.getCantCitas(); j++) {
			if(codigo.equals(listaCitas.getCitaI(j).getCodigo())) {
				cita = listaCitas.getCitaI(j);
				break;
			}
		}
		if(j==listaCitas.getCantCitas()) {
			throw new NullPointerException("Codigo incorrecto");
		}
		//prices of the operations
		int blanqueamiento_laser = 136000;
		int exodoncia = 35000;
		int limpieza = 63000;
		int radiografia = 15000;
		int fluoracion = 55000;
		//it identifies the prevision
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

		if(cita.getOperacion().equals("Exodoncia")){
			pago = prevision*exodoncia;
		}
		else if(cita.getOperacion().equals("Limpieza")){
			pago = prevision*limpieza;
		}
		else if(cita.getOperacion().equals("Radiografia")){
			pago = prevision*radiografia;
		}
		else if(cita.getOperacion().equals("Fluoracion")){
			pago = prevision*fluoracion;
		}
		else if(cita.getOperacion().equals("Blanqueamiento laser")){
			pago = prevision*blanqueamiento_laser;
		}
		else {
			throw new NullPointerException("Operacion incorrecta");
		}
		//the dentist gets the 10%, the clinic gets the rest
		double comisiones = cita.getDentista().getComisiones();
		cita.getDentista().setComisiones(comisiones + (pago*0.1));
		for(int y = 0; y < listaClinicas.getCantClinicas(); y++) {
			if(cita.getCiudad().equals(listaClinicas.getClinicaI(y).getCiudad())){
				listaClinicas.getClinicaI(y).setGanancias(listaClinicas.getClinicaI(y).getGanancias() + (pago - (pago*0.1)));
			}
		}
		//delete appointment
		listaCitas.eliminarCita(codigo);
	}
	
	public boolean cancelarCitaCliente(String rut_cliente, String codigo) {
		int i;
		for(i=0;i<listaCitas.getCantCitas();i++) {
			if(codigo.equals(listaCitas.getCitaI(i).getCodigo())) {
				break;
			}
		}
		if(i==listaCitas.getCantCitas()) {
			throw new NullPointerException("La cita ingresada no existe");
			
		}
		else{
			listaCitas.eliminarCita(codigo);
			return true;
		}
		
	}

	public int SueldoDentista(String rut) {
		int Sueldo = 0;
		if(listaDentistas.buscarDentista(rut) != null) {
			Sueldo = listaDentistas.buscarDentista(rut).getSueldo();
			return Sueldo;
		}
		else {
			throw new NullPointerException("El dentista no existe");
		}
	}

	public boolean CancelarCitaDentista(String rut, String codigo) {
		if(listaCitas.eliminarCita(codigo)) {
			return true;
		}
		else {
			throw new NullPointerException("La cita no existe");
		}
	}

	public void Sobreescribir() {
		ArchivoSalida arch;
		try {
			arch = new ArchivoSalida("clientes.txt");
			for(int i = 0; i<1000; i++) {
				if(listaClientes.getClienteI(i) == null) {
					break;
				}
				Registro reg = new Registro(6);
				String[] nombre = listaClientes.getClienteI(i).getNombre().split(" ");
				reg.agregarCampo(nombre[0]);
				reg.agregarCampo(nombre[1]);
				reg.agregarCampo(listaClientes.getClienteI(i).getRut());
				reg.agregarCampo(listaClientes.getClienteI(i).getContrasena());
				reg.agregarCampo(listaClientes.getClienteI(i).getPrevision());
				reg.agregarCampo(listaClientes.getClienteI(i).getCiudad());
				arch.writeRegistro(reg);
			}
		arch.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			arch = new ArchivoSalida("dentistas.txt");
			for(int i = 0; i<1000; i++) {
				if(listaDentistas.getDentistaI(i) == null) {
					break;
				}
				Registro reg = new Registro(8);
				String[] nombre = listaDentistas.getDentistaI(i).getNombre().split(" ");
				reg.agregarCampo(nombre[0]);
				reg.agregarCampo(nombre[1]);
				reg.agregarCampo(listaDentistas.getDentistaI(i).getRut());
				reg.agregarCampo(listaDentistas.getDentistaI(i).getContrasena());
				reg.agregarCampo(listaDentistas.getDentistaI(i).getSueldo());
				reg.agregarCampo(listaDentistas.getDentistaI(i).getComisiones());
				reg.agregarCampo(listaDentistas.getDentistaI(i).getCiudad());
				reg.agregarCampo(listaDentistas.getDentistaI(i).getExperiencia());
				arch.writeRegistro(reg);
			}
		arch.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			arch = new ArchivoSalida("clinicas.txt");
			for(int i = 0; i<1000; i++) {
				if(listaClinicas.getClinicaI(i) == null) {
					break;
				}
				Registro reg = new Registro(3);
				reg.agregarCampo(listaClinicas.getClinicaI(i).getCiudad());
				reg.agregarCampo(listaClinicas.getClinicaI(i).getGanancias());
				reg.agregarCampo(listaClinicas.getClinicaI(i).getEstado());
				arch.writeRegistro(reg);
			}
		arch.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			arch = new ArchivoSalida("citas.txt");
			for(int i = 0; i<1000; i++) {
				if(listaCitas.getCitaI(i) == null) {
					break;
				}
				Registro reg = new Registro(6);
				reg.agregarCampo(listaCitas.getCitaI(i).getCliente().getRut());
				reg.agregarCampo(listaCitas.getCitaI(i).getOperacion());
				reg.agregarCampo(listaCitas.getCitaI(i).getCiudad());
				reg.agregarCampo(listaCitas.getCitaI(i).getDentista().getRut());
				reg.agregarCampo(listaCitas.getCitaI(i).getFecha());
				reg.agregarCampo(listaCitas.getCitaI(i).getCodigo());
				arch.writeRegistro(reg);
			}
		arch.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
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
		int j;
		for(j = 0; j < listaCitas.getCantCitas(); j++) {
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

	public String obtenerSueldosDentistas() {
		int i = 0;
		int j = 0;
		int sueldo = 0;
		int experiencia = 0;
		double comisiones = 0;
		double sf = 0;
		String t = "";
		for(i = 0; i < listaClinicas.getCantClinicas(); i++){
			for(j = 0; j < listaClinicas.getClinicaI(i).getCantDentistas(); j++) {
				t += "Informacion del dentista: " + listaClinicas.getClinicaI(i).getLista()[j].toString() + "\n";
				// final salary
				sueldo = listaClinicas.getClinicaI(i).getLista()[j].getSueldo();
				experiencia = listaClinicas.getClinicaI(i).getLista()[j].getExperiencia();
				comisiones = listaClinicas.getClinicaI(i).getLista()[j].getComisiones();
				sf = (sueldo*(experiencia*1.1) + comisiones);				
				t += "Sueldo final: " + sf + "\n" + "\n";
			}
		}
		return t;
	}

	public String obtenerInfoGanancias() {
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
		if(sePuedeContratar) {
			ingresarDentista(nombre_completo,rut,contrasena,sueldo,comisiones,ciudad,experiencia);
			System.out.println("Dentista ingresado con exito");
		}
	}
}
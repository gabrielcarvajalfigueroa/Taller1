package Taller1.logica;

import java.io.IOException;

import ucn.*;

public class App {

	public static void main(String[] args) throws IOException {
		SistemaClinicas sistema = new SistemaClinicasImpl(); //levanta el sistema
		LeerClinicas(sistema);
		LeerClientes(sistema);
		LeerDentistas(sistema);
		LeerCitas(sistema);
		
		
		IniciarSesion(sistema);
		
	}
	
	public static void LeerClientes(SistemaClinicas sistema) throws IOException {
		ArchivoEntrada arch = new ArchivoEntrada("clientes.txt");
		
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			
			String nombre = reg.getString();
			String apellido = reg.getString();
			String nombre_completo = nombre + " " + apellido; // Se juntan los nombres el cliente queda con su nombre completo en el atributo
			String rut = reg.getString();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			String contrasena = reg.getString();
			String prevision = reg.getString();
			String ciudad = reg.getString();
		
			
			boolean ingreso = sistema.ingresarCliente(nombre_completo,rut,contrasena,prevision,ciudad);
			
			if(!ingreso) { // Revisar la logica dentro del if
				StdOut.println("No se ingreso el cliente no hay espacio"); 
			}						
		}	
	}
	/**
	 * Se leen los dentistas del txt, se crean los objetos y se añaden a su lista
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerDentistas(SistemaClinicas sistema) throws IOException{
		ArchivoEntrada arch = new ArchivoEntrada("dentistas.txt");
		
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			
			String nombre = reg.getString();
			String apellido = reg.getString();
			String nombre_completo = nombre + " " + apellido;
			String rut = reg.getString();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			String contrasena = reg.getString();
			String sueldo  = reg.getString(); // se recibe como string para quitarle el punto
			sueldo = sueldo.replace(".", ""); // quita el punto
			int sueldo_int = Integer.parseInt(sueldo); // pasa el string a int
			String comisiones = reg.getString(); // lo mismo que con sueldo
			comisiones = comisiones.replace(".","");
			int comisiones_int = Integer.parseInt(comisiones);
			String ciudad = reg.getString();
			int experiencia = reg.getInt();
			
			boolean ingreso = sistema.ingresarDentista(nombre_completo, rut, contrasena, sueldo_int, comisiones_int, ciudad, experiencia);
			
			
			if(!ingreso) {System.out.println("No se ingreso el dentista no hay espacio");}
		}
	}
	/**
	 * <h1><em>LeerClinicas</em></h1> ESTE METODO SE USA PARA LEER LAS CLINICAS<p>hiaosndkjankj
	 * @param sistema ESTE ES EL SISTEMA DE LA APP
	 * @throws IOException
	 */
	public static void LeerClinicas(SistemaClinicas sistema) throws IOException{
		ArchivoEntrada arch = new ArchivoEntrada("clinicas.txt");
		
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			
			String ciudad = reg.getString();
			String ganancias = reg.getString();
			ganancias.replace(".", "");
			int ganancias_int = Integer.parseInt(ganancias);
			String estado = reg.getString();
			
			boolean ingreso = sistema.ingresarClinica(ciudad, ganancias_int, estado);
			
			if(!ingreso) {System.out.println("No se ingreso la clinica no hay espacio");}
		}
	}
	
	public static void LeerCitas(SistemaClinicas sistema) throws IOException{
		ArchivoEntrada arch = new ArchivoEntrada("citas.txt");
		
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			
			String rutPaciente = reg.getString();
			rutPaciente = rutPaciente.replace(".","");
			rutPaciente = rutPaciente.replace("-", "");
			String operacion = reg.getString();
			String ciudad = reg.getString();
			String rutDentista = reg.getString();
			rutDentista = rutDentista.replace(".", "");
			rutDentista = rutDentista.replace("-", "");
			String fecha = reg.getString();
			String codigo = reg.getString();
			
			boolean ingreso = sistema.ingresarCita(rutPaciente, operacion, ciudad, rutDentista, fecha, codigo);
			
			if(!ingreso) {System.out.println("No se ingreso la cita porque no hay espacio");}
		}
	}
	
	private static void IniciarSesion(SistemaClinicas sistema) {
		System.out.println("Ingrese su RUT: ");
		String rutIngresado = StdIn.readString();
		rutIngresado = rutIngresado.replace(".", "");
		rutIngresado = rutIngresado.replace("-", "");
		System.out.println("Ingrese su contrasena");
		String contraIngresada = StdIn.readString();
		
		while(true) {
			if(rutIngresado.equals("CERRAR") && contraIngresada.equals("CERRAR")) {
				System.out.println("Sistema cerrado");
				break;
			}
			if(sistema.VerificadorC(rutIngresado, contraIngresada) == true) {
				System.out.println("Inicio de sesion de cliente exitoso");
				MenuCliente(sistema, rutIngresado);
			}
			else if(sistema.VerificadorD(rutIngresado, contraIngresada) == true) {
				System.out.println("Inicio de sesion de dentista exitoso");
				MenuDentista(sistema);
			}
			else {
				System.out.println("Rut no encontrado");
				System.out.println("1) Intentar nuevamente");
				System.out.println("2) Registrar un nuevo rut");
				System.out.println("3) Cerrar el sistema");
				String entrada = StdIn.readString();
				if(entrada.equals("3")) {
					break;
				}
				else if(entrada.equals("1")){
				}
				else {
					System.out.println("Ingrese un nuevo RUT");
					String nuevoRut = StdIn.readString();
					System.out.println("Cree una contrasena");
					String nuevacontra = StdIn.readString();
					System.out.println("Nombre: ");
					String nombre = StdIn.readString();
					System.out.println("Apellido: ");
					String apellido = StdIn.readString();
					nombre = nombre + apellido;
					System.out.println("Prevision: ");
					String nuevaPrev = StdIn.readString();
					System.out.println("Ciudad: ");
					String nuevaCiudad = StdIn.readString();
					boolean v = sistema.ingresarCliente(nombre, nuevoRut, nuevacontra, nuevaPrev, nuevaCiudad);
					if(v) {
						System.out.println("Cliente ingresado al sistema exitosamente");
					}
					else {
						System.out.println("Sistema de clientes lleno, lo sentimos.");
					}
				}
				
			}
			System.out.println("Ingrese su RUT: ");
			rutIngresado = StdIn.readString();
			rutIngresado = rutIngresado.replace(".", "");
			rutIngresado = rutIngresado.replace("-", "");
			System.out.println("Ingrese su contrasena");
			contraIngresada = StdIn.readString();
		}
	}

	private static void MenuDentista(SistemaClinicas sistema) {
		System.out.println("Menu Dentista: Eliga la acción a realizar");
		System.out.println("a) Desplegar sueldo");
		System.out.println("b) Cancelar cita");
		System.out.println("c) Volver");
		String entrada = StdIn.readString();
		if(entrada.equals("a")) {
			System.out.println("Sueldo desplegado");
		}
		else if(entrada.equals("b")) {
			System.out.println("Cita cancelada");
		}
		else if(entrada.equals("c")) {
			
		}
		else {
			System.out.println("Error de entrada.");
		}
	}

	private static void MenuCliente(SistemaClinicas sistema, String rut) {
		System.out.println("Menu Cliente: Eliga la acción a realizar");
		System.out.println("1) Desplegar citas agendadas");
		System.out.println("2) Agendar cita");
		System.out.println("3) Completar cita");
		System.out.println("4) Cancelar cita");
		System.out.println("5) Volver");
		String entrada = StdIn.readString();
		if(entrada.equals("1")) {
			System.out.println(sistema.CitasAgendadas(rut));
		}
		else if(entrada.equals("2")) {
			System.out.println("Cita agendada");
		}
		else if(entrada.equals("3")) {
			System.out.println("Cita completada");
		}
		else if(entrada.equals("4")) {
			System.out.println("Cita cancelada");
		}
		else if(entrada.equals("5")) {
		}
		else {
			System.out.println("Error de entrada.");
		}
	}
	
}
	
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
		
		
		StdOut.println(sistema.obtenerClientes());
		StdOut.println(sistema.obtenerDentistas());
		System.out.println(sistema.obtenerClinicas());
	}
	public static void LeerClientes(SistemaClinicas sistema) throws IOException {
		ArchivoEntrada arch = new ArchivoEntrada("clientes.txt");
		
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			
			String nombre = reg.getString();
			String apellido = reg.getString();
			String nombre_completo = nombre + " " + apellido; // Se juntan los nombres el cliente queda con su nombre completo en el atributo
			String rut = reg.getString();
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
			String operacion = reg.getString();
			String ciudad = reg.getString();
			String rutDentista = reg.getString();
			String fecha = reg.getString();
			String codigo = reg.getString();
			
			boolean ingreso = sistema.ingresarCita(rutPaciente, operacion, ciudad, rutDentista, fecha, codigo);
			
			if(!ingreso) {System.out.println("No se ingreso la cita porque no hay espacio");}
		}
	}
	
	
	
}

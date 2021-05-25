package Taller1.logica;

import java.io.IOException;

import ucn.*;

public class App {

	public static void main(String[] args) throws IOException {
		SistemaClinicas sistema = new SistemaClinicasImpl(); //levanta el sistema
		
		LeerClientes(sistema);
		
		StdOut.println(sistema.obtenerClientes());
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
		}
		
	}
}

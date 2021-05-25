package Taller1.logica;

public interface SistemaClinicas {
	
	public String CitasAgendadas(String rut);
	
	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad);
	
	public String obtenerClientes();

}

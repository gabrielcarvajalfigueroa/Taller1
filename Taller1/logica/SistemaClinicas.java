package Taller1.logica;

import Taller1.dominio.*;

public interface SistemaClinicas {
	
	public String CitasAgendadas(String rut);
	
	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad);
	
	public boolean ingresarDentista(String nombre,String rut,String contrasena,int sueldo,int comisiones,String ciudad,int experiencia);
	
	public boolean ingresarClinica(String ciudad,int ganancias,String estado);
	
	public boolean ingresarCita(String rutPaciente,String operacion,String ciudad,String rutDentista,String fecha,String codigo);
	
	public boolean VerificadorC(String rut, String contrasena);
	
	public boolean VerificadorD(String rut, String contrasena);
	
	public String obtenerDentistas();
	
	public String obtenerClinicas();
}

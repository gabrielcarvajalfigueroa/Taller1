package Taller1.logica;

import Taller1.dominio.*;

public interface SistemaClinicas {
	
	public String CitasAgendadas(String rut);
	
	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad);
	
	public boolean ingresarDentista(String nombre,String rut,String contrasena,int sueldo,double comisiones,String ciudad,int experiencia);
	
	public boolean ingresarClinica(String ciudad,int ganancias,String estado);
	
	public boolean ingresarCita(String rutPaciente,String operacion,String ciudad,String rutDentista,String fecha,String codigo);
	
	public boolean VerificadorC(String rut, String contrasena);
	
	public boolean VerificadorD(String rut, String contrasena);
	
	public String crearCodigo(); // metodo extra para crear codigos para citas
	
	public String agendarCita(String rut_cliente);
	
	public String obtenerDentistas();
	
	public String obtenerClinicas();
	
	public String obtenerCitas();
	
	public String completarCita(String rut_cliente);
	
	public boolean cancelarCitaCliente(String rut_cliente);
	
	public void clienteCovidDetectado();
	
	public void obtenerSueldosDentistas();
	
	public String obtenerInfoGanancias();
	
	public void higienizarLocal();
	
	public void contratarDentista();
}

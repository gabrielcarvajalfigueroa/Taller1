package Taller1.logica;
/**
 * Set of operations that the system can do.
 * @author Pablo S. Rodriguez <pablo.rodriguez01@alumnos.ucn.cl>
 * @version 2021.06.13
 */
public interface SistemaClinicas {
	/**
	 * It returns the scheduled appointments of the client
	 * @param rut of the client
	 * @return the scheduled appointments as a String
	 */
	public String CitasAgendadas(String rut);
	/**
	 * It returns the scheduled appointments of the dentist
	 * @param rut of the dentist
	 * @return the scheduled appointments as a String
	 */
	public String CitasAgendadasD(String rut);
	/**
	 * Enters a client to the system
	 * @param nombre
	 * @param rut
	 * @param contrasena
	 * @param prevision
	 * @param ciudad
	 * @return if there is slot in the system returns true, otherwise false
	 */
	public boolean ingresarCliente(String nombre,String rut,String contrasena,String prevision,String ciudad);
	/**
	 * Enters a dentist to the system and associates it with a clinic
	 * @param nombre
	 * @param rut
	 * @param contrasena
	 * @param sueldo
	 * @param comisiones
	 * @param ciudad
	 * @param experiencia
	 * @return if there is slot in the system returns true, otherwise false
	 */
	public boolean ingresarDentista(String nombre,String rut,String contrasena,int sueldo,double comisiones,String ciudad,int experiencia);
	/**
	 * Enters a clinic to the system
	 * @param ciudad
	 * @param ganancias
	 * @param estado
	 * @return if there is slot in the system returns true, otherwise false
	 */
	public boolean ingresarClinica(String ciudad,double ganancias,String estado);
	/**
	 * Enters a appointment to the system
	 * Requirements:
	 * 		the client has to exist
	 * 		that a dentist is available
	 * 
	 * @param rutPaciente
	 * @param operacion
	 * @param ciudad
	 * @param rutDentista
	 * @param fecha
	 * @param codigo
	 * @return true if the operation was successful
	 */
	public boolean ingresarCita(String rutPaciente,String operacion,String ciudad,String rutDentista,String fecha,String codigo);
	/**
	 * It checks that the rut and the password belongs to a client in the system
	 * @param rut
	 * @param contrasena
	 * @return true if the client exists, false otherwise
	 */
	public boolean VerificadorC(String rut, String contrasena);
	/**
	 * It checks that the rut and the password belongs to a dentist in the system
	 * @param rut
	 * @param contrasena
	 * @return true if the dentist exists, false otherwise
	 */
	public boolean VerificadorD(String rut, String contrasena);
	/**
	 * It schedule an appointment associated with a client and a dentist. 
	 * Requirements:
	 * 		It has to exist a clinic in the city of the client
	 * 		The clinic has to be available and with at least one dentist
	 * 		The entered operation has to exist	
	 * @param rut_cliente
	 * @param fecha
	 * @param operacion
	 */
	public void agendarCita(String rut_cliente, String fecha, String operacion);
	/**
	 * It completes a client's appointment and pays the cost of the operation to the clinic and the dentist,
	 *  then its deleted from the system.
	 * Requirements:
	 * 		The entered code has to correspond with a existing appointment
	 * 		The operation has to be correct
	 * @param rut_cliente
	 * @param codigo
	 */
	public void completarCita(String rut_cliente, String codigo);
	/**
	 * It cancels a client's appointment and deletes it from the system.
	 * Requirements:
	 * 		The appointment has to exist in the system.
	 * @param rut_cliente
	 * @param codigo
	 * @return true if the operation is successful
	 */
	public boolean cancelarCitaCliente(String rut_cliente, String codigo);
	/**
	 * Gets the salary of a dentist
	 * Requirements:
	 * 		The dentist has to exist
	 * 
	 * @param rut
	 * @return the salary of the dentist as a integer
	 */
	public int SueldoDentista(String rut);
	/**
	 * It cancels a dentist's appointment and deletes it from the system
	 * Requirements:
	 * 		The appointment has to exist
	 * @param rut
	 * @param codigo
	 * @return true if the operation was successful
	 */
	public boolean CancelarCitaDentista(String rut, String codigo);
	/**
	 * It rewrites the text files "clientes.txt", "dentistas.txt", "clinicas.txt", "citas.txt"
	 * with the updated information
	 */
	public void Sobreescribir();
	/**
	 * It display the list of clinics and changes the state of a clinic to "Insalubre", the clinic is selected by
	 * the city entered by the user
	 */
	public void clienteCovidDetectado();
	/**
	 * It gets the information of all dentist including their final salary. Final salary is calculated using
	 * the following formula: (salary*(experience*1.1) + commissions)
	 * @return a list with the information of all dentists including their final salary as a String
	 */
	public String obtenerSueldosDentistas();
	/**
	 * It gets the profits of all the clinics
	 * @return a list with the profit of all the clinics as a String
	 */
	public String obtenerInfoGanancias();
	
	public void higienizarLocal();
	
	public void contratarDentista();
}
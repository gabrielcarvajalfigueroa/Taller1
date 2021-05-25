package Taller1.dominio;

public class Cita {
	private Cliente cliente;
	private String operacion;
	private String ciudad;
	private Dentista dentista;
	private String fecha;
	private String codigo;
	
	
	public String toString() {
		return "Cita [cliente=" + cliente + ", operacion=" + operacion + ", ciudad=" + ciudad + ", dentista=" + dentista
				+ ", fecha=" + fecha + ", codigo=" + codigo + "]";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cita(Cliente cliente, String operacion, String ciudad, Dentista dentista, String fecha, String codigo) {
		this.cliente = cliente;
		this.operacion = operacion;
		this.ciudad = ciudad;
		this.dentista = dentista;
		this.fecha = fecha;
		this.codigo = codigo;
	}
	
	
	
	
}

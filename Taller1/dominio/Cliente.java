package Taller1.dominio;

public class Cliente {
	private String nombre;
	private String rut;
	private String contrasena;
	private String prevision;
	private String ciudad;
	
	public Cliente(String nombre,String rut,String contrasena,String prevision,String ciudad) {
		this.nombre = nombre;
		this.rut = rut;
		this.contrasena = contrasena;
		this.prevision = prevision;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", rut=" + rut + ", contrasena=" + contrasena + ", prevision=" + prevision
				+ ", ciudad=" + ciudad + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getPrevision() {
		return prevision;
	}

	public void setPrevision(String prevision) {
		this.prevision = prevision;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}

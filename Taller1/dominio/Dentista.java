package Taller1.dominio;

public class Dentista {
	private String nombre;
	private String rut;
	private String contrasena;
	private int sueldo;
	private int comisiones;
	private String ciudad;
	private int experiencia;
	public Dentista(String nombre, String rut, String contrasena, int sueldo, int comisiones, String ciudad,
			int experiencia) {
		this.nombre = nombre;
		this.rut = rut;
		this.contrasena = contrasena;
		this.sueldo = sueldo;
		this.comisiones = comisiones;
		this.ciudad = ciudad;
		this.experiencia = experiencia;
	}
	@Override
	public String toString() {
		return "Dentista [nombre=" + nombre + ", rut=" + rut + ", contrasena=" + contrasena + ", sueldo=" + sueldo
				+ ", comisiones=" + comisiones + ", ciudad=" + ciudad + ", experiencia=" + experiencia + "]";
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
	public int getSueldo() {
		return sueldo;
	}
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	public int getComisiones() {
		return comisiones;
	}
	public void setComisiones(int comisiones) {
		this.comisiones = comisiones;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
}

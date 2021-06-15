package Taller1.dominio;

public class Dentista {
	private String nombre;
	private String rut;
	private String contrasena;
	private int sueldo;
	private double comisiones;
	private String ciudad;
	private int experiencia;
	public Dentista(String nombre, String rut, String contrasena, int sueldo, double comisiones, String ciudad,
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
		return "nombre:" + nombre + ", rut:" + rut  + ", sueldo:" + sueldo
				+ ", comisiones:" + comisiones + ", ciudad:" + ciudad + ", experiencia:" + experiencia;
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
	public double getComisiones() {
		return comisiones;
	}
	public void setComisiones(double d) {
		this.comisiones = d;
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

package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {

	// DECLACIÓN DE ATRIBUTOS
	private static final String ER_TELEFONO = ("[69][0-9]{8}");
	private static final String ER_CORREO = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private String nombre;
	private String correo;
	private String telefono;

	// GENERAMOS PRIMERO GETTER Y SETTERS NOMBRE
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		} else if (nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	// GENERAMOS GETTER Y SETTER CORREO
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {

		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}

		Pattern pat = Pattern.compile(ER_CORREO);
		Matcher mat = pat.matcher(correo);
		if (mat.matches() == false) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}
		this.correo = correo;
	}

	// GENERAMOS GETTER Y SETTER TELEFONO
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		if (telefono == null) {
			this.telefono = telefono;
		} else {
			Pattern pat = Pattern.compile(ER_TELEFONO);
			Matcher mat = pat.matcher(telefono);
			if (mat.matches() == false) {
				throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
			}
		}
		this.telefono = telefono;
	}

	// CONSTRUCTOR CON DOS PARAMETROS
	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	// AHORA PODEMOS GENERAR EL CONSTRUCTOR CON TRES PARAMETROS
	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	// GENERAMOS EL CONSTRUCTOR COPIA
	public Profesor(Profesor otroProfesor) {
		if (otroProfesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		} else {
			setNombre(otroProfesor.getNombre());
			setCorreo(otroProfesor.getCorreo());
			setTelefono(otroProfesor.getTelefono());
		}
	}

	// GENERAMOS MÉTODOS HAASH AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Profesor))
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	// GENERAMOS MÉTODO STRING
	@Override
	public String toString() {
		if (telefono != null) {
			return "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono;
		}
		return "nombre=" + nombre + ", correo=" + correo;
	}

}

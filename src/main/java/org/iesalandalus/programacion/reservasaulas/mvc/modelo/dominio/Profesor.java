package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
	
	// DECLACIÓN DE ATRIBUTOS
	private static final String ER_TELEFONO = ("[69][0-9]{8}");
	private static final String ER_CORREO = "^[A-Za-z0-9+_.-]+@(.+)$\", ^[A-Za-z0-9+_.-]";
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
	private String getCorreo() {
		return correo;
	}


	/**
	 * @param correo the correo to set
	 */
	private void setCorreo(String correo) {
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
	private String getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	private void setTelefono(String telefono) {
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
	
	// AHORA PODEMOS GENERAR EL CONSTRUCTOR CON TRES PARAMETROS
	public Profesor(String nombre, String correo, String telefono) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
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


	
	
	//GENERAMOS MÉTODOS HAASH AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}


	
	//GENERAMOS MÉTODO STRING
	@Override
	public String toString() {
		if (telefono != null) {
			return "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono;
		}
		return "nombre=" + nombre + ", correo=" + correo;
	}

	
}

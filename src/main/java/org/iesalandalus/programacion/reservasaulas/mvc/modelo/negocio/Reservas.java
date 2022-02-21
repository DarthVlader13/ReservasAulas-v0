package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	// DECLARACIÓN DE ATRIBUTOS
	private int capacidad;
	private int tamano;
	Reserva[] coleccionReservas;

	// CREAMOS CONSTRUCTOR CON PARÁMETROS
	public Reservas(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionReservas = new Reserva[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

	// GENERAMOS GETTERS
	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamano;
	}

	// CREAMOS MÉTODO RESERVA[] GET() PARA EVITAR ALIASING
	public Reserva[] get() {
		return copiaProfundaReservas();
	}

	// CREAMOS COPIAPROFUNDA
	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaReservas = new Reserva[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i] != null)
				copiaReservas[i] = new Reserva(coleccionReservas[i]);
		}
		return copiaReservas;

	}

	// CREAMOS MÉTODO INSERTAR
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}
		if (tamanoSuperado(indice)) {
			coleccionReservas[indice] = new Reserva(reserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");
		}
	}

	// CREAMOS MÉTODO BUSCARINDICE
	private int buscarIndice(Reserva reserva) {
		boolean encontrado = false;
		int resultado = 0;
		for (int i = 0; i <= tamano - 1; i++) {
			if (reserva.equals(coleccionReservas[i])) {
				encontrado = true;
				resultado = i;
			}
		}
		if (encontrado) {
			return resultado;
		} else {
			return tamano + 1;
		}
	}

	// CREAMOS MÉTODO TAMANOSUPERADO Y CAPACIDADSUPERADA
	private boolean tamanoSuperado(int indice) {
		if (indice > tamano) {
			return true;
		}
		return false;
	}

	private boolean capacidadSuperada(int tamano) {
		if (tamano >= capacidad) {
			return true;
		}
		return false;
	}

	// CREAMOS MÉTODO BUSCAR
	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(reserva);
		}
	}

	// CREAMOS MÉTODO DESPLAZARUNAPOSICIONHACIAIZQUIERDA
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		tamano--;
	}

	// CREAMOS MÉTODO BORRAR
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		int indice = 0;
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		indice = buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionReservas[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
	}

	// CREAMOS MÉTODO REPRESENTAR
	public String[] representar() {
		String[] representacion = new String[tamano];
		for (int i = 0; i < representacion.length; i++)
			if (coleccionReservas != null)
				representacion[i] = "" + coleccionReservas[i];
		return representacion;
	}

	// CREAMOS MÉTODO GETRESERVASPROFESOR
	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor = new Reserva[capacidad];
		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (profesor.equals(coleccionReservas[i].getProfesor())) {
				reservasProfesor[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasProfesor;
	}

	// CREAMOS MÉTODO GETRESERVASAULA
	public Reserva[] getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede reservar un aula nula.");
		}
		Reserva[] reservasAula = new Reserva[capacidad];
		for (int i = 0; i < tamano; i++)
			if (coleccionReservas[i].getAula().equals(aula))
				reservasAula[i] = coleccionReservas[i];
		return reservasAula;

	}

	// CREAMOS MÉTODO GETRESERVASPERMANENCIA
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[capacidad];
		int indice = 0;
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (permanencia.equals(coleccionReservas[i].getPermanencia())) {
				reservasPermanencia[indice] = new Reserva(coleccionReservas[i]);
				indice++;
			}
		}
		return reservasPermanencia;
	}

	// CREAMOS MÉTODO CONSULTARDISPONIBILIDAD
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean disponibilidad = true;
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		for (int i = 0; i <= tamano - 1; i++) {
			if (aula.equals(coleccionReservas[i].getAula())
					&& permanencia.equals(coleccionReservas[i].getPermanencia())) {
				disponibilidad = false;
			}
		}
		return disponibilidad;
	}

}

package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	// DECLARACIÓN DE ATRIBUTOS
	private int capacidad;
	private int tamano;
	Profesor[] coleccionProfesores;

	// CREAMOS MÉTODO GETTAMANO Y GET CAPACIDAD
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

	// CREAMOS MÉTODO PROFESOR PARA EVITAR ALIASING
	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	// CONSTRUCTOR CON PARAMETROS
	public Profesores(int capacidadProfesores) {
		if (capacidadProfesores <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			coleccionProfesores = new Profesor[capacidadProfesores];
			this.capacidad = capacidadProfesores;
			tamano = 0;
		}
	}

	// CREAMOS MÉTODO COPIAPROFUNDAPROFESORES
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionProfesores[i] != null)
				copiaProfesores[i] = new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesores;

	}

	// CREAMOS MÉTODO INSERTAR
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int posibleHueco = buscarIndice(profesor);
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if (tamanoSuperado(posibleHueco)) {
			coleccionProfesores[tamano] = new Profesor(profesor);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
	}
	

	// CREAMOS MÉTODO BUSCAR ÍNDICE
	private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (!tamanoSuperado(indice) && !profesorEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				profesorEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
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

	// CREAMOS MÉTODO DESPLAZARUNAPOSICIONALAIZQUIERDA
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionProfesores[i] = coleccionProfesores[i + 1];
		}
		tamano--;
	}

	// CREAMOS MÉTODO BUSCAR
	public Profesor buscar (Profesor profesor) {
		if (profesor==null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice=buscarIndice(profesor);
		if(tamanoSuperado(indice)) {
			return null;
		}
		else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}

	// CREAMOS MÉTODO BORRAR
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		int indice = 0;
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		indice = buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			coleccionProfesores[tamano - 1] = null;
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
	}

	// CREAMOS MÉTODO REPRESENTAR
	public String[] representar() {
		String[] representacion = new String[tamano];
		for (int i = 0; i < representacion.length; i++)
			if (coleccionProfesores[i] != null)
				representacion[i] ="" + coleccionProfesores[i];
		return representacion;
	}
}

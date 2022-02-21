package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	
	//DECLARACIÓN DE ATRIBUTOS
	private int capacidad;
	private int tamano;
	Aula[] coleccionAulas;
	
	//CONSTRUCTOR CON PARAMETROS
	public Aulas(int capacidadAulas) {
		if (capacidadAulas <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			coleccionAulas = new Aula[capacidadAulas];
			this.capacidad = capacidadAulas;
			tamano = 0;
		}
	}
	
	//CREAMOS MÉTODO AULA GET, CREANDO COPIA PROFUNDA DEL ARRAY PARA EVITAR EL ALIASING
	public Aula[] get() {
		return copiaProfundaAulas();
	}
	
	private Aula[] copiaProfundaAulas() {
		Aula[] copiaAulas = new Aula[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionAulas[i] != null)
				copiaAulas[i] = new Aula(coleccionAulas[i]);
		}
		return copiaAulas;

	}
	
	//CREAMOS MÉTODO GETTAMANO
	public int getTamano() {
		return tamano;
	}
	
	//CREAMOS MÉTODO GETCAPACIDAD
	public int getCapacidad() {
		return capacidad;
	}
	
	//CREAMOS MÉTODO BUSCAR INDICE
		private int buscarIndice(Aula aula) {
			int indice = 0;
			boolean aulaEncontrada = false;
			while (!tamanoSuperado(indice) && !aulaEncontrada) {
				if (coleccionAulas[indice].equals(aula)) {
					aulaEncontrada = true;
				} else {
					indice++;
				}
			}
			return indice;
		}
		
		
	//CREAMOS MÉTODO INSERTAR
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
		if (tamanoSuperado(indice)) {
			coleccionAulas[indice] = new Aula(aula);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
	}
	
	//CREAMOS MÉTODO TAMANOSUPERADO Y CAPACIDADSUPERADA
	private boolean tamanoSuperado(int indice) {
		boolean tamanoSuperado;
		tamanoSuperado = (indice >= tamano);
		return tamanoSuperado;
	}
	
	private boolean capacidadSuperada(int indice) {
		boolean capacidadSuperada;
		capacidadSuperada = (indice >= capacidad);
		return capacidadSuperada;

	}
	
	//CREAMOS MÉTODO BUSCAR
	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(aula);
		}
	}
	
	//CREAMOS MÉTODO BORRAR
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}
	
	//CREAMOS MÉTODO DESPLAZARUNAPOSICIONHACIALAIZQ
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionAulas[i] = coleccionAulas[i + 1];
		}
		tamano--;
	}
	
	//CREAMOS MÉTODO REPRESENTAR
	public String[] representar() {
		String[] representacion = new String[tamano];
		for (int i = 0; i < representacion.length; i++) {
			if (coleccionAulas[i] != null)
				representacion[i] = "nombre Aula=" + coleccionAulas[i].getNombre();
		}
		return representacion;
	}

	
	
	
}

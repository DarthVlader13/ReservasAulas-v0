package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {

	// DECLARACIÓN DE ATRIBUTOS
	Modelo modelo;
	Vista vista;

	// CREAMOS CONSTRUCTOR CON PARÁMETROS DE MODELO Y VISTA
	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}

		if (vista == null) {
			throw new NullPointerException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	// CREAMOS MÉTODO COMENZAR
	public void comenzar() {
		vista.comenzar();
	}

	// CREAMOS MÉTODO TERMINAR
	public void terminar() {
		vista.salir();
	}

	// CREAMOS MÉTODO INSERTARAULA
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}

	// CREAMOS MÉTODO INSERTARPROFESOR
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}

	// CREAMOS MÉTODO BORRARAULA
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}

	// CREAMOS MÉTODO BORRARPROFESOR
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}

	// CREAMOS MÉTODO BUSCARAULA
	public Aula buscarAula(Aula aula) {
		return modelo.buscarAula(aula);
	}

	// CREAMOS MÉTODO BUSCARPROFESOR
	public Profesor buscarProfesor(Profesor profesor) {
		return modelo.buscarProfesor(profesor);
	}

	// CREAMOS MÉTODO REPRESENTARAULAS
	public String[] representarAulas() {
		return modelo.representarAulas();
	}

	// CREAMOS MÉTODO REPRESENTARPROFESOR
	public String[] representarProfesores() {
		return modelo.representarProfesores();
	}

	// CREAMOS MÉTODO REPRESENTARRESERVAS
	public String[] representarReservas() {
		return modelo.representarReservas();
	}

	// CREAMOS MÉTODO REALIZARRESERVA
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}

	// CREAMOS MÉTODO ANULARRESERVA
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.anularReserva(reserva);

	}

	// CREAMOS MÉTODO GETRESERVASPROFESOR
	public Reserva[] getReservasProfesor(Profesor profesor) {
		return modelo.getReservasProfesor(profesor);
	}

	// CREAMOS MÉTODO GETRESERVASAULA
	public Reserva[] getReservasAula(Aula aula) {
		return modelo.getReservasAula(aula);
	}

	// CREAMOS MÉTODO GETRESERVASPERMANENCIA
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return modelo.getReservasPermanencia(permanencia);
	}

	// CREAMOS MÉTODO CONSULTARDISPONIBILIDAD
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}
}

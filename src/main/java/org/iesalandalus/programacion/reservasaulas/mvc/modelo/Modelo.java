package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	// DECLARACIÓN DE ATRIBUTOS
	private static int CAPACIDAD = 10;
	Profesores profesores;
	Aulas aulas;
	Reservas reservas;

	// CREAMOS EL CONSTRUCTOR QUE NOS CREA LOS OBJETOS ANTERIORES CON LA CAPACIDAD
	// REQUERIDA
	public Modelo() {
		aulas = new Aulas(CAPACIDAD);
		profesores = new Profesores(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);
	}

	// GENERAMOS GETTERS DE AULAS Y NUMAULAS
	public Aula[] getAulas() {
		return aulas.get();
	}

	public int getNumAulas() {
		return aulas.getTamano();
	}

	// CREAMOS MÉTODO REPRESENTARAULAS
	public String[] representarAulas() {
		String[] listaAulas = aulas.representar();
		boolean vacio = true;
		for (String s : listaAulas) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaAulas;
	}

	// CREAMOS MÉTODO BUSCARAULA
	public Aula buscarAula(Aula aula) {
		Aula aulaEncontrada = aulas.buscar(aula);
		if (aulaEncontrada == null) {
			return null;
		} else {
			return new Aula(aulaEncontrada);
		}
	}

	// CREAMOS MÉTODO INSERTARAULA
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}

	// CREAMOS MÉTODO BORRARAULA
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	// GENERAMOS GETTER PROFESORES Y NUMPROFESORES
	public Profesor[] getProfesores() {
		return profesores.get();
	}

	public int getNumProfesores() {
		return profesores.getTamano();
	}

	// CREAMOS MÉTODO REPRESENTARPROFESORES
	public String[] representarProfesores() {
		String[] listaProfesores = profesores.representar();
		boolean vacio = true;
		for (String s : listaProfesores) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaProfesores;
	}

	// CREAMOS MÉTODO BUSCARPROFESOR
	public Profesor buscarProfesor(Profesor profesor) {
		Profesor profesorEncontrado = profesores.buscar(profesor);
		if (profesorEncontrado == null) {
			return null;
		}
		return new Profesor(profesorEncontrado);
	}

	// CREAMOS MÉTODO INSERTARPROFESOR
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	// CREAMOS MÉTODO BORRARPROFESOR
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	// GENERAMOS GETTERS RESERVAS Y NUMRESERVAS
	public Reserva[] getReservas() {
		return reservas.get();
	}

	public int getNumReservas() {
		return reservas.getTamano();
	}

	// CREAMOS MÉTODO REPRESENTARRESERVAS
	public String[] representarReservas() {
		String[] listaReservas = reservas.representar();
		boolean vacio = true;
		for (String s : listaReservas) {
			if (s != null) {
				vacio = false;
			}
		}
		if (vacio == true) {
			return null;
		}
		return listaReservas;
	}

	// CREAMOS MÉTODO BUSCARRESERVA
	public Reserva buscarReserva(Reserva reserva) {
		Reserva reservaEncontrada = reservas.buscar(reserva);
		if (reservaEncontrada == null) {
			return null;
		}
		return new Reserva(reservaEncontrada);
	}

	// CREAMOS MÉTODO REALIZARRESERVA
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}

	// CREAMOS MÉTODO ANULARRESERVA
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	// CREAMOS MÉTODO RESERVAPROFESOR
	public Reserva[] getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}

	// CREAMOS MÉTODO RESERVAAULA
	public Reserva[] getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}

	// CREAMOS MÉTODO RESERVAPERMANENCIA
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	// CREAMOS MÉTODO CONSULTARDISPONIBILIDAD
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}

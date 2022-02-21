package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {
	//DECLARACIÓN DE ATRIBUTOS
	Controlador controlador;

	//CREAMOS CONSTRUCTOR DEFAULT
	public Vista() {
		Opcion.setVista(this);
	}

	//CREAMOS MÉTODO SETCONTROLADOR
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	//CREAMOS MÉTODO COMENZAR
	public void comenzar() {
		Consola.mostrarCabecera("Programa de gestión para reservar aulas");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	//CREAMOS MÉTODO SALIR
	public void salir() {
		System.out.println("¡Hasta la próxima!");
	}

	//CREAMOS MÉTODO INSERTARAULA
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Se ha insertado el aula correctamente");
		} catch (OperationNotSupportedException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO BORRAR AULA
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.borrarAula(aula);
			System.out.println("Se ha borrado el aula correctamente.");
		} catch (OperationNotSupportedException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO BUSCARAULA
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = controlador.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("No existe ningún aula con ese nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO LISTARAULA
	public void listarAula() {
		Consola.mostrarCabecera("Listar aulas");
		String[] aulas = controlador.representarAulas();
		if (aulas.length != 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}

	//CREAMOS MÉTODO INSERTARPROFESOR
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("El profesor se ha insertado correctamente");
		} catch (OperationNotSupportedException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO BORRARPROFESOR
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("El profesor se ha borrado correctamente.");
		} catch (OperationNotSupportedException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO BUSCARPROFESOR
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO LISTARPROFESOR
	public void listarProfesor() {
		Consola.mostrarCabecera("Listar profesores");
		String[] profesores = controlador.representarProfesores();
		if (profesores.length != 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}

	//CREAMOS MÉTODO REALIZARRESERVA
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		try {
			controlador.realizarReserva(leerReserva(Consola.leerProfesor()));
			System.out.println("La reserva se ha realizado correctamente");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO LEERRESERVA
	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Leer reserva");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva reserva = new Reserva(profesor, Consola.leerAula(), permanencia);
		return reserva;
	}

	//CREAMOS MÉTODO ANULAR RESERVA
	public void anularReserva() {
		Consola.mostrarCabecera("Anular reserva");
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("La reserva se ha anulado correctamente.");
		} catch (OperationNotSupportedException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	//CREAMOS MÉTODO LISTAR RESERVAS
	public void listarReservas() {
		Consola.mostrarCabecera("Listar Reservas");
		String[] reservas = controlador.representarReservas();
		if (reservas.length != 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}

	//CREAMOS MÉTODO LISTARRESERVASAULA
	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas aula");
		Aula aula = Consola.leerAula();
		Reserva[] reservasAulas = controlador.getReservasAula(aula);
		if (reservasAulas.length != 0) {
			for (Reserva reservaAula : reservasAulas) {
				System.out.println(reservaAula);
			}
		} else {
			System.out.println(aula.getNombre() + " no tiene ninguna reserva a su nombre.");
		}
	}

	//CREAMOS MÉTODO LISTARRESERVASPROFESOR
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas profesor");
		Profesor profesor = Consola.leerProfesor();
		Reserva[] reservasProfesor = controlador.getReservasProfesor(profesor);
		if (reservasProfesor.length != 0) {
			for (Reserva reservaProfesor : reservasProfesor) {
				System.out.println(reservaProfesor);
			}
		} else {
			System.out.println(profesor.getNombre() + " no tiene ninguna reserva a su nombre.");
		}
	}

	//CREAMOS MÉTODO LISTARRESERVASPERMANENCIA
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas permanencia");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservasPermanencia = controlador.getReservasPermanencia(permanencia);
		if (reservasPermanencia.length != 0) {
			for (Reserva reservaPermanencia : reservasPermanencia) {
				System.out.println(reservaPermanencia);
			}
		} else {
			System.out.println(permanencia.getDia() + ": no hay ninguna reserva.");
		}
	}

	//CREAMOS MÉTODO CONSULTARDISPONIBILIDAD
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		if (controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println(aula + " se encuentra disponible el dia " + permanencia.getDia() + ".");
		} else {
			System.out.println(aula + " no se encuentra disponible el dia " + permanencia.getDia() + ".");
		}
	}

}

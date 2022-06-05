package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	// DECLARACIÓN DE ATRIBUTOS
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static Opcion[] opciones = Opcion.values();
	static Tramo[] tramos = Tramo.values();

	// CREAMOS CONSTRUCTOR CONSOLA (UTILIDAD, NO SE INSTANCIAN OBJETOS).
	private Consola() {

	}

	// CREAMOS MÉTODO MOSTRARMENU
	public static void mostrarMenu() {
		mostrarCabecera("¡Hola! Bienvenido al sistema gestor de reservas de aulas. ¡A trabajar!");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	// CREAMOS MÉTODO MOSTRARCABECERA
	public static void mostrarCabecera(String cabecera) {
		LocalDate presente = LocalDate.now();
		String salida = " Hoy es " + presente.format(FORMATO_DIA).toString();
		System.out.println(cabecera + salida);
	}

	// CREAMOS MÉTODO ELEGIR OPCION
	public static int elegirOpcion() {
		System.out.println("");
		System.out.println("Por favor, elija una de las opciones del menú: ");
		System.out.println("");
		int opcionElegida = Entrada.entero();
		while (opcionElegida < 0 || opcionElegida > Opcion.values().length) {
			System.out.println("Por favor, elija una opción comprendida entre 0 y 15: ");
			opcionElegida = Entrada.entero();
		}
		return opcionElegida;
	}

	// CREAMOS MÉTODO LEERAULA
	public static Aula leerAula() {
		Aula aula = new Aula(leerNombreAula());
		return new Aula(aula);
	}

	// CREAMOS MÉTODO LEERNOMBREAULA
	public static String leerNombreAula() {
		System.out.println("Introduzca el nombre del aula");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

	// CREAMOS MÉTODO LEERPROFESOR
	public static Profesor leerProfesor() {
		String nombreProfesor = leerNombreProfesor();
		System.out.println("Introduzca el correo del profesor");
		String correoProfesor = Entrada.cadena();
		System.out.println("Introduzca el teléfono del profesor");
		String telefonoProfesor = Entrada.cadena();
		Profesor profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);
		return new Profesor(profesor);
	}

	// CREAMOS MÉTODO LEERNOMBREPROFESOR
	public static String leerNombreProfesor() {
		System.out.println("Introduzca el nombre del profesor");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;
	}

	// CREAMOS MÉTODO LEERTRAMO
	public static Tramo leerTramo() {
		Tramo tramoFinal = null;
		boolean problema = false;
		do {
			System.out.println("Elija un tramo horario:");
			for (Tramo t : tramos) {
				System.out.println(t.toString());
			}
			String tramoElegido = Entrada.cadena();
			if (tramoElegido.equalsIgnoreCase(tramos[0].toString())) {
				tramoFinal = Tramo.MANANA;
				problema = false;
			} else if (tramoElegido.equalsIgnoreCase(tramos[1].toString())) {
				tramoFinal = Tramo.TARDE;
				problema = false;
			} else {
				System.out.println("ERROR: El tramo introducido no es válido");
				problema = true;
			}
		} while (problema == true);
		return tramoFinal;
	}

	// CREAMOS MÉTODO LEERDIA
	public static LocalDate leerDia() {
		LocalDate fechaFinal = null;
		boolean problema = false;
		do {
			try {
				System.out.println("Introduzca una fecha(formato dd/mm/aaaa):");
				String fechaIntroducida = Entrada.cadena();
				fechaFinal = LocalDate.parse(fechaIntroducida, FORMATO_DIA);
				problema = false;

			} catch (DateTimeParseException e) {
				System.out.println("ERROR: Formato incorrecto");
				problema = true;
			}
			if (fechaFinal.isBefore(LocalDate.now())) {
				System.out.println("ERROR: La fecha introducida no puede ser anterior al día presente");
				problema = true;
			}
		} while (problema == true);
		return fechaFinal;
	}
}

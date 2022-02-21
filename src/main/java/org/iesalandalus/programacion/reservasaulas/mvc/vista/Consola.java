package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	// DECLARACIÓN DE ATRIBUTOS
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");;

	// CREAMOS CONSTRUCTOR CONSOLA (UTILIDAD, NO SE INSTANCIAN OBJETOS).
	private Consola() {

	}

	//CREAMOS MÉTODO MOSTRARMENU
	public static void mostrarMenu() {
		mostrarCabecera("¡Hola! Bienvenido al sistema gestor de reservas de aulas. ¡A trabajar!");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	//CREAMOS MÉTODO MOSTRARCABECERA
	public static void mostrarCabecera(String cabecera) {
		LocalDate presente = LocalDate.now();
		String salida = " Hoy es " + presente.format(FORMATO_DIA).toString();
		System.out.println(cabecera + salida);
	}

	//CREAMOS MÉTODO ELEGIR OPCION
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

	//CREAMOS MÉTODO LEERAULA
	public static Aula leerAula() {
		Aula aula = new Aula(leerNombreAula());
		return new Aula(aula);
	}

	//CREAMOS MÉTODO LEERNOMBREAULA
	public static String leerNombreAula() {
		System.out.println("Introduzca el nombre del aula");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

	//CREAMOS MÉTODO LEERPROFESOR
	public static Profesor leerProfesor() {
		String nombreProfesor = leerNombreProfesor();
		System.out.println("Introduzca el correo del profesor");
		String correoProfesor = Entrada.cadena();
		System.out.println("Introduzca el teléfono del profesor");
		String telefonoProfesor = Entrada.cadena();
		Profesor profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);
		return new Profesor(profesor);
	}
	

	//CREAMOS MÉTODO LEERNOMBREPROFESOR
	public static String leerNombreProfesor() {
		System.out.println("Introduzca el nombre del profesor:");
		String nombre = Entrada.cadena();
		return nombre;
	}

	//CREAMOS MÉTODO LEERTRAMO
	public static Tramo leerTramo() {
		System.out.println("Eliga un tramo horio (mañana o tarde): ");
		int indice = Entrada.entero();
		switch (indice) {
		case 1:
			return Tramo.MANANA;

		case 2:
			return Tramo.TARDE;

		default:
			return null;
		}
	}

	//CREAMOS MÉTODO LEERDIA
	public static LocalDate leerDia() {
		System.out.println("Introduzca una fecha con el siguiente formato: dd/MM/aaaa:");
		return LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
	}
}

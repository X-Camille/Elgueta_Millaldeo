package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Registro {
    public static void main(String[] args) {
        String[][] registro = new String[50][3]; // se le declara como tipo String
        int opcion = -1; // Se le cambió el nombre a "opcion"

        do {
            System.out.println("""
                Menú
                1) Agregar persona.
                2) Mostrar la cantidad de personas mayores de edad.
                3) Mostrar la cantidad de personas menores de edad.
                4) Mostrar la cantidad de personas de tercera edad.
                5) Mostrar la cantidad de personas según estado civil (Soltero/a - Casado/a).
                6) Salir.
                """);

            do {
                try {
                    opcion = new Scanner(System.in).nextInt(); // Error de buenas prácticas
                } catch (InputMismatchException e) {
                    System.err.println("Opción inválida"); // OJO
                }
            } while (opcion > 0 && opcion <= 6); // Error de lógica, se cambió el "o" por un "y"

            if (opcion == 1) {
                if (hayCupo(registro)) { // Si hay cupo en la matriz registro
                    int indiceDisponible = obtenerUltimoEspacio(registro);
                    String nombre;
                    String Estadocivil;
                    String edad; // Se le cambió a tipo String

                    while (true) {
                        try {
                            nombre = new Scanner(System.in).nextLine();
                        } catch (InputMismatchException e) {
                            System.err.println("Opción inválida"); // El continue no es necesario
                        }
                        break;
                    }

                    while (true) {
                        try {
                            Estadocivil = new Scanner(System.in).nextLine();
                        } catch (InputMismatchException e) {
                            System.err.println("Opción inválida");
                        }
                        break;
                    }

                    while (true) {
                        try {
                            edad = new Scanner(System.in).nextLine(); // Se declara edad como int
                        } catch (InputMismatchException e) {
                            System.err.println("Opción inválida");
                        }
                        break;
                    }

                    registro[indiceDisponible][0] = nombre;
                    registro[indiceDisponible][1] = Estadocivil;
                    registro[indiceDisponible][2] = edad;
                    System.out.println("Persona agregada.");
                } else {
                    System.out.println("No hay cupo.");
                }
            } else if (opcion == 2) {
                int mayoresDeEdad = 0;

                for (String[] persona : registro) {
                    if (Integer.parseInt(persona[2]) >= 18) mayoresDeEdad++; // Se pasa el valor de String a int utilizando la clase Integer
                }

                System.out.println("Hay " + mayoresDeEdad + " mayores de edad.");
            } else if (opcion == 3) {
                int menoresDeEdad = 0;
                int queSera = obtenerUltimoEspacio(registro);

                for (int i = 0; i < queSera; i++) {
                    if (Integer.parseInt(registro[i][2]) < 18) menoresDeEdad++;
                }

                System.out.println("Hay " + menoresDeEdad + " menores de edad.");
            } else if (opcion == 4) {
                int mmmm = 0;

                for (String[] persona : registro) {
                    if (Integer.parseInt(persona[2]) >= 60 && persona[1].equals("casado/a")) {
                        mmmm++;
                    } else if (Integer.parseInt(persona[2]) >= 65 && persona[1].equals("soltero/a")) {
                        mmmm++;
                    }
                }
                System.out.println("Hay " + mmmm + " personas de tercera edad");
            } else if (opcion == 5) {
                int c = 0;
                int d = 0;
                for (String[] persona : registro) {
                    if (persona[1].equals("casado/a")) {
                        c++;
                    } else if (persona[1].equals("soltero/a")) {
                        d++;
                    }
                }

                System.out.println("Hay " + d + " casados/as.");
                System.out.println("Hay " + c + " solteros/as.");
            } else if (opcion == 6) {
                System.out.println("Programa finalizado");
            }
        } while (opcion == 6);
    }

    public static int obtenerUltimoEspacio(String[][] registro) {
        return registro.length - opa(registro);
    }

    public static boolean hayCupo(String[][] registro) {
        return opa(registro) != 0; // retorna verdadero si opa es distinto de 0
    }

    public static int opa(String[][] registro) {
        for (int i = 0; i < registro.length; i++) {
            if (registro[i][0].equals("")) { // equals funcione para comparar String, no double y String
                return registro.length - i;
            }
        }
        return 0;
    }
}

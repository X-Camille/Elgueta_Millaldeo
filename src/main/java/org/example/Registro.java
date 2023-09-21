package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Registro {
    public static void main(String[] args) {
        String[][] registro = new String[50][3]; // se le declara como tipo String
        int personasAgregadas = 0;

        int opcion;

        while (personasAgregadas < 50) {
            mostrarOpciones();
            opcion = ingresarOpcion();
            switch (opcion) {
                case 1:
                    agregarPersona(registro, personasAgregadas);
                    personasAgregadas++;
                    break;
                case 2:
                    contarMayoresDeEdad(registro);
                    break;
                case 3:
                    contarMenoresDeEdad(registro);
                    break;
                case 4:
                    contarPersonasTerceraEdad(registro);
                    break;
                case 5:
                    contarPersonasSegunEstadoCivil(registro);
                    break;
                case 6:
                    System.out.println("Saliendo del programa");
                    return; //
                default:
                    System.err.println("Opción inválida");
            }
        }

    }

    public static void mostrarOpciones() {
        System.out.println("""
                Menú
                1) Agregar persona.
                2) Mostrar la cantidad de personas mayores de edad.
                3) Mostrar la cantidad de personas menores de edad.
                4) Mostrar la cantidad de personas de tercera edad.
                5) Mostrar la cantidad de personas según estado civil (Soltero/a - Casado/a).
                6) Salir.
                """);
    }

    public static int ingresarOpcion() {
        int opcion;
        do {
            opcion = manejarExcepcionDeEntradaInt();
        } while (opcion < 1 || opcion > 6); // Corrección en la condición lógica
        return opcion;
    }

    public static int manejarExcepcionDeEntradaInt() {
        Scanner scanner = new Scanner(System.in);
        int entrada = -1;
        try {
            entrada = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Opción inválida");
        }
        return entrada;
    }


    public static void agregarPersona(String[][] registro, int index) {
        Scanner scanner = new Scanner(System.in);
        if (index >= 0 && index < registro.length) {
            System.out.println("Ingrese el nombre de la persona:");
            registro[index][0] = scanner.nextLine();

            System.out.println("Ingrese el estado civil de la persona:");
            registro[index][1] = scanner.nextLine();

            System.out.println("Ingrese la edad de la persona:");
            registro[index][2] = scanner.nextLine();

            System.out.println("Persona agregada.");
        } else {
            System.out.println("No hay espacio para agregar más personas.");
        }
    }

    public static void contarMayoresDeEdad(String[][] registro) {
        int mayoresDeEdad = 0;

        for (String[] persona : registro) {
            if (persona != null && persona.length > 2 && persona[2] != null && !persona[2].isEmpty()) {
                try {
                    int edad = Integer.parseInt(persona[2]);
                    if (edad >= 18) {
                        mayoresDeEdad++;
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que la cadena no sea un número válido
                    System.err.println("Error al convertir la edad en número: " + persona[2]);
                }
            }
        }

        System.out.println("Hay " + mayoresDeEdad + " mayores de edad.");
    }


    public static void contarMenoresDeEdad(String[][] registro) {
        int menoresDeEdad = 0;
        int queSera = obtenerUltimoEspacio(registro);

        for (int i = 0; i < queSera; i++) {
            if (registro[i] != null && Integer.parseInt(registro[i][2]) < 18) {
                menoresDeEdad++;
            }
        }

        System.out.println("Hay " + menoresDeEdad + " menores de edad.");
    }

    public static void contarPersonasTerceraEdad(String[][] registro) {
        int personasTerceraEdad = 0;

        for (String[] persona : registro) {
            if (persona != null && ((Integer.parseInt(persona[2]) >= 60 && persona[1].equals("casado/a")) ||
                    (Integer.parseInt(persona[2]) >= 65 && persona[1].equals("soltero/a")))) {
                personasTerceraEdad++;
            }
        }

        System.out.println("Hay " + personasTerceraEdad + " personas de tercera edad.");
    }
    public static void contarPersonasSegunEstadoCivil(String[][] registro) {
        int casados = 0;
        int solteros = 0;

        for (String[] persona : registro) {
            if (persona != null) {
                if (persona[1].equalsIgnoreCase("casado/a")) {
                    casados++;
                } else if (persona[1].equalsIgnoreCase("soltero/a")) {
                    solteros++;
                }
            }
        }

        System.out.println("Hay " + solteros + " solteros/as.");
        System.out.println("Hay " + casados + " casados/as.");
    }

    public static int obtenerUltimoEspacio(String[][] registro) {
        return registro.length - existeEspacioVacio(registro);
    }

    public static boolean hayEspacioEnLaMatriz(String[][] registro) {
        return existeEspacioVacio(registro) != -1;
    }

    public static int existeEspacioVacio(String[][] registro) {
        for (int i = 0; i < registro.length; i++) {
            if (registro[i][0] == null || registro[i][0].isEmpty()) {
                return i;
            }
        }
        return -1;
    }
}



/**
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

    public static boolean hayEspacioEnLaMatriz(String[][] registro) {
        return existeEspacioVacio(registro) != 0; // retorna verdadero si opa es distinto de 0
    }

    public static int existeEspacioVacio(String[][] registro) {
        for (int i = 0; i < registro.length; i++) {
            if (registro[i][0].equals("")) { // equals funcione para comparar String, no double y String
                return registro.length - i;
            }
        }
        return 0;
    }

    public static void menu(){

    }
    public static int ingresarOpcion() {
        int opcion;
        do {
            opcion = manejarExcepcionDeEntradaInt();
        } while (opcion < 1 || opcion > 6); // Corrección en la condición lógica
        return opcion;
    }

    public static int manejarExcepcionDeEntradaInt() {
        Scanner scanner = new Scanner(System.in);
        int entrada = -1;
        try {
            entrada = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Opción inválida");
        }
        return entrada;
    }


}
**/
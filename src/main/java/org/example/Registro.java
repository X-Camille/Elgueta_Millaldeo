package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Registro {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
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

            registro[index][1] = ingresarEstadoCivil();

            registro[index][2] = ingresarEdad();

            System.out.println("Persona agregada.");
        } else {
            System.out.println("No hay espacio para agregar más personas.");
        }
    }

    public static String ingresarEstadoCivil() {
        Scanner scanner = new Scanner(System.in);
        String estadoCivil;

        while (true) {
            System.out.println("Ingrese el estado civil (soltero/a o casado/a):");
            estadoCivil = scanner.nextLine().toLowerCase();

            if (estadoCivil.equals("soltero") || estadoCivil.equals("soltera") || estadoCivil.equals("casado") || estadoCivil.equals("casada")) {
                return estadoCivil;
            } else {
                System.err.println("Estado civil no válido. Ingrese 'soltero/a' o 'casado/a'.");
            }
        }
    }

    public static String ingresarEdad(){
        int edad;
        do{
            System.out.println("Ingrese la edad de la persona:");
            edad = manejarExcepcionDeEntradaInt();
        } while(!validarEdad(edad));
        return String.valueOf(edad);
    }

    public static boolean validarEdad(int edad){
        if(edad > 0 && edad < 130){
            return true;
        }
        return false;
    }

    public static int contarMayoresDeEdad(String[][] registro) {
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
        return mayoresDeEdad;
    }


    public static int contarMenoresDeEdad(String[][] registro) {
        int menoresDeEdad = 0;

        for (String[] persona : registro) {
            if (persona != null && persona.length > 2 && persona[2] != null && !persona[2].isEmpty()) {
                try {
                    int edad = Integer.parseInt(persona[2]);
                    if (edad < 18) {
                        menoresDeEdad++;
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que la cadena no sea un número válido
                    System.err.println("Error al convertir la edad en número: " + persona[2]);
                }
            }
        }

        System.out.println("Hay " + menoresDeEdad + " menores de edad.");
        return menoresDeEdad;
    }

    public static int contarPersonasTerceraEdad(String[][] registro) {
        int personasTerceraEdad = 0;

        for (String[] persona : registro) {
            if (persona != null && persona.length > 2 && persona[2] != null && !persona[2].isEmpty()) {
                try {
                    int edad = Integer.parseInt(persona[2]);
                    String estadoCivil = persona[1];
                    if ((edad >= 60 && estadoCivil.equals("casado/a")) || (edad >= 65 && estadoCivil.equals("soltero/a")) || edad >= 70) {
                        personasTerceraEdad++;
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que la cadena no sea un número válido
                    System.err.println("Error al convertir la edad en número: " + persona[2]);
                }
            }
        }

        System.out.println("Hay " + personasTerceraEdad + " personas de tercera edad.");
        return personasTerceraEdad;
    }

    public static int[] contarPersonasSegunEstadoCivil(String[][] registro) {
        int casados = 0;
        int solteros = 0;

        for (String[] persona : registro) {
            if (persona != null && persona.length > 1 && persona[1] != null) {
                if (persona[1].equalsIgnoreCase("casado") || persona[1].equalsIgnoreCase("casada")) {
                    casados++;
                } else if (persona[1].equalsIgnoreCase("soltero") || persona[1].equalsIgnoreCase("soltera")) {
                    solteros++;
                }
            }
        }

        System.out.println("Hay " + solteros + " solteros/as.");
        System.out.println("Hay " + casados + " casados/as.");

        return new int[]{solteros, casados};
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

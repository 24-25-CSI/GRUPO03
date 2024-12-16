package com.example.app;
/*
 2.	Realice el cifrado de un mensaje por permutación de columnas, 
 teniendo como clave 5 columnas y la cantidad de filas que sean necesarias (garantice al menos 3). 
 Los espacios del mensaje original se sustituyen con el carácter “#” Si en la matriz de cifrado sobran espacios, 
 estos deben llenarse con el carácter “_”.
*/

public class ejercicio2 {

    public static char[][] obtenerMatriz(String mensaje) {
        // Definir el número de columnas
        int columnas = 5;
        // Calcular el número de filas necesarias
        int filas = (int) Math.ceil((double) mensaje.length() / columnas);
        // Asegurar al menos 3 filas
        filas = Math.max(filas, 3);
        // Crear la matriz
        char[][] matriz = new char[filas][columnas];
        int indice = 0;

        // Llenar la matriz con los caracteres del mensaje
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (indice < mensaje.length()) {
                    matriz[i][j] = mensaje.charAt(indice++);
                } else {
                    matriz[i][j] = '_'; // Llenar los espacios restantes con '_'
                }
            }
        }
        return matriz;

    }

    public static String mensajeCifrado(char[][] matriz) {
        // Leer la matriz columna por columna para obtener el mensaje cifrado

        int columnas = matriz[0].length;
        int filas = matriz.length;
        System.out.println("columnas: " + columnas + " filas: " + filas);
        StringBuilder mensajeCifrado = new StringBuilder();
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++) {
                mensajeCifrado.append(matriz[i][j]);
            }
        }

        return mensajeCifrado.toString();
    }


    public static void mostrarMatriz(char[][] matriz) {
        System.out.println("Matriz Cifrada:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String mensaje = "Hola mundo"; // Mensaje Inicial
        mensaje = mensaje.replace(" ", ""); // Eliminar espacios del mensaje
        char[][] matrizCifrada = obtenerMatriz(mensaje);
        mostrarMatriz(matrizCifrada);

        String mensajeCifrado = mensajeCifrado(matrizCifrada); // Calculamos el mensaje cifrado basado en la matriz
        System.out.println("Mensaje cifrado: "+mensajeCifrado); // Mostramos el mensaje cifrado

        System.out.println("Mensaje Original: " + mensaje); // mensaje inicial
    }
}
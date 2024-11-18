package grupo3;

import java.util.Scanner;

/*
 3. Algoritmo que realice el cifrado de un mensaje por permutación de columnas, teniendo como clave n
 columnas. Tanto n como el texto del mensaje se ingresan al iniciar el algoritmo. El algoritmo debe controlar
 que el número de caracteres del mensaje (sin espacios), sea menor o igual que n x n. Imprima la matriz de
 cifrado, el mensaje original y el mensaje cifrado.  Si en la matriz de cifrado sobran espacios para
 almacenar los caracteres del mensaje original, estos deben llenarse con "*".
*/

public class ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar el mensaje
        System.out.print("Ingrese el mensaje: ");
        String mensaje = scanner.nextLine().replace(" ", "");  // Eliminar espacios del mensaje
        int n = 0;  // Variable para el número de columnas

        // Bucle para solicitar el número de columnas hasta que sea suficiente
        do {
            System.out.print("Ingrese el número de columnas: ");
            n = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            
            // Comprobar si el mensaje cabe en la matriz de n x n
            if (mensaje.length() > n * n) {
                System.out.println("El mensaje es demasiado largo para la clave proporcionada. Por favor, ingrese un número mayor.");
            }
        } while (mensaje.length() > n * n);
        
        // Crear la matriz de cifrado y llenarla con '*'
        char[][] matriz = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = '*';
            }
        }
        
        // Llenar la matriz con el mensaje, columna por columna
        int index = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (index < mensaje.length()) {
                    matriz[i][j] = mensaje.charAt(index++);
                }
            }
        }
        
        // Imprimir la matriz de cifrado
        System.out.println("Matriz de cifrado:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        
        // Crear el mensaje cifrado leyendo la matriz en orden de filas
        StringBuilder mensajeCifrado = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mensajeCifrado.append(matriz[i][j]);
            }
        }
        
        // Imprimir el mensaje original y el mensaje cifrado
        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Mensaje cifrado: " + mensajeCifrado.toString());
        
        scanner.close();
    }
}

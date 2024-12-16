package grupo3;

import java.util.Scanner;

/*
 realice un cifrado de un mensaje por permutacion de fila,
  teniendo en como clave 5 filas y la cantidad de columnas que 
  sean necesarias(garantice al menos 3). los espacios del mensaje original
   se sustituyen con el caracter "-", si en la matriz del cifrado sobran
    espacios, estos deben llenarse con el caracter "*". 
*/

public class ejercicioUNO {
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar el mensaje
        System.out.print("Ingrese el mensaje: ");
        String mensaje = scanner.nextLine().replace(" ", "-");  // Sustituir espacios con '-'
        int n = 0;  // Variable para el número de filas

        // Bucle para solicitar el número de filas hasta que sea suficiente
        do {
            System.out.print("Ingrese el número de filas (mínimo 5): ");
            n = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            
            // Comprobar si el mensaje cabe en la matriz de n x n
            if (mensaje.length() > n * n) {
                System.out.println("El mensaje es demasiado largo para la clave proporcionada. Por favor, ingrese un número mayor.");
            }
        } while (mensaje.length() > n * n || n < 5);
        
        // Crear la matriz de cifrado y llenarla con '*'
        char[][] matriz = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = '*';  // Llenar con '*'
            }
        }
        
        // Llenar la matriz con el mensaje, fila por fila
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
        
        // Crear el mensaje cifrado leyendo la matriz en orden de columnas
        StringBuilder mensajeCifrado = new StringBuilder();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                mensajeCifrado.append(matriz[i][j]);
            }
        }
        
        // Imprimir el mensaje original y el mensaje cifrado
        System.out.println("Mensaje original: " + mensaje.replace("-", " "));  // Reemplazar '-' por espacios
        System.out.println("Mensaje cifrado: " + mensajeCifrado.toString());
        
        scanner.close();
    }
}

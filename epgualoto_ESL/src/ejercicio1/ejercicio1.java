/*
Realice el cifrado de un mensaje por permutación de filas, 
teniendo como clave 5 filas y la cantidad de columnas que sean necesarias 
(garantice al menos 3). Los espacios del mensaje original se sustituyen con 
el carácter "-". Si en la matriz de cifrado sobra espacios, estos deben llenarse 
con el carácter "*"
en cualquiera de los casos, el algoritmo recibe el mensaje al iniciar y debe 
mostrar los siguientes resultados:
El mensaje original
La matriz de cifrado
El mensaje cifrado

En el caso que se produzca algún error en la ejecución, el mismo debe mostrarse 
para alertar al usuario.
*/

package ejercicio1;

import java.util.Scanner;

public class ejercicio1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Ingrese el mensaje a cifrar:");
			String mensaje = scanner.nextLine();
			
			int filas= 5;
			
			//Se reemplazara los espacios por -.
			mensaje = mensaje.replace(" ", "-");
			
			//Al menos tenga 3 columnas.
			int columnas = Math.max(3, (int) Math.ceil((double) mensaje.length() / filas));
			
            char[][] matriz = new char[filas][columnas];
            
            System.out.println("\nMensaje ingresado: " + mensaje);
            
            // Se rellena la matriz con el mesaje ingresado y en los espacios en blanco con --> '*'
            int matrizVacia = 0;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (matrizVacia < mensaje.length()) {
                        matriz[i][j] = mensaje.charAt(matrizVacia);
                    } else {
                        matriz[i][j] = '*';
                    }
                    matrizVacia++;
                }
            }
            
            System.out.println("\nMatriz cifrada: ");
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
           
            StringBuilder mensajeCifrado = new StringBuilder();
            for (int j = 0; j < columnas; j++) {
                for (int i = 0; i < filas; i++) {
                    mensajeCifrado.append(matriz[i][j]);
                }
            }
            
            System.out.println("\nMensaje cifrado: " + mensajeCifrado.toString());
              
            } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

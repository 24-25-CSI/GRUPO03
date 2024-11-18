package grupo3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 6. Algoritmo que realice el cifrado de una cadena de caracteres utilizando la siguiente tabla de cifrado.
 La cadena de caracteres se ingresa al iniciar el programa. Si algún caracter del texto no existe en la matriz,
 coloque "**". Imprima la matriz de cifrado, el mensaje original y el mensaje cifrado.
*/

public class ejercicio6 {
    public static void main(String[] args) {
        // Solicitar al usuario que ingrese la cadena de caracteres
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cadena de caracteres: ");
        String cadena = scanner.nextLine();

        // Definir la matriz de cifrado
        Map<Character, String> matrizCifrado = new HashMap<>();
        String[] equivalencias = {
            "QA", "QS", "QD", "QF", "QG", // A-E
            "WA", "WS", "WD", "WF", "WG", // F-J
            "EA", "ES", "ED", "EF", "EG", // K-O
            "RA", "RS", "RD", "RF", "RG", // P-T
            "TA", "TS", "TD", "TF", "TG"  // U-Y
        };

        // Asignar las equivalencias a las letras
        for (int i = 0; i < equivalencias.length; i++) {
            matrizCifrado.put((char) ('A' + i), equivalencias[i]);
            matrizCifrado.put((char) ('a' + i), equivalencias[i]);
        }

        // Cifrar la cadena de caracteres
        StringBuilder resultado = new StringBuilder();
        for (char caracter : cadena.toCharArray()) {
            String cifrado = matrizCifrado.getOrDefault(caracter, "**");
            resultado.append(cifrado);
        }

        // Imprimir la matriz de cifrado, la cadena de caracteres ingresada y la cadena de caracteres cifrada
        System.out.println("Matriz de cifrado: " + matrizCifrado);
        System.out.println("Cadena de caracteres ingresada: " + cadena);
        System.out.println("Cadena de caracteres cifrada: " + resultado.toString());

        scanner.close();
    }
}

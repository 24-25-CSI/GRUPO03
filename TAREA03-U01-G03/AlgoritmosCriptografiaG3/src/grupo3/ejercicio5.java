package grupo3;

import java.util.Scanner;

/*
5. Algoritmo que realice el cifrado de una cadena de caracteres mediante un método de sustitución
 Polialfabético de Vigenère. La cadena se ingresa al iniciar el algoritmo. El algoritmo debe mostrar la
 cadena de caracteres ingresada, la clave de cifrado y la cadena de caracteres cifrada.
*/

public class ejercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar al usuario que ingrese la cadena de caracteres y la clave de cifrado
        System.out.print("Ingrese la cadena de caracteres: ");
        String cadena = scanner.nextLine();
        System.out.print("Ingrese la clave de cifrado: ");
        String clave = scanner.nextLine().toLowerCase();
        
        // Definir el alfabeto
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        
        // Cifrar la cadena de caracteres utilizando la clave de cifrado
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = Character.toLowerCase(cadena.charAt(i));
            char claveCaracter = clave.charAt(i % clave.length());
            if (alfabeto.indexOf(caracter) != -1) {
                int indice = (alfabeto.indexOf(caracter) + alfabeto.indexOf(claveCaracter)) % alfabeto.length();
                resultado.append(alfabeto.charAt(indice));
            } else {
                resultado.append(cadena.charAt(i));  // Dejar el carácter sin cambios si no está en el alfabeto
            }
        }
        
        // Imprimir la cadena de caracteres ingresada, la clave de cifrado y la cadena de caracteres cifrada
        System.out.println("Cadena de caracteres ingresada: " + cadena);
        System.out.println("Clave de cifrado: " + clave);
        System.out.println("Cadena de caracteres cifrada: " + resultado.toString());
        
        scanner.close();
    }
}


package grupo3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 4. Algoritmo que realice el cifrado de una cadena de caracteres mediante un método de sustitución
 Monoalfabético de desplazamiento n caracteres a la derecha. Tanto la palabra como el valor de n se
 ingresan al iniciar el algoritmo. El algoritmo debe mostrar el alfabeto original, el alfabeto cifrado, la
 cadena de caracteres ingresada y su resultado.
*/

public class ejercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar al usuario que ingrese la cadena de caracteres y el valor de desplazamiento
        System.out.print("Ingrese la cadena de caracteres: ");
        String cadena = scanner.nextLine();
        System.out.print("Ingrese el valor de desplazamiento: ");
        int n = scanner.nextInt();
        
        // Definir el alfabeto original
        String alfabetoOriginal = "abcdefghijklmnopqrstuvwxyz";
        
        // Crear el alfabeto cifrado desplazando el alfabeto original n caracteres a la derecha
        String alfabetoCifrado = alfabetoOriginal.substring(n) + alfabetoOriginal.substring(0, n);
        
        // Crear un mapa de mapeo de cada carácter del alfabeto original a su correspondiente en el alfabeto cifrado
        Map<Character, Character> mapeo = new HashMap<>();
        for (int i = 0; i < alfabetoOriginal.length(); i++) {
            mapeo.put(alfabetoOriginal.charAt(i), alfabetoCifrado.charAt(i));
        }
        
        // Cifrar la cadena de caracteres reemplazando cada carácter por su correspondiente en el alfabeto cifrado
        StringBuilder resultado = new StringBuilder();
        for (char caracter : cadena.toLowerCase().toCharArray()) {
            resultado.append(mapeo.getOrDefault(caracter, caracter));
        }
        
        // Imprimir el alfabeto original, el alfabeto cifrado, la cadena de caracteres ingresada y su resultado
        System.out.println("Alfabeto original: " + alfabetoOriginal);
        System.out.println("Alfabeto cifrado: " + alfabetoCifrado);
        System.out.println("Cadena de caracteres ingresada: " + cadena);
        System.out.println("Resultado: " + resultado.toString());
        
        scanner.close();
    }
}


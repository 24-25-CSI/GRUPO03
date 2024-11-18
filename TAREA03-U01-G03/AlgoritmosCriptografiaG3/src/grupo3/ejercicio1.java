package grupo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 1. Algoritmo que escriba todas las permutaciones posibles de una palabra de longitud n SIN espacios
 (Anagrama). La palabra se ingresa al iniciar el algoritmo. El algoritmo debe mostrar el número total de
 permutaciones y las 10 primeras ordenadas alfabéticamente.
 */

public class ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa una palabra: ");
        String palabra = scanner.nextLine();

        List<String> permutaciones = new ArrayList<>();
        generarPermutaciones("", palabra, permutaciones);

        // Ordenar las permutaciones alfabéticamente
        Collections.sort(permutaciones);

        // Mostrar el total de permutaciones
        System.out.println("Total de permutaciones: " + permutaciones.size());

        // Mostrar las 10 primeras permutaciones
        System.out.println("Las 10 primeras permutaciones:");
        for (int i = 0; i < Math.min(10, permutaciones.size()); i++) {
            System.out.println(permutaciones.get(i));
        }
    }

    private static void generarPermutaciones(String prefijo, String palabra, List<String> permutaciones) {
        int n = palabra.length();
        if (n == 0) {
            permutaciones.add(prefijo);
        } else {
            for (int i = 0; i < n; i++) {
                generarPermutaciones(prefijo + palabra.charAt(i), palabra.substring(0, i) + palabra.substring(i + 1, n), permutaciones);
            }
        }
    }
}

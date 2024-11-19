package main.java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;

public class algoritmoSHA1 {

    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get("data/palabras_10000.txt");
        StringBuilder contentBuilder = new StringBuilder();
        long startTotal = System.nanoTime();

        // Métrica T-E1: Tiempo de lectura del archivo
        long startRead = System.nanoTime();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endRead = System.nanoTime();
        long te1Read = endRead - startRead;

        String content = contentBuilder.toString();
        int caracteresEntrada = content.length();

        // Métrica T-E2: Tiempo para inicializar SHA-1
        long startKey = System.nanoTime();
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        long endKey = System.nanoTime();
        long te2Key = endKey - startKey;

        // Métrica T-E3: Tiempo para calcular el hash
        long startHash = System.nanoTime();
        byte[] hash = sha1.digest(content.getBytes(StandardCharsets.UTF_8));
        long endHash = System.nanoTime();
        long te3Hash = endHash - startHash;

        // Convertir el hash a representación hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        String hashHex = hexString.toString();
        int caracteresSalida = hashHex.length();

        // Métrica T-E4: Tiempo ficticio de descifrado
        long startDecrypt = System.nanoTime();
        // Simulación: no se realiza descifrado en SHA-1
        Thread.sleep(10); // Simula un descifrado con tiempo fijo
        long endDecrypt = System.nanoTime();
        long te4Decrypt = endDecrypt - startDecrypt;

        long endTotal = System.nanoTime();
        long totalTime = endTotal - startTotal;

        // Convertir nanosegundos a segundos
        double te1ReadSeconds = te1Read / 1_000_000_000.0;
        double te2KeySeconds = te2Key / 1_000_000_000.0;
        double te3HashSeconds = te3Hash / 1_000_000_000.0;
        double te4DecryptSeconds = te4Decrypt / 1_000_000_000.0;
        double totalTimeSeconds = totalTime / 1_000_000_000.0;

        // Resultados
        System.out.println("Caracteres de entrada: " + caracteresEntrada);
        System.out.println("Caracteres de salida: " + caracteresSalida);
        System.out.println("Tiempo de lectura (T-E1): " + te1ReadSeconds + " s");
        System.out.println("Tiempo de clave (T-E2): " + te2KeySeconds + " s");
        System.out.println("Tiempo de cifrado (T-E3): " + te3HashSeconds + " s");
        System.out.println("Tiempo de descifrado (T-E4): " + te4DecryptSeconds + " s");
        System.out.println("Tiempo total (T-Total): " + totalTimeSeconds + " s");
        System.out.println("Hash SHA-1: " + hashHex);
    }
}
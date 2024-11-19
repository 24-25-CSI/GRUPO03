package main.java;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;

public class algoritmoDES {

    public static void main(String[] args) throws Exception {
        // Medir el tiempo de generación de la clave
        long startKeyGenTime = System.nanoTime();
        // Generar clave de 56 bits para DES
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        long endKeyGenTime = System.nanoTime();

        // Mostrar la clave generada
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Clave generada: " + encodedKey);

        // Mostrar el tiempo que demoró en generar la clave en segundos
        System.out.println("Tiempo de generación de clave: " + ((endKeyGenTime - startKeyGenTime) / 1_000_000_000.0) + " segundos\n");

        // Crear el objeto Cipher para cifrar con DES
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Especificar la ruta del archivo
        Path filePath = Paths.get("data/palabras_10.txt");

        // Obtener el nombre del archivo
        String fileName = filePath.getFileName().toString();

        // Medir el tiempo para leer el archivo
        long startFileReadTime = System.nanoTime();
        // Contadores para los caracteres de entrada y salida
        long inputChars = 0;
        long outputChars = 0;
        
        // Leer el contenido del archivo línea por línea
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            System.out.println("Leyendo archivo: " + fileName); // Mostrar el nombre del archivo

            String line;
            while ((line = reader.readLine()) != null) {
                // Contar los caracteres de entrada
                inputChars += line.length();
                
                // Cifrar la línea
                byte[] text = line.getBytes(StandardCharsets.UTF_8);
                long startEncryptTime = System.nanoTime();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedText = cipher.doFinal(text);
                long endEncryptTime = System.nanoTime();
                String encodedEncryptedText = Base64.getEncoder().encodeToString(encryptedText);
                System.out.println("Texto cifrado: " + encodedEncryptedText);

                // Contar los caracteres de salida (texto cifrado)
                outputChars += encodedEncryptedText.length();

                // Desencriptar la línea
                long startDecryptTime = System.nanoTime();
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decodedEncryptedText = Base64.getDecoder().decode(encodedEncryptedText);
                byte[] decryptedText = cipher.doFinal(decodedEncryptedText);
                long endDecryptTime = System.nanoTime();
                System.out.println("Texto desencriptado: " + new String(decryptedText, StandardCharsets.UTF_8) + "\n");

                System.out.println("RESULTADOS");
                System.out.println("Tiempo de cifrado: " + ((endEncryptTime - startEncryptTime) / 1_000_000_000.0) + " segundos");
                System.out.println("Tiempo de descifrado: " + ((endDecryptTime - startDecryptTime) / 1_000_000_000.0) + " segundos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endFileReadTime = System.nanoTime();

        // Calcular el tiempo total sumado en segundos
        double totalTime = (endFileReadTime - startKeyGenTime) / 1_000_000_000.0;
        
        System.out.println("Tiempo de lectura del archivo: " + ((endFileReadTime - startFileReadTime) / 1_000_000_000.0) + " segundos");
        System.out.println("Número de caracteres de entrada: " + inputChars);
        System.out.println("Número de caracteres de salida: " + outputChars);
        System.out.println("Tiempo total de ejecución: " + totalTime + " segundos");
    }
}
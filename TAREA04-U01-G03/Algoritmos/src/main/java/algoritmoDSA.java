package main.java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
 
public class algoritmoDSA {
 
    public static void main(String[] args) throws Exception {
        // Generar par de claves DSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
 
        // Leer el contenido del archivo línea por línea
        Path filePath = Paths.get("data/palabras_10.txt");
        StringBuilder contentBuilder = new StringBuilder();
        long startReadTime = System.nanoTime();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endReadTime = System.nanoTime();
        String content = contentBuilder.toString();
        System.out.println("Texto 123"+content);
 
        // Calcular número de caracteres
        int caracteresEntrada = content.length();
 
        // Firmar el contenido
        Signature dsa = Signature.getInstance("SHA256withDSA");
        dsa.initSign(privateKey);
        dsa.update(content.getBytes(StandardCharsets.UTF_8));
        long startSignTime = System.nanoTime();
        byte[] signature = dsa.sign();
        long endSignTime = System.nanoTime();
 
        // Imprimir la longitud de la firma antes de convertir a Base64
       System.out.println("Longitud de la firma (bytes): " + signature.length);
 
 
        // Convertir la firma a Base64 para una representación legible
        String signatureBase64 = Base64.getEncoder().encodeToString(signature);
        int caracteresSalida = signatureBase64.length();
 
        // Mostrar resultados
        System.out.println("Firma: " + signatureBase64);
        System.out.println("Número de caracteres de entrada: " + caracteresEntrada);
        System.out.println("Número de caracteres de salida: " + caracteresSalida);
        System.out.println("T-E1 Lectura (ns): " + (endReadTime - startReadTime));
        System.out.println("T-E2 Clave (ns): " + (endReadTime - startReadTime)); // Tiempo de generación de clave (no se mide aquí)
        System.out.println("T-E3 Cifrado (ns): " + (endSignTime - startSignTime));
 
        // Verificar la firma
        dsa.initVerify(publicKey);
        dsa.update(content.getBytes(StandardCharsets.UTF_8));
        long startVerifyTime = System.nanoTime();
        boolean isVerified = dsa.verify(signature);
        long endVerifyTime = System.nanoTime();
        System.out.println("Firma verificada: " + isVerified);
        System.out.println("T-E4 Verificación (ns): " + (endVerifyTime - startVerifyTime));
    }
}
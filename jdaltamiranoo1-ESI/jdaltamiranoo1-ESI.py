
import math

def row_permutation_cipher():
    try:
        # Entrada del mensaje original
        mensajeOriginal = input("Ingrese el mensaje a cifrar: ")

        # Reemplazar espacios con '-'
        formatoMensaje = mensajeOriginal.replace(" ", "-")

        # Parámetros de la matriz de cifrado
        rows = 5
        cols = math.ceil(len(formatoMensaje) / rows)
        cols = max(cols, 3)  # Garantizar al menos 3 columnas

        # Crear la matriz de cifrado
        cipher_matrix = [['*' for _ in range(cols)] for _ in range(rows)]

        # Llenar la matriz con los caracteres del mensaje
        index = 0
        for i in range(rows):
            for j in range(cols):
                if index < len(formatoMensaje):
                    cipher_matrix[i][j] = formatoMensaje[index]
                    index += 1

        # Construir el mensaje cifrado leyendo por columnas
        encrypted_message = "".join(
            cipher_matrix[i][j] for j in range(cols) for i in range(rows)
        )

        # Mostrar resultados
        print("\nMensaje Original:")
        print(mensajeOriginal)

        print("\nMatriz de Cifrado:")
        for row in cipher_matrix:
            print(" ".join(row))

        print("\nMensaje Cifrado:")
        print(encrypted_message)

    except Exception as e:
        print(f"Error vuelva a intentarlo: {str(e)}")

# Ejecutar el programa
row_permutation_cipher()

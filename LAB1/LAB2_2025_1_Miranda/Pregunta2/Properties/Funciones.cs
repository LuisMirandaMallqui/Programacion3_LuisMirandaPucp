public class Funciones
{
    /// <summary>
    /// Suma dos números enteros y devuelve el resultado.
    /// </summary>
    /// <param name="a">Primer número a sumar.</param>
    /// <param name="b">Segundo número a sumar.</param>
    /// <returns>El resultado de la suma de <paramref name="a"/> y <paramref name="b"/>.</returns>
    public static int Sumar(int a, int b)
    {
        return a + b;
    }

    public static string CapitalizarPalabra(string palabra)
    {
        if (string.IsNullOrEmpty(palabra))
        {
            return palabra; // Retorna la palabra tal cual si es null o vacía
        }
        // Convierte la primera letra a mayúscula y el resto a minúscula
        return char.ToUpper(palabra[0]) + palabra.Substring(1).ToLower();
    }
}
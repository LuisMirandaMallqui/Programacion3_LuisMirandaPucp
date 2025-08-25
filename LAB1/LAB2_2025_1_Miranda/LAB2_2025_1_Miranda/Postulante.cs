namespace LAB2_2025_1_Miranda
{
    /// <summary>
    /// Esta clase representa un postulante con informacion personal
    /// </summary>
    public class Postulante
    {
        //Atributos
        /// <summary>
        /// Objeto de la clase Postulante
        /// </summary>
        private string paterno;
        private string materno;
        private string nombre;
        private string hola;
        private string dni;
        public string Paterno { get => paterno; set => paterno = value; }
        public string Materno { get => materno; set => materno = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Dni { get => dni; set => dni = value; }
        ///<summary>
        ///Constructor por defecto
        /// </summary>/
        public Postulante()
        {
            paterno = "";
            materno = "";
            nombre = "";
            dni = "";
        }
        ///<summary>
        ///Constructor con parametros que crea una nueva instancia de la clase Postulante
        /// </summary>
        public Postulante(string paterno, string materno, string nombre, string dni)
        {
            this.paterno = paterno;
            this.materno = materno;
            this.nombre = nombre;
            this.dni = dni;
        }

        ///<summary>
        ///onstructor copia que crea una nueva instancia de la clase Postulante
        ///</summary>
        public Postulante(Postulante otroPostulante)
        {
            this.paterno = otroPostulante.paterno;
            this.materno = otroPostulante.materno;
            this.nombre = otroPostulante.nombre;
            this.dni = otroPostulante.dni;
        }



        /// <summary>
        /// Suma dos números enteros y devuelve el resultado.
        /// </summary>
        /// <param name="a">Primer número a sumar.</param>
        /// <param name="b">Segundo número a sumar.</param>
        /// <returns>El resultado de la suma de <paramref name="a"/> y <paramref name="b"/>.</returns>
        /// <example>
        /// Ejemplo de uso:
        /// int resultado = Calculadora.Sumar(5, 3);
        /// Console.WriteLine(resultado); // Imprime 8
        /// </example>
        public static int Sumar(int a, int b)
        {
            return a + b;
        }

        public override string ToString()
        {
            return paterno + materno + ", " + nombre + " (" + dni + ")";
        }
    }
}

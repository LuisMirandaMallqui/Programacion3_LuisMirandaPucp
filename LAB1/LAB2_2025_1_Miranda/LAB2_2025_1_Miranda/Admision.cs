using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LAB2_2025_1_Miranda
{
    class Admision
    {
        private List<FichaEvaluacion> lista_fichas;
        private int cantidad_admitidos;
        private int cantidad_postulantes;

        public int Cantidad_admitidos { get => cantidad_admitidos; set => cantidad_admitidos = value; }
        public int Cantidad_postulantes { get => cantidad_postulantes; set => cantidad_postulantes = value; }
        internal List<FichaEvaluacion> Lista_fichas { get => lista_fichas; }
        public void agregarFichaDeEvaluacion(FichaEvaluacion ficha)
        {
            FichaEvaluacion copia = new FichaEvaluacion(ficha);
            lista_fichas.Add(copia);
            cantidad_postulantes++;

            if (copia.Estado == FichaEvaluacion.EstadoCandidato.ADMITIDO)
            {
                cantidad_admitidos++;
            }
        }
        /// <summary>
        ///  CONSTRUCTOR POR DEFECTO
        /// </summary>
        public Admision()
        {
            lista_fichas = new List<FichaEvaluacion>();
            cantidad_admitidos = 0;
            cantidad_postulantes = 0;
        }

        public override string ToString()
        {
            // Crear el reporte de admisión
            string resultado = $"PROCESO DE ADMISION: {cantidad_postulantes} postulantes, {cantidad_admitidos} admitidos\n\nLISTA DE ADMITIDOS:\n";

            // Recorrer la lista de fichas y agregar los postulantes admitidos al reporte
            foreach (var ficha in lista_fichas)
            {
                if (ficha.Estado == FichaEvaluacion.EstadoCandidato.ADMITIDO)
                {
                    // Se asume que la clase Postulante tiene sobrecargado el método ToString() para retornar el nombre completo
                    resultado += ficha.Candidato.ToString() + "\n";
                }
            }

            return resultado;
        }
    }
}

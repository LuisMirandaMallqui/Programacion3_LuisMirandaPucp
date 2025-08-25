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
            lista_fichas.Add(ficha);
            cantidad_postulantes++;

            if(ficha.Estado == FichaEvaluacion.estado_candidato.ADMITIDO)
            {
                cantidad_admitidos++;
            }
        }
    }
}
